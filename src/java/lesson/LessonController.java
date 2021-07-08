/**
 * Jun 16, 2021
 *
 * @author Vu Duy Anh
 */
package lesson;

import common.entities.Course;
import common.entities.Lesson;
import common.entities.LessonType;
import common.entities.Status;
import common.entities.User;
import common.utilities.Controller;
import course.CourseService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quiz.QuizService;

public class LessonController extends HttpServlet implements Controller {

    private LessonService lessonService;
    private CourseService courseService;
    private QuizService quizService;

    private final int itemPerPage = 5;

    @Override
    public void init() throws ServletException {
        lessonService = new LessonService();
        courseService = new CourseService();
        quizService = new QuizService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if (operation == null) {
            processGetLesson(request, response);
        } else {
            switch (operation) {
                case "VIEW":
                    processViewLesson(request, response);
                    break;
                case "UPDATESTATUS":
                    processUpdateLessonStatus(request, response);
                    break;
                case "PAGINATION":
                    processPagination(request, response);
                    break;
                case "VIEWUSERLESSON":
                    processViewUserLesson(request, response);
                    break;
                case "VIEWUSERLESSONDETAIL":
                    processViewUserLessonDetail(request, response);
                    break;
                case "PREVIOUSLESSON":
                    processPreviousLesson(request, response);
                    break;
                case "NEXTLESSON":
                    processNextLesson(request, response);
                    break;
                case "DONELESSON":
                    processDoneLesson(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lessonId;
        String operation = request.getParameter("operation");
        if (operation.equals("ADDNEWLESSON")) {
            lessonId = request.getParameter("lessonId");
            if (lessonId == null || lessonId.equals("")) {
                addLesson(request, response);
            } else {
                editLesson(request, response);
            }
        }
    }

    private void processViewLesson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // View lesson details
        String lessonId = request.getParameter("lessonId");
        int id;
        if (lessonId == null || lessonId.equals("")) {
            Lesson lesson = null;
            viewLesson(request, response, lesson);
        } else {
            id = Integer.parseInt(lessonId);
            Lesson lesson = lessonService.getLesson(id);
            viewLesson(request, response, lesson);
        }
    }

    private void processPagination(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        ArrayList<Lesson> lessonList = (ArrayList<Lesson>) currentSession.getAttribute("lessonList");
        int page = processPageParameter(request, response, lessonList.size());
        if (page == -1) {
            return;
        }

        ArrayList<Lesson> pageItems = getItemInPage(lessonList, page);

        if (pageItems != null) {
            request.setAttribute("pageItems", pageItems);
            request.getRequestDispatcher("/auth/teacher/lesson/list.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
        }
    }

    private void processGetLesson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        String keyword = request.getParameter("keyword");
        int subjectId = 1;

        // If there is no subjectId, send redirect to 404
        try {
            subjectId = Integer.parseInt(request.getParameter("subjectId"));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " at ~130 in LessonController");
        }

        // Save the base array into current session
        ArrayList<Lesson> lessons = this.lessonService.getLessonList(subjectId, keyword);
        currentSession.setAttribute("lessonList", lessons);

        // Send data to the list page
        int page = this.processPageParameter(request, response, lessons.size());
        ArrayList<Lesson> pageItems = this.getItemInPage(lessons, page);
        if (pageItems != null) {
            request.setAttribute("pageItems", pageItems);
            request.getRequestDispatcher("/auth/teacher/lesson/list.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
        }
    }

    private void processViewUserLesson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        List<Lesson> lessonList = lessonService.getLessonsByCourseId(courseId);
        Course courseName = courseService.getCourseNameLessonList(courseId);
        session.setAttribute("courseId", courseId);
        request.setAttribute("lessonList", lessonList);
        request.setAttribute("courseName", courseName.getCourseName());
        //session.setAttribute("ketquacuoicung", null);
        request.getRequestDispatcher("/auth/user/course/lesson/view.jsp").forward(request, response);
        //Chỉnh lại url auth/user/course/lesson/view.jsp
    }

    private void processViewUserLessonDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        List<Lesson> allLesson = lessonService.getAllLesson();
        Lesson lessonDetail = lessonService.getLessonDetailByLessonId(lessonId);
        request.setAttribute("lessonDetail", lessonDetail);
        int courseId = Integer.parseInt(session.getAttribute("courseId") + "");
        List<Lesson> lessonList = lessonService.getLessonsByCourseId(courseId);
        session.setAttribute("lessonId", lessonId);
        session.setAttribute("courseId", courseId);
        request.setAttribute("lessonList", lessonList);
        request.setAttribute("allLesson", allLesson);
        session.setAttribute("courseId", courseId);
        //THỬ NGHIỆM:
        for (int i = 0; i < lessonList.size() - 1; i++) {
            int a = lessonList.get(i).getVideoLink().indexOf("embed/");
            request.setAttribute("videoId" + (i + 1), lessonList.get(i).getVideoLink().substring(a + 6));
        }

        request.setAttribute("length", lessonList.size());
        request.setAttribute("lessonId", lessonId);
        Lesson minIdLesson = lessonService.getMinLessonIdByCourseId(courseId);
        Lesson maxIdLesson = lessonService.getMaxLessonIdByCourseId(courseId);
        if (lessonId == minIdLesson.getId()) {
            request.setAttribute("disabled", "disabled");
        } else if (lessonId == maxIdLesson.getId()) {
            request.setAttribute("disabledNext", "disabled");
        }
        PrintWriter out = response.getWriter();
        User u = (User) session.getAttribute("user");
        request.setAttribute("quizIdCuaDuyAnh", quizService.getQuizIdTheoYeuCauCuaDuyAnh(lessonId));
        String videoId = "DxcpvaDglb4";
        request.setAttribute("videoId", videoId);
        session.setAttribute("lessonIdSession", lessonId);
        request.getRequestDispatcher("/auth/user/course/lesson/detail.jsp").forward(request, response);
        //Chỉnh lại url auth/user/course/lesson/detail.jsp
    }

    private void processPreviousLesson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        session.setAttribute("courseId", courseId);
        List<Lesson> lessonList = lessonService.getLessonsByCourseId(courseId);
        request.setAttribute("lessonList", lessonList);
        List<Lesson> allLesson = lessonService.getAllLesson();
        request.setAttribute("allLesson", allLesson);
        Lesson minIdLesson = lessonService.getMinLessonIdByCourseId(courseId);
        //THỬ NGHIỆM:
        for (int i = 0; i < lessonList.size() - 1; i++) {
            int a = lessonList.get(i).getVideoLink().indexOf("embed/");
            request.setAttribute("videoId" + (i + 1), lessonList.get(i).getVideoLink().substring(a + 6));
        }
        if (lessonId == minIdLesson.getId()) {
            Lesson lessonDetail = lessonService.getLessonDetailByLessonId(lessonId);
            request.setAttribute("lessonDetail", lessonDetail);
            request.setAttribute("lessonId", minIdLesson.getId());
            session.setAttribute("lessonIdSession", minIdLesson.getId());
        } else {
            Lesson lessonDetail = lessonService.getLessonDetailByLessonId(lessonId);
            request.setAttribute("lessonDetail", lessonDetail);
            request.setAttribute("lessonId", lessonId);
            session.setAttribute("lessonIdSession", lessonId);
        }
        request.setAttribute("minIdLesson", minIdLesson.getId());
        request.getRequestDispatcher("/auth/user/course/lesson/detail.jsp").forward(request, response);
        //Chỉnh lại url auth/user/course/lesson/detail.jsp
    }

    private void processNextLesson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        session.setAttribute("courseId", courseId);
        List<Lesson> lessonList = lessonService.getLessonsByCourseId(courseId);
        request.setAttribute("lessonList", lessonList);
        List<Lesson> allLesson = lessonService.getAllLesson();
        request.setAttribute("allLesson", allLesson);
        Lesson maxIdLesson = lessonService.getMaxLessonIdByCourseId(courseId);
        //THỬ NGHIỆM:
        for (int i = 0; i < lessonList.size() - 1; i++) {
            int a = lessonList.get(i).getVideoLink().indexOf("embed/");
            request.setAttribute("videoId" + (i + 1), lessonList.get(i).getVideoLink().substring(a + 6));
        }
        if (lessonId == maxIdLesson.getId()) {
            Lesson lessonDetail = lessonService.getLessonDetailByLessonId(lessonId);
            request.setAttribute("lessonDetail", lessonDetail);
            request.setAttribute("lessonId", maxIdLesson.getId());
            session.setAttribute("lessonIdSession", maxIdLesson.getId());
        } else {
            Lesson lessonDetail = lessonService.getLessonDetailByLessonId(lessonId);
            request.setAttribute("lessonDetail", lessonDetail);
            request.setAttribute("lessonId", lessonId);
            session.setAttribute("lessonIdSession", lessonId);
        }
        request.setAttribute("maxIdLesson", maxIdLesson.getId());
        request.getRequestDispatcher("/auth/user/course/lesson/detail.jsp").forward(request, response);
        //Chỉnh lại url auth/user/course/lesson/detail.jsp
    }

    private void processDoneLesson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("lessonId").contains("INACTIVE")) {
            int alpha = request.getParameter("lessonId").indexOf("E");
            String doneLessonId = request.getParameter("lessonId").substring(alpha + 1);
            lessonService.updateDoneLesson(doneLessonId);
            response.sendRedirect(request.getContextPath() + "/auth/user/course/lesson?operation=VIEWUSERLESSONDETAIL&&lessonId=" + doneLessonId);
            //Chỉnh lại url auth/user/course/lesson?
        } else if (request.getParameter("lessonId").contains("ACTIVE")) {
            int alpha = request.getParameter("lessonId").indexOf("E");
            String doneLessonId = request.getParameter("lessonId").substring(alpha + 1);
            lessonService.updateUndoneLesson(doneLessonId);
            response.sendRedirect(request.getContextPath() + "/auth/user/course/lesson?operation=VIEWUSERLESSONDETAIL&&lessonId=" + doneLessonId);
            //Chỉnh lại url auth/user/course/lesson?
        }
    }

    private void processUpdateLessonStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        Status lessonStatus = Status.valueOf(request.getParameter("status"));
        boolean isUpdate = lessonService.updateLessonStatus(lessonId, lessonStatus);

        if (isUpdate) {
            response.sendRedirect(request.getContextPath() + "/auth/teacher/lesson");
        }
    }

    private void addLesson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lessonName = request.getParameter("lesson-name");
        LessonType lessonType = LessonType.valueOf(request.getParameter("type"));
        int order = Integer.parseInt(request.getParameter("order"));
        int courseId = Integer.parseInt(request.getParameter("course"));

        if (lessonType.equals(LessonType.valueOf("SUBJECT_TOPIC"))) {
            Lesson lesson = new Lesson(lessonName, order, lessonType, courseId);
            if (lessonService.checkLessonExist(lesson) == null) {
                if (lessonService.addLessonDetail(lesson)) {
                    response.sendRedirect(request.getContextPath() + "/auth/teacher/lesson");
                }
            }
        } else if (lessonType.equals(LessonType.valueOf("LESSON"))) {
            String videoLink = request.getParameter("video-link");
            String html = request.getParameter("html-content");
            Lesson lesson = new Lesson(lessonName, order, lessonType, courseId, videoLink, html);
            if (lessonService.checkLessonExist(lesson) == null) {
                if (lessonService.addLessonDetail(lesson)) {
                    response.sendRedirect(request.getContextPath() + "/auth/teacher/lesson");
                }
            }
        } else {
            int quizId = Integer.parseInt(request.getParameter("quiz"));
            String html = request.getParameter("html-quiz");
            Lesson lesson = new Lesson(lessonName, order, lessonType, courseId, html, quizId);
            if (lessonService.checkLessonExist(lesson) == null) {
                if (lessonService.addLessonDetail(lesson)) {
                    response.sendRedirect(request.getContextPath() + "/auth/teacher/lesson");
                }
            }
        }
    }

    private void editLesson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("lessonId"));
        String lessonName = request.getParameter("lesson-name");
        LessonType lessonType = LessonType.valueOf(request.getParameter("type"));
        int order = Integer.parseInt(request.getParameter("order"));
        int courseId = Integer.parseInt(request.getParameter("course"));
        Lesson lesson = lessonService.getLesson(order);
        request.setAttribute("lesson", lesson);
        if (lessonType.equals(LessonType.valueOf("SUBJECT_TOPIC"))) {
            lesson = new Lesson(lessonName, order, lessonType, courseId);
            lessonService.updateLessonDetail(lesson, id);
        } else if (lessonType.equals(LessonType.valueOf("LESSON"))) {
            String videoLink = request.getParameter("video-link");
            String html = request.getParameter("html-content");
            lesson = new Lesson(lessonName, order, lessonType, courseId, videoLink, html);
            lessonService.updateLessonDetail(lesson, id);
        } else {
            int quizId = Integer.parseInt(request.getParameter("quiz"));
            String html = request.getParameter("html-quiz");
            lesson = new Lesson(lessonName, order, lessonType, courseId, html, quizId);
            lessonService.updateLessonDetail(lesson, id);
        }
        response.sendRedirect(request.getContextPath() + "/auth/teacher/lesson");
    }

    private void viewLesson(HttpServletRequest request, HttpServletResponse response, Lesson lesson)
            throws ServletException, IOException {
        int courseId = 1;
        HashMap<Integer, String> getCourses = courseService.getCourses();
        HashMap<Integer, String> quizzesForLesson = quizService.getQuizForLesson(courseId);
        request.setAttribute("course", getCourses);
        request.setAttribute("quiz", quizzesForLesson);
        request.setAttribute("lesson", lesson);
        request.getRequestDispatcher("/auth/teacher/lesson/detail.jsp").forward(request, response);
    }

    private ArrayList<Lesson> getItemInPage(ArrayList<Lesson> lessonList, int page) {
        if (page > ((lessonList.size() / 5) + 1)) {
            return null;
        }
        int startItem = (page - 1) * itemPerPage;
        int endItem = (startItem + itemPerPage) > lessonList.size() ? lessonList.size() : startItem + itemPerPage;

        ArrayList<Lesson> itemInPage = new ArrayList<>();
        for (int i = startItem; i < endItem; i++) {
            itemInPage.add(lessonList.get(i));
        }

        return itemInPage;
    }

    private int processPageParameter(HttpServletRequest request, HttpServletResponse response, int listSize)
            throws ServletException, IOException {
        // If not yet receive page param (first time in page) change it to 1
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));

            int pageNum;
            if (listSize % itemPerPage == 0) {
                pageNum = listSize / itemPerPage;
            } else {
                pageNum = (listSize / itemPerPage) + 1;
            }

            if (page < 1 || page > pageNum) {
                response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
                return -1;
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " at ~96 LessonController");
        }

        return page;
    }

}

/**
 * Jun 16, 2021
 *
 * @author Vu Duy Anh
 */
package lesson;

import common.entities.Lesson;
import common.entities.LessonType;
import common.entities.Quiz;
import common.entities.Status;
import common.utilities.Controller;
import course.CourseService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
        HttpSession currentSession = request.getSession();

        String operation = request.getParameter("operation");

        if (operation == null) {
            processGetLesson(request, response);
        } else {
            String lessonId;
            switch (operation) {
                case "VIEW":
                    // View lesson details
                    lessonId = request.getParameter("lessonId");
                    int id;
                    if (lessonId == null || lessonId.equals("")) {
                        Lesson lesson = null;
                        viewLesson(request, response, lesson);
                    } else {
                        id = Integer.parseInt(lessonId);
                        Lesson lesson = lessonService.getLesson(id);
                        viewLesson(request, response, lesson);
                    }
                    break;
                case "UPDATESTATUS":
                    processUpdateLessonStatus(request, response);
                    break;
                case "PAGINATION":
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
                    break;
                case "ADDNEWLESSON":
                    lessonId = request.getParameter("lessonId");
                    if (lessonId == null || lessonId.equals("")) {
                        addLesson(request, response);
                    } else {
                        editLesson(request, response);
                    }
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
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
            System.out.println(e.getMessage() + " at ~70 in LessonController");
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

    private void processUpdateLessonStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        Status lessonStatus = Status.valueOf(request.getParameter("status"));
        boolean isUpdate = lessonService.updateLessonStatus(lessonId, lessonStatus);

        if (isUpdate) {
            response.sendRedirect(request.getContextPath() + "/auth/teacher/lesson");
        }
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

    private void addLesson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lessonName = request.getParameter("lesson-name");
        LessonType lessonType = LessonType.valueOf(request.getParameter("type"));
        int order = Integer.parseInt(request.getParameter("order"));
        int courseId = Integer.parseInt(request.getParameter("course"));

        if (lessonType.equals(LessonType.valueOf("SUBJECT_TOPIC"))) {
            Lesson lesson = new Lesson(lessonName, order, lessonType, courseId);
            if (lessonService.checkLessonExist(lesson) == null) {
                lessonService.addLessonDetail(lesson);
            }
        } else if (lessonType.equals(LessonType.valueOf("LESSON"))) {
            String videoLink = request.getParameter("video-link");
            String html = request.getParameter("html-content");
            Lesson lesson = new Lesson(lessonName, order, lessonType, courseId, videoLink, html);
            if (lessonService.checkLessonExist(lesson) == null) {
                lessonService.addLessonDetail(lesson);
            }
        } else {
            int quizId = Integer.parseInt(request.getParameter("quiz"));
            String html = request.getParameter("html-quiz");
            Lesson lesson = new Lesson(lessonName, order, lessonType, courseId, html, quizId);
            if (lessonService.checkLessonExist(lesson) == null) {
                lessonService.addLessonDetail(lesson);
            }
        }
        response.sendRedirect("/auth/teacher/lesson");
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
        response.sendRedirect("/auth/teacher/lesson");
    }

    private void viewLesson(HttpServletRequest request, HttpServletResponse response, Lesson lesson)
            throws ServletException, IOException {
        HashMap<Integer, String> getCourses = courseService.getCourses();
//        ArrayList<Quiz> quizList = quizService.getQuizList(1, "", null);

        request.setAttribute("course", getCourses);
//        request.setAttribute("quiz", quizList);
        request.setAttribute("lesson", lesson);
        request.getRequestDispatcher("/auth/teacher/lesson/detail.jsp").forward(request, response);
    }

}

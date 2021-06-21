/**
 * Jun 17, 2021
 *
 * @author Hoang Tien Minh
 */
package lesson;

import common.entities.Lesson;
import common.entities.Status;
import common.utilities.Controller;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LessonController extends HttpServlet implements Controller {

    private LessonService lessonService;
    private final int itemPerPage = 5;

    @Override
    public void init() throws ServletException {
        lessonService = new LessonService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        String operation = request.getParameter("operation");

        if (operation == null) {
            processGetLesson(request, response);
        } else {
            switch (operation) {
                case "VIEW":
                    // View lesson details
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
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if (operation.equals("FILTER")) {
            processGetLesson(request, response);
        } else {

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
}

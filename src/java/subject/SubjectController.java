/**
 * Jun 15, 2021
 *
 * @author Hoang Tien Minh
 */
package subject;

import common.entities.Status;
import common.entities.User;
import course.CourseService;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SubjectController extends HttpServlet {

    private CourseService courseService;
    private int itemPerPage = 4;

    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if (operation.equals("GETSUBJECT")) {
            // This will set a List of List<String> in session and can be used to display data to subjectlist
            processInputForSubject(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void processInputForSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        int categoryId;
        String status = request.getParameter("status");

        if (keyword == null) {
            keyword = "";
        }
        try {
            categoryId = Integer.parseInt(request.getParameter("category"));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            categoryId = 0;
        }

        // We don't need to process status because null is consider as status's default value
        processGetSubject(request, response, keyword, categoryId, status);
    }

    private void processGetSubject(HttpServletRequest request, HttpServletResponse response,
            String keyword, int categoryId, String status) throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        // Get current user (teacher/admin) id to get the according subject list
        int teacherId;
        if (Boolean.parseBoolean((String) currentSession.getAttribute("isAdmin"))) {
            teacherId = -1;
        } else {
            teacherId = ((User) currentSession.getAttribute("user")).getId();
        }
        ArrayList<ArrayList<String>> subjectList = (ArrayList<ArrayList<String>>) currentSession.getAttribute("subjectList");

        // If not yet receive page param (first time in page) change it to 1
        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            page = 1;
        }

        // If the subjectList is not yet stored in session
        if (subjectList == null) {
            subjectList = courseService.getSubjectList(keyword, categoryId, Status.valueOf(status), teacherId);
            currentSession.setAttribute("subjectList", subjectList);
        }

        // Send data to the list page
        ArrayList<ArrayList<String>> pageItems = getItemInPage(subjectList, page);
        if (pageItems != null) {
            request.setAttribute("pageItems", pageItems);
            request.getRequestDispatcher(request.getContextPath() + "/auth/teacher/subject/list.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
        }

    }

    // Get the right data for the right page
    private ArrayList<ArrayList<String>> getItemInPage(ArrayList<ArrayList<String>> subjectList, int page) {
        if (page * itemPerPage > subjectList.size()) {
            return null;
        }
        int startItem = page * itemPerPage;
        int endItem = (startItem + itemPerPage) > subjectList.size() ? subjectList.size() : startItem + itemPerPage;

        ArrayList<ArrayList<String>> subjectInPage = new ArrayList<>();
        for (int i = startItem; i < endItem; i++) {
            subjectInPage.add(subjectList.get(i));
        }

        return subjectInPage;
    }
}

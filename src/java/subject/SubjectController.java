/**
 * Jun 15, 2021
 *
<<<<<<< HEAD
 * @author Nguyen Khanh Toan
 */
package subject;

import common.entities.Dimension;
import java.util.List;
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

    protected void displayData(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Display subject dimesion detail of specified course
        int courseId = 1;
        List<Dimension> listDimension = courseService.getSubjectDimensionByCourseId(courseId);
        request.setAttribute("listDimension", listDimension);
        request.getRequestDispatcher("auth/teacher/subject/detail.jsp").forward(request, response);
        //sau khi merge code chỉnh lại đường dẫn auth/teacher/subject/detail.jsp
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation == null) {
            request.setAttribute("activeId", 1);
            displayData(request, response);
        } else if (operation.equals("DELETESUBJECT")) {
            int courseIdDelete = Integer.parseInt(request.getParameter("courseId"));
            int dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
            courseService.deleteSubjectDimensionByCourseId(courseIdDelete, dimensionId);
            request.setAttribute("activeId", 2);
            displayData(request, response);
        } else if (operation.equals("GETDIMENSIONDETAIL")) {
            int dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
            Dimension dimensionDetail = courseService.getDimensionDetail(dimensionId);
            request.setAttribute("dimensionDetail", dimensionDetail);
            request.setAttribute("dimensionId", dimensionId);
            request.getRequestDispatcher("auth/teacher/subject/dimensionEditInfo.jsp").forward(request, response);
            //sau khi merge code chỉnh lại đường dẫn auth/teacher/subject/dimensionEditInfo.jsp
        
        } else if (operation.equals("GETSUBJECT")) {
            // This will set a List of List<String> in session and can be used to display data to subjectlist
            processInputForSubject(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int courseId = 1;
        String operation = request.getParameter("operation");
        if (operation.equals("ADDSUBJECT")) {
            String type = request.getParameter("type");
            String dimension = request.getParameter("dimension");
            int typeInt = 0;
            if (type.equals("Domain")) {
                typeInt = 1;
            } else if(type.equals("Group")) {
                typeInt = 2;
            } else {
                courseService.addDimensionType(type);
                typeInt = courseService.getDimensionTypeDetail(type).getId();
            }
            String description = request.getParameter("description");
            courseService.addDimension(typeInt, dimension, description);
            Dimension dimensionAdd = courseService.getDimensionId(dimension);
            courseService.addDimensionCourse(courseId, dimensionAdd.getId());
            request.setAttribute("activeId", 2);
            displayData(request, response);
        } else if (operation.equals("UPDATESUBJECT")) {
            int dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
            String type = request.getParameter("type");
            int typeInt = 0;
            if (type.equals("domain")) {
                typeInt = 1;
            } else if(type.equals("group")) {
                typeInt = 2;
            } else {
                typeInt = courseService.getDimensionTypeDetail(type).getId();
            }
            String dimension = request.getParameter("dimension");
            String description = request.getParameter("description");
            courseService.updateSubjectDimension(typeInt, dimension, description, dimensionId);
            request.setAttribute("activeId", 2);
            displayData(request, response);
        }


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

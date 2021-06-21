/**
 * Jun 15, 2021
 *
 * @author Nguyen Khanh Toan
 */
package subject;

import common.entities.Dimension;
import common.entities.DimensionType;
import java.util.List;
import common.entities.Status;
import common.entities.User;
import common.utilities.Controller;
import course.CourseService;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SubjectController extends HttpServlet implements Controller {

    private CourseService courseService;
    private final int itemPerPage = 5;

    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
    }

    protected void displayData(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Display subject dimesion detail of specified course
        int subjectId = 1;
        List<Dimension> listDimension = courseService.getSubjectDimensionByCourseId(subjectId);
        request.setAttribute("listDimension", listDimension);
        request.getRequestDispatcher("/auth/teacher/subject/detail.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();
        String operation = request.getParameter("operation");

        int dimensionId;
        int subjectId = 1;
        if (operation == null) {
            processInputForSubject(request, response);
        } else {
            switch (operation) {
                case "VIEW":
                    currentSession.setAttribute("currentSubject", request.getParameter("subjectId"));
                    request.setAttribute("activeId", 1);
                    displayData(request, response);
                    break;
                case "DELETESUBJECT":
                    subjectId = Integer.parseInt((String) currentSession.getAttribute("currentSubject"));
                    dimensionId = Integer.parseInt(request.getParameter("dimensionId"));

                    courseService.deleteSubjectDimensionByCourseId(subjectId, dimensionId);
                    request.setAttribute("activeId", 2);
                    displayData(request, response);
                    break;
                case "GETDIMENSIONDETAIL":
                    dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
                    Dimension dimensionDetail = courseService.getDimensionDetail(dimensionId);
                    List<DimensionType> dimensionTypeList = courseService.getAllDimenstionType();

                    request.setAttribute("dimensionDetail", dimensionDetail);
                    request.setAttribute("dimensionId", dimensionId);
                    request.setAttribute("dimensionTypeList", dimensionTypeList);

                    request.getRequestDispatcher("/auth/teacher/subject/dimensionEditInfo.jsp").forward(request, response);
                    break;
                case "PAGINATION":
                    getItemInPage(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        String operation = request.getParameter("operation");
        switch (operation) {
            case "ADDSUBJECT": {
                int subjectId = Integer.parseInt((String) currentSession.getAttribute("currentSubject"));
                String type = request.getParameter("type");
                String dimension = request.getParameter("dimension");
                int typeInt = 0;
                switch (type) {
                    case "Domain":
                        typeInt = 1;
                        break;
                    case "Group":
                        typeInt = 2;
                        break;
                    default:
                        courseService.addDimensionType(type);
                        typeInt = courseService.getDimensionTypeDetail(type).getId();
                        break;
                }
                String description = request.getParameter("description");
                courseService.addDimension(typeInt, dimension, description);
                Dimension dimensionAdd = courseService.getDimensionId(dimension);
                courseService.addDimensionCourse(subjectId, dimensionAdd.getId());
                request.setAttribute("activeId", 2);
                displayData(request, response);
                break;
            }
            case "UPDATESUBJECT": {
                int dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
                String type = request.getParameter("type");
                int typeInt = 0;
                switch (type) {
                    case "domain":
                        typeInt = 1;
                        break;
                    case "group":
                        typeInt = 2;
                        break;
                    default:
                        typeInt = courseService.getDimensionTypeDetail(type).getId();
                        break;
                }
                String dimension = request.getParameter("dimension");
                String description = request.getParameter("description");
                courseService.updateSubjectDimension(typeInt, dimension, description, dimensionId);
                request.setAttribute("activeId", 2);
                displayData(request, response);
                break;
            }
            case "FILTER":
                processInputForSubject(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
                break;
        }
    }

    private void processInputForSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        int categoryId = -1;
        String statusString = request.getParameter("status");
        Status status;

        try {
            String category = request.getParameter("category");
            System.out.println(category);
            categoryId = Integer.parseInt(category);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " at ~60 SubjectController");
        }

        if (statusString != null && !statusString.equals("")) {
            status = Status.valueOf(statusString);
        } else {
            status = null;
        }

        // Save selected value to request
        request.setAttribute("selectedCategory", categoryId);
        request.setAttribute("selectedStatus", statusString);
        request.setAttribute("selectedKeyword", keyword);

        // We don't need to process status because null is consider as status's default value
        processGetSubject(request, response, keyword, categoryId, status);
    }

    private void processGetSubject(HttpServletRequest request, HttpServletResponse response,
            String keyword, int categoryId, Status status) throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        // Get current user (teacher/admin) id to get the according subject list
        int teacherId;
        if (currentSession.getAttribute("isAdmin") != null) {
            teacherId = -1;
        } else {
            teacherId = ((User) currentSession.getAttribute("user")).getId();
        }
        ArrayList<ArrayList<String>> subjectList = courseService.getSubjectList(keyword, categoryId, status, teacherId);
        currentSession.setAttribute("subjectList", subjectList);

        // Send data to the list page
        int page = processPageParameter(request, response, subjectList.size());
        ArrayList<ArrayList<String>> pageItems = getItemInPage(subjectList, page);
        if (pageItems != null) {
            request.setAttribute("categories", courseService.getAllCategory());
            request.setAttribute("pageItems", pageItems);
            request.getRequestDispatcher("/auth/teacher/subject/list.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
        }

    }

    private void getItemInPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        int page = Integer.parseInt(request.getParameter("page"));

        ArrayList<ArrayList<String>> pageItems = getItemInPage((ArrayList<ArrayList<String>>) currentSession.getAttribute("subjectList"), page);
        if (pageItems != null) {
            request.setAttribute("categories", courseService.getAllCategory());
            request.setAttribute("pageItems", pageItems);
            request.getRequestDispatcher("/auth/teacher/subject/list.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
        }
    }

    // Get the right data for the right page
    private ArrayList<ArrayList<String>> getItemInPage(ArrayList<ArrayList<String>> subjectList, int page) {
        if (page > ((subjectList.size() / 5) + 1)) {
            return null;
        }
        int startItem = (page - 1) * itemPerPage;
        int endItem = (startItem + itemPerPage) > subjectList.size() ? subjectList.size() : startItem + itemPerPage;

        ArrayList<ArrayList<String>> subjectInPage = new ArrayList<>();
        for (int i = startItem; i < endItem; i++) {
            subjectInPage.add(subjectList.get(i));
        }

        return subjectInPage;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user_course;

import common.entities.CourseRegistation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import common.entities.User;
import common.utilities.Controller;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserCourseController extends HttpServlet implements Controller {

    private UserCourseService userCourseService;
    private final int itemPerPage = 5;

    @Override
    public void init() throws ServletException {
        userCourseService = new UserCourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        String operation = request.getParameter("operation");
        switch (operation) {
            case "SearchByCategory":
                searchCourseByCategory(request, response, u);
                break;
            case "SearchByTitle":
                searchCourseByTitle(request, response, u);
                break;
            case "UpdateStatus":
                updateStatus(request, response, u);
                break;
            case "VIEWALL":
                processViewRegistration(request, response);
                break;
            case "PAGINATION":
                processRegistrationPagination(request, response);
                break;
            case "VIEWDETAIL":
                processViewRegistrationDetail(request, response);
                break;
            default:
                listCourseRegistation(request, response, u);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation == null) {

        } else {
            switch (operation) {
                case "EDITINFO":
                    processEditRegistrationInfo(request, response);
                    break;
                case "FILTER":
                    processFilterRegistration(request, response);
                    break;
                default:
                    send404(request, response);
                    break;
            }
        }
    }

    private void processViewRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processFilterInput(request, response);
    }

    private void processFilterRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processFilterInput(request, response);
    }

    private void processRegistrationPagination(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getItemInPage(request, response);
    }

    private void processViewRegistrationDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = 1;
        int courseId = 1;
//        int userId = Integer.parseInt(request.getParameter("userId"));
//        int courseId = Integer.parseInt(request.getParameter("courseId"));
        CourseRegistation regisDetail = userCourseService.getRegistrationDetail(userId, courseId);
        request.setAttribute("detail", regisDetail);
        request.getRequestDispatcher("/auth/user/registration/detail.jsp").forward(request, response);
    }

    private void processEditRegistrationInfo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void updateStatus(HttpServletRequest request, HttpServletResponse response, User u)
            throws ServletException, IOException {
        int userId = u.getId();
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int status = 0;
        userCourseService.updateStatus(userId, courseId, status);
        response.sendRedirect(request.getContextPath() + "/auth/user/UserCourse?operation=");
    }

    private void searchCourseByTitle(HttpServletRequest request, HttpServletResponse response, User u)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String title = request.getParameter("title");
        List<CourseRegistation> listC = userCourseService.searchCourseByTitle(u.getId(), title);
        for (CourseRegistation o : listC) {
            out.print("<tr>\n"
                    + "                                    <td>" + o.getId() + "</td>\n"
                    + "                                    <td>\n"
                    + "                                        <a href=\"#\">\n"
                    + "                                            " + o.getTitle() + "\n"
                    + "                                        </a>\n"
                    + "                                    </td>\n"
                    + "                                    <td>" + o.getRegistationTime() + "</td>  \n"
                    + "                                    <td>" + o.getTotalCost() + "</td>\n"
                    + "                                    <td>" + o.getValidFrom() + "</td>\n"
                    + "                                    <td>" + o.getValidTo() + "</td>");
            if (o.getStatus() == 0) {
                out.print("<td><span class=\"status text-danger\">&bull;</span> Cancelled</td>");
            }
            if (o.getStatus() == 1) {
                out.print("<td><span class=\"status text-warning\">&bull;</span> \n"
                        + "                                            <a href=\"UserCourse?operation=UpdateStatus&courseId=" + o.getId() + "\" class=\"confirmation\">Submited</a>\n"
                        + "                                        </td>");
            }
            if (o.getStatus() == 2) {
                out.print("<td><span class=\"status text-success\">&bull;</span> Completed</td>");
            }
            out.print("      <td>" + o.getPackages() + "</td>                        \n"
                    + "                                </tr>");
        }
    }

    private void searchCourseByCategory(HttpServletRequest request, HttpServletResponse response, User u)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String categoryId = request.getParameter("category");
        List<CourseRegistation> listC = userCourseService.searchCourseByCategory(u.getId(), Integer.parseInt(categoryId));
        for (CourseRegistation o : listC) {
            out.print("<tr>\n"
                    + "                                    <td>" + o.getId() + "</td>\n"
                    + "                                    <td>\n"
                    + "                                        <a href=\"#\">\n"
                    + "                                            " + o.getTitle() + "\n"
                    + "                                        </a>\n"
                    + "                                    </td>\n"
                    + "                                    <td>" + o.getRegistationTime() + "</td>  \n"
                    + "                                    <td>" + o.getTotalCost() + "</td>\n"
                    + "                                    <td>" + o.getValidFrom() + "</td>\n"
                    + "                                    <td>" + o.getValidTo() + "</td>");
            if (o.getStatus() == 0) {
                out.print("<td><span class=\"status text-danger\">&bull;</span> Cancelled</td>");
            }
            if (o.getStatus() == 1) {
                out.print("<td><span class=\"status text-warning\">&bull;</span> \n"
                        + "                                            <a href=\"UserCourse?operation=UpdateStatus&courseId=" + o.getId() + "\" class=\"confirmation\">Submited</a>\n"
                        + "                                        </td>");
            }
            if (o.getStatus() == 2) {
                out.print("<td><span class=\"status text-success\">&bull;</span> Completed</td>");
            }
            out.print("      <td>" + o.getPackages() + "</td>                        \n"
                    + "                                </tr>");
        }
    }

    private void listCourseRegistation(HttpServletRequest request, HttpServletResponse response, User u)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String index = request.getParameter("page");
        if (index == null || index.equals("")) {
            index = "1";
        }
        int endPage = (userCourseService.countCourseUserRegis() / 5) + (userCourseService.countCourseUserRegis() % 5 == 0 ? 0 : 1);
        request.setAttribute("endPage", endPage);
        /*truyền cái u.getId() vào số 1 ở đây*/
        List<CourseRegistation> listC = userCourseService.getCourseUserRegister(u.getId(), Integer.parseInt(index));
        request.setAttribute("listC", listC);
        request.setAttribute("index", index);
        request.getRequestDispatcher("/auth/user/MyRegistation.jsp").forward(request, response);
    }

    private void processFilterInput(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        String keyword = request.getParameter("keyword");
        String fromString = request.getParameter("validFrom");
        String toString = request.getParameter("validTo");

        Date validFrom = null;
        Date validTo = null;

        String orderBy = request.getParameter("orderBy");

        if (keyword == null || keyword.equals("")) {
            keyword = "";
        }

        if (orderBy == null || orderBy.equals("")) {
            orderBy = "";
        }

        // Change date input to Date object
        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (fromString != null && !fromString.equals("")
                && toString != null && !toString.equals("")) {
            try {
                validFrom = formatter.parse(fromString);
                validTo = formatter.parse(toString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        currentSession.setAttribute("selectedKeyword", keyword);
        currentSession.setAttribute("selectedFrom", fromString);
        currentSession.setAttribute("selectedTo", toString);

        processGetAllRegistration(request, response, keyword, validFrom, validTo, orderBy);
    }

    private void processGetAllRegistration(HttpServletRequest request, HttpServletResponse response,
            String keyword, Date from, Date to, String orderBy)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        // Get current user (teacher/admin) id to get the according subject list
        int teacherId;
        if (currentSession.getAttribute("isAdmin") != null) {
            teacherId = -1;
        } else {
            teacherId = ((User) currentSession.getAttribute("user")).getId();
        }

        System.out.println(keyword);
        System.out.println(orderBy);
        System.out.println(teacherId);

        ArrayList<ArrayList<String>> registrationList = userCourseService.getCourseRegistations(keyword, from, to, orderBy, teacherId);
        currentSession.setAttribute("registrationList", registrationList);

        // Send data to the list page
        int page = processPageParameter(request, response, registrationList.size());
        ArrayList<ArrayList<String>> pageItems = getItemInPage(registrationList, page);
        if (pageItems != null) {
            request.setAttribute("pageItems", pageItems);
            request.getRequestDispatcher("/auth/teacher/registration/list.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
        }
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
            e.printStackTrace();
        }

        return page;
    }

    private ArrayList<ArrayList<String>> getItemInPage(ArrayList<ArrayList<String>> registrationList, int page) {
        if (page > ((registrationList.size() / 5) + 1)) {
            return null;
        }
        int startItem = (page - 1) * itemPerPage;
        int endItem = (startItem + itemPerPage) > registrationList.size() ? registrationList.size() : startItem + itemPerPage;

        ArrayList<ArrayList<String>> subjectInPage = new ArrayList<>();
        for (int i = startItem; i < endItem; i++) {
            subjectInPage.add(registrationList.get(i));
        }

        return subjectInPage;
    }

    private void getItemInPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        int page = Integer.parseInt(request.getParameter("page"));

        ArrayList<ArrayList<String>> pageItems = getItemInPage((ArrayList<ArrayList<String>>) currentSession.getAttribute("registrationList"), page);
        if (pageItems != null) {
            request.setAttribute("pageItems", pageItems);
            request.getRequestDispatcher("/auth/teacher/registration/list.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
        }
    }

}

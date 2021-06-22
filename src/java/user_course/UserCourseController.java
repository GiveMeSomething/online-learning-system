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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import common.entities.User;
import java.util.List;



public class UserCourseController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
//        if (u == null) {
//            out.println("You must login first to view registation!");
//            return;
//        }
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
            default:
                listCourseRegistation(request, response, u);
                break;
        }
    }

    private void updateStatus(HttpServletRequest request, HttpServletResponse response, User u)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        UserCourseService courseService = new UserCourseService();
      int userId = u.getId();
    //    int userId = 1;
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int status = 0;
        courseService.updateStatus(userId, courseId, status);
        response.sendRedirect(request.getContextPath()+ "/auth/user/UserCourse?operation=");
    }

    private void searchCourseByTitle(HttpServletRequest request, HttpServletResponse response, User u)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        UserCourseService courseService = new UserCourseService();
        String title = request.getParameter("title");
        List<CourseRegistation> listC = courseService.searchCourseByTitle(u.getId(), title);
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

        UserCourseService courseService = new UserCourseService();
        String categoryId = request.getParameter("category");
        List<CourseRegistation> listC = courseService.searchCourseByCategory(u.getId(), Integer.parseInt(categoryId));
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
        UserCourseService courseService = new UserCourseService();

        String index = request.getParameter("page");
        if (index == null || index.equals("")) {
            index = "1";
        }
        int endPage = (courseService.countCourseUserRegis() / 5) + (courseService.countCourseUserRegis() % 5 == 0 ? 0 : 1);
        request.setAttribute("endPage", endPage);
        /*truyền cái u.getId() vào số 1 ở đây*/
        List<CourseRegistation> listC = courseService.getCourseUserRegister(u.getId(), Integer.parseInt(index));
        request.setAttribute("listC", listC);
        request.setAttribute("index", index);
        request.getRequestDispatcher("/auth/user/MyRegistation.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import common.entities.Account;
import auth.AuthService;
import common.entities.User;
import user.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ControllerServlet", urlPatterns = {"/"})
public class AdminController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/search":
                searchUser(request, response);
                break;
            case "/filter":
                filterUser(request, response);
                break;
            case "/viewuser":
                viewUser(request, response);
                break;
            case "/edituser":
                editUser(request, response);
                break;
            case "/updateuser":
                updateUser(request, response);
                break;
            case "/adduser":
                insertUser(request, response);
                break;
            case "/updaterole":
                updateRole(request, response);
                break;

            default:
                filterUser(request, response);
                break;
        }
    }

    public void updateRole(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService dao = new UserService();
        int roleId;
        String roleName = request.getParameter("role");
        if (roleName.equals("ADMIN")) {
            roleId = 0;
        } else if (roleName.equals("TEACHER")) {
            roleId = 1;
        } else {
            roleId = 2;
        }
        String email = request.getParameter("mailne");
        int status;
        String sttName = request.getParameter("status");
        if (sttName.equals("ACTIVE")) {
            status = 1;
        } else {
            status = 0;
        }
        dao.updateRole(roleId, email);
        dao.updateStatus(status, email);
        response.sendRedirect("");

    }

    public void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService dao = new UserService();
        String image = request.getParameter("image");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String mobile = request.getParameter("mobile");
        boolean gender = request.getParameter("gender").equals("MALE");
        String statusName = request.getParameter("status");
        int status;
        if (statusName.equalsIgnoreCase("ACTIVE")) {
            status = 1;
        } else {
            status = 0;
        }
        String roleName = request.getParameter("role");
        int roleId;
        if (roleName.equals("STUDENT")) {
            roleId = 2;
        } else {
            roleId = 1;
        }
        int id = dao.getNewId();
        dao.insertAccount(email, password, roleId);
        dao.insertUser(id, image, fullname, gender, email, address, status, mobile);
        response.sendRedirect("");

    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService dao = new UserService();
        String img = request.getParameter("image");
        int uid = Integer.parseInt(request.getParameter("uid"));
        String fullname = request.getParameter("fullname");
        String address = request.getParameter("address");
        String mobile = request.getParameter("mobile");
        boolean gender = request.getParameter("gender").equals("male");
        dao.updateUserInformation(img, uid, fullname, gender, address, mobile);
        response.sendRedirect("");

    }

    public void editUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService dao = new UserService();
        int uid = Integer.parseInt(request.getParameter("uid"));
        request.setAttribute("user", dao.getUserById(uid));
        request.getRequestDispatcher("auth/admin/edituser.jsp").forward(request, response);

    }

    public void viewUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService dao = new UserService();
        int uid = Integer.parseInt(request.getParameter("uid"));
        AuthService authService = new AuthService();
        User user = dao.getUserById(uid);
        Account account = authService.getAccount(user.getEmail());

        request.setAttribute("account", account);
        request.setAttribute("user", dao.getUserById(uid));
        request.getRequestDispatcher("auth/admin/userdetail.jsp").forward(request, response);

    }

    public void filterUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String[] gender = request.getParameterValues("gender");
        String[] role = request.getParameterValues("role");
        String[] status = request.getParameterValues("status");
        String type = request.getParameter("sorttype");
        if (type == "" || type == null) {
            type = "1";
        }
        String sql = "select * from db_ite1.user join account on user.email = account.user_email where 1=1 ";
        String sqlCount = "select count(*) from db_ite1.user join account on user.email = account.user_email where 1=1 ";
        if (gender != null) {
            if (gender.length == 1) {
                sql += " and (gender = " + gender[0];
                sql += ")";
                sqlCount += " and (gender = " + gender[0];
                sqlCount += ")";
            } else {
                sql += " and (gender = " + gender[0];
                sqlCount += " and (gender = " + gender[0];
                for (int i = 1; i < gender.length; i++) {
                    sql += " or gender = " + gender[i];
                    sqlCount += " or gender = " + gender[i];
                }
                sql += ")";
                sqlCount += ")";
            }
        }
        if (role != null) {
            if (role.length == 1) {
                sql += " and  (role_id = " + role[0];
                sql += ")";
                sqlCount += " and  (role_id = " + role[0];
                sqlCount += ")";
            } else {
                sql += " and  (role_id = " + role[0];
                sqlCount += " and  (role_id = " + role[0];
                for (int i = 1; i < role.length; i++) {
                    sql += " or role_id = " + role[i];
                    sqlCount += " or role_id = " + role[i];
                }
                sql += ")";
                sqlCount += ")";
            }
        }
        if (status != null) {
            if (status.length == 1) {
                sql += " and (status_id = " + status[0];
                sql += ")";
                sqlCount += " and (status_id = " + status[0];
                sqlCount += ")";
            } else {
                sql += " and (status_id = " + status[0];
                sqlCount += " and (status_id = " + status[0];
                for (int i = 1; i < status.length; i++) {
                    sql += " or status_id = " + status[i];
                    sqlCount += " or status_id = " + status[i];
                }
                sql += ")";
                sqlCount += ")";
            }
        }
        if (type.equals("2")) {
            sql += " order by id desc ";
        }
        UserService dao = new UserService();
        sql += "LIMIT 5 OFFSET ?";
        String index = request.getParameter("page");
        if (index == null) {
            index = "1";
        }
        int endPage = dao.countSearchUserByField(sqlCount) / 5 + (dao.countSearchUserByField(sqlCount) % 5 == 0 ? 0 : 1);
        request.setAttribute("endPage", endPage);
        request.setAttribute("page", index);
        if (type.equals("1")) {
            request.setAttribute("type", 1);
        }
        List<User> list = dao.searchUserByField(sql, Integer.parseInt(index));
        request.setAttribute("UserList", list);
        if (gender != null) {
            for (String string : gender) {
                request.setAttribute(String.valueOf("a" + string), "checked");
            }
        }
        if (role != null) {
            for (String string : role) {
                request.setAttribute(String.valueOf("b" + string), "checked");
            }
        }
        if (status != null) {
            for (String string : status) {
                request.setAttribute(String.valueOf("c" + string), "checked");
            }
        }
        request.getRequestDispatcher("auth/admin/userlist.jsp").forward(request, response);

    }

    public void getAllUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService dao = new UserService();
        String index = request.getParameter("page");
        if (index == null) {
            index = "1";
        }
        int endPage = dao.countTotalUser() / 5 + (dao.countTotalUser() % 5 == 0 ? 0 : 1);
        request.setAttribute("endPage", endPage);
        request.setAttribute("page", index);
        request.setAttribute("UserList", dao.pagingUser(Integer.parseInt(index)));
        request.getRequestDispatcher("auth/admin/userlist.jsp").forward(request, response);

    }

    public void searchUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService dao = new UserService();
        request.setAttribute("UserList", dao.searchUser(request.getParameter("searchtxt")));
        request.getRequestDispatcher("auth/admin/userlist.jsp").forward(request, response);

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
        processRequest(request, response);
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

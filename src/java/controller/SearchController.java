/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchController", urlPatterns = {"/searchby"})
public class SearchController extends HttpServlet {

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
            out.println("<title>Servlet SearchController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchController at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        try {
            PrintWriter out = response.getWriter();
            String[] gender = request.getParameterValues("gender");
            String[] role = request.getParameterValues("role");
            String[] status = request.getParameterValues("status");
            String sql = "select * from db_ite1.user where 1=1 ";
            if (gender != null) {
                if (gender.length == 1) {
                    sql += " and (gender = " + gender[0];
                    sql += ")";
                } else {
                    sql += " and (gender = " + gender[0];
                    for (int i = 1; i < gender.length; i++) {
                        sql += " or gender = " + gender[i];
                    }
                    sql += ")";
                }
            }
            if (role != null) {
                if (role.length == 1) {
                    sql += " and  (roleid = " + role[0];
                    sql += ")";
                } else {
                    sql += " and  (roleid = " + role[0];
                    for (int i = 1; i < role.length; i++) {
                        sql += " or roleid = " + role[i];
                    }
                    sql += ")";
                }
            }
            if (status != null) {
                if (status.length == 1) {
                    sql += " and (statusid = " + status[0];
                    sql += ")";
                } else {
                    sql += " and (statusid = " + status[0];
                    for (int i = 1; i < status.length; i++) {
                        sql += " or statusid = " + status[i];
                    }
                    sql += ")";
                }
            }
            DAO dao = new DAO();
            request.setAttribute("UserList", dao.searchUserByField(sql));
            if (gender != null) {
                for (String string : gender) {
                    request.setAttribute(String.valueOf("dcm" + string), "checked");
                }
            }
            if (role != null) {
                for (String string : role) {
                    request.setAttribute(String.valueOf("clm" + string), "checked");
                }
            }
            if (status != null) {
                for (String string : status) {
                    request.setAttribute(String.valueOf("vkl" + string), "checked");
                }
            }
            request.getRequestDispatcher("userlist.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

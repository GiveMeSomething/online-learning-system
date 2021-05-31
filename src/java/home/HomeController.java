/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import entities.Course;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Home_Controller", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        private HomeService homeService;

    @Override
    public void init() throws ServletException {
        homeService = new HomeService();
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       List<Course> itCourse =  homeService.getITCourse();
       List<Course> bizCourse =  homeService.getBusinessCourse();
       List<Course> marCourse =  homeService.getMarketingCourse();
       List<Course> aiCourse =  homeService.getAICourse();
       List<Course> iaCourse =  homeService.getIACourse();
       List<Course> langCourse =  homeService.getLanguageCourse();
       List<Course> studentsViewCourse =  homeService.getStudentAreViewingCourse();

        request.setAttribute("itCourse", itCourse);
        request.setAttribute("bizCourse", bizCourse);
        request.setAttribute("marCourse", marCourse);
        request.setAttribute("aiCourse", aiCourse);
        request.setAttribute("iaCourse", iaCourse);
        request.setAttribute("langCourse", langCourse);
        request.setAttribute("studentsViewCourse", studentsViewCourse);
        request.getRequestDispatcher("home.jsp").forward(request, response);
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

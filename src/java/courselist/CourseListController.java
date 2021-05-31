/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courselist;

import entities.Category;
import entities.Course;
import home.HomeService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nguyen Khanh Toan
 */
@WebServlet(name = "CourseListController", urlPatterns = {"/CourseListController"})
public class CourseListController extends HttpServlet {

    private CourseListService courseListService;

    @Override
    public void init() throws ServletException {
        courseListService = new CourseListService();
    }

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
            out.println("<title>Servlet CourseListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseListController at " + request.getContextPath() + "</h1>");
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
        int cateID = Integer.parseInt(request.getParameter("cID"));
        HttpSession ses = request.getSession();
        ses.setAttribute("cateID",cateID);
        List<Course> course = courseListService.getCourseByCateID(cateID);
        List<Course> courseFeature = courseListService.courseFeature(cateID);
        String searchName = request.getParameter("searchCourse");
        int id = 0;
        for(int i = 0; i < courseFeature.size();i++){
            id = courseFeature.get(0).getId();
        }
        request.setAttribute("course", course);
        switch (cateID) {
            case 1: {
                request.setAttribute("title", "Software Enginneering");
                break;
            }
            case 2: {
                request.setAttribute("title", "Economy");
                break;
            }
            case 3: {
                request.setAttribute("title", "Digital Marketing");
                break;
            }
            case 4: {
                request.setAttribute("title", "Artificial Intelligence");
                break;
            }
            case 5: {
                request.setAttribute("title", "Information Assurance");
                break;
            }
            case 6: {
                request.setAttribute("title", "Language");
                break;
            }

        }

        //MỚI THÊM VÀO
        int total = courseListService.countingCourseList(cateID);
        int endPage = 0;
        if (total % 8 == 0) {
            endPage = courseListService.countingCourseList(cateID) / 8;
        } else {
            endPage = (courseListService.countingCourseList(cateID) / 8) + 1;
        }

        String index = request.getParameter("index");
        if (index == null) {
            index = "1";
        }
        int indexPage = Integer.parseInt(index);
        List<Course> listCoursePaging = courseListService.pagingCourseList(cateID, indexPage);
        HomeService homeService = new HomeService();
        List<Category> listC = homeService.getAllCategory();
        request.setAttribute("listC", listC);
        request.setAttribute("tag", index);
        request.setAttribute("course", listCoursePaging);
        request.setAttribute("end", endPage);
        request.setAttribute("cateID", cateID);
        request.setAttribute("courseFeature", courseFeature);
        HttpSession session = request.getSession();
        session.removeAttribute("searchName");
//        request.setAttribute("course", courseSearched);
        request.setAttribute("id", id);
        request.getRequestDispatcher("courselist.jsp").forward(request, response);
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

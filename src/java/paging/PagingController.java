/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paging;

import entities.Category;
import entities.Course;
import home.HomeService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nguyen Khanh Toan
 */
@WebServlet(name = "PagingController", urlPatterns = {"/paging"})
public class PagingController extends HttpServlet {

    private PagingService pagingService;

    @Override
    public void init() throws ServletException {
        pagingService = new PagingService();
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
        String index = request.getParameter("index");
        if (index == null) {
            index = "1";
        }
        int indexPage = Integer.parseInt(index);
        int cateID = Integer.parseInt(request.getParameter("cID"));
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
        String price = request.getParameter("price");
//        if(price == null){
//            price = "";
//        }
        String searchName = request.getParameter("searchName");
        if (searchName == null) {
            searchName = "";
        }
        List<Course> listCoursePaging = pagingService.pagingCourseList(cateID, searchName, indexPage,price);
        int total = pagingService.countingCourseList(cateID,searchName);
        int endPage = 0;
        if (total % 8 == 0) {
            endPage = pagingService.countingCourseList(cateID,searchName) / 8;
        } else {
            endPage = (pagingService.countingCourseList(cateID,searchName) / 8) + 1;
        }
        List<Course> courseFeature = pagingService.courseFeature(cateID);
        int id = 0;
        for (int i = 0; i < courseFeature.size(); i++) {
            id = courseFeature.get(0).getId();
        }
        HomeService homeService = new HomeService();
        List<Category> listC = homeService.getAllCategory();
        request.setAttribute("listC", listC);
        request.setAttribute("courseFeature", courseFeature);
        request.setAttribute("id", id);
        request.setAttribute("tag", index);
        request.setAttribute("course", listCoursePaging);
        
        request.setAttribute("end", endPage);
        request.setAttribute("cateID", cateID);
        request.getRequestDispatcher("courselist.jsp").forward(request, response);
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

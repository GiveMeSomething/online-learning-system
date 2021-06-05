/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import common.entities.Category;
import common.entities.Course;
import home.HomeService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.enterprise.context.SessionScoped;
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
@WebServlet(name = "SearchController", urlPatterns = {"/searchController"})
public class SearchController extends HttpServlet {

    private SearchService searchService;
    private HomeService homeService;

    @Override
    public void init() throws ServletException {
        searchService = new SearchService();
        homeService = new HomeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        int cID = Integer.parseInt(ses.getAttribute("cateID")+"");
        response.sendRedirect("CourseListController?cID="+cID);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cateID = Integer.parseInt(request.getParameter("cID"));
        String searchName = request.getParameter("searchCourse");
        HttpSession ses = request.getSession();
        ses.setAttribute("searchName", searchName);
        int total = searchService.countingCourseListSearch(cateID,searchName);
        int endPage = 0;
        if (total % 8 == 0) {
            endPage = searchService.countingCourseListSearch(cateID,searchName) / 8;
        } else {
            endPage = (searchService.countingCourseListSearch(cateID,searchName) / 8) + 1;
        }
        
        List<Course> list = searchService.searchCourse(cateID,searchName);
        List<Course> courseFeature = searchService.getCourseFeature(cateID);
        List<Category> listC = homeService.getAllCategory();
        request.setAttribute("listC", listC);
        int id = 0;
        for (int i = 0; i < courseFeature.size(); i++) {
            id = courseFeature.get(0).getId();
        }
        request.setAttribute("id", id);
        request.setAttribute("end", endPage);
        request.setAttribute("tag",1);
        request.setAttribute("course", list);
        request.setAttribute("courseFeature", courseFeature);
        request.getRequestDispatcher("courselist.jsp").forward(request, response);
    }

}

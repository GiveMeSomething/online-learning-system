/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paging;

import common.entities.Category;
import common.entities.Course;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        String alpha = request.getParameter("alpha");
//        if(price == null){
//            price = "";
//        }
        String searchName = request.getParameter("searchName");
        if (searchName == null) {
            searchName = "";
        }
        List<Course> listCoursePaging = pagingService.pagingCourseList(cateID, searchName, indexPage,price,alpha);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }
}

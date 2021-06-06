/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course;

import common.entities.Category;
import common.entities.Course;
import java.io.IOException;
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
@WebServlet(name = "CourseController", urlPatterns = {"/course"})
public class CourseController extends HttpServlet {

    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
    }
    
    private void getTitle(HttpServletRequest request, HttpServletResponse response,int categoryId)
            throws ServletException, IOException{
         switch (categoryId) {
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
    }
    
    private int getTotalPage(HttpServletRequest request, HttpServletResponse response,int categoryId)
            throws ServletException, IOException{
        int total = courseService.countingCourseList(categoryId);
        int endPage = 0;
        if (total % 8 == 0) {
            endPage = courseService.countingCourseList(categoryId) / 8;
        } else {
            endPage = (courseService.countingCourseList(categoryId) / 8) + 1;
        }
        return endPage;
    }
    
    private int getTotalPageSearch(HttpServletRequest request, HttpServletResponse response,int categoryId,String searchName)
            throws ServletException, IOException{
        int total = courseService.countingCourseListSearch(categoryId, searchName);
        int endPage = 0;
        if (total % 8 == 0) {
            endPage = courseService.countingCourseListSearch(categoryId, searchName) / 8;
        } else {
            endPage = (courseService.countingCourseListSearch(categoryId, searchName) / 8) + 1;
        }
        return endPage;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("cID"));
        HttpSession session = request.getSession();
        session.setAttribute("categoryId", categoryId);
        List<Course> courseFeature = courseService.getCourseFeature(categoryId);
        String searchName = request.getParameter("searchName");
        int id = 0;
        for (int i = 0; i < courseFeature.size(); i++) {
            id = courseFeature.get(0).getId();
        }
        //Get title corresponding to category id
        getTitle(request, response, categoryId);
        
        String index = request.getParameter("index");
        if (index == null) {
            index = "1";
        }
        int indexPage = Integer.parseInt(index);

        List<Category> listC = courseService.getAllCategory();
        //FILTER PRICE,ALPHA
        String price = request.getParameter("price");
        String alpha = request.getParameter("alpha");
        if (price != null && price != "") {
            List<Course> listCoursePagingDetail = courseService.pagingCourseList(categoryId, searchName, indexPage, price, alpha);
            request.setAttribute("course", listCoursePagingDetail);
        } else if (alpha != null && alpha != "") {
            List<Course> listAlpha = courseService.sortCourseAlpha(categoryId, alpha);
            List<Course> listCoursePagingDetail = courseService.pagingCourseList(categoryId, searchName, indexPage, price, alpha);
            request.setAttribute("course", listCoursePagingDetail);
        } else {
            if (price == null && alpha == null) {
                List<Course> listCoursePaging = courseService.pagingCourseList(categoryId, indexPage);
                request.setAttribute("course", listCoursePaging);
            } else {
                List<Course> listCoursePagingDetail = courseService.pagingCourseList(categoryId, searchName, indexPage, price, alpha);
                request.setAttribute("course", listCoursePagingDetail);
            }

        }
        session.setAttribute("price", price);
        session.setAttribute("alpha", alpha);
        
        request.setAttribute("listC", listC);
        request.setAttribute("tag", index);
        request.setAttribute("price", price);
        request.setAttribute("end", getTotalPage(request, response, categoryId));
        request.setAttribute("cateID", categoryId);
        request.setAttribute("courseFeature", courseFeature);
        session.removeAttribute("searchName");
        request.setAttribute("id", id);
        request.getRequestDispatcher("nauth/course/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("cID"));
        String searchName = request.getParameter("searchCourse");
        HttpSession ses = request.getSession();
        ses.setAttribute("searchName", searchName);
        String index = request.getParameter("index");
        if (index == null) {
            index = "1";
        }
        int indexPage = Integer.parseInt(index);
        
        String price = request.getParameter("price");
        String alpha = request.getParameter("alpha");
        List<Course> list = courseService.searchCourse(categoryId, searchName);
        List<Course> listCoursePagingDetail = courseService.pagingCourseList(categoryId, searchName, indexPage, price, alpha);
        List<Course> courseFeature = courseService.getCourseFeature(categoryId);
        List<Category> listC = courseService.getAllCategory();
        request.setAttribute("listC", listC);
        int id = 0;
        for (int i = 0; i < courseFeature.size(); i++) {
            id = courseFeature.get(0).getId();
        }
        request.setAttribute("id", id);
        request.setAttribute("end", getTotalPageSearch(request, response, categoryId, searchName));
        request.setAttribute("tag", 1);
        request.setAttribute("course", list);
        request.setAttribute("courseFeature", courseFeature);
        request.getRequestDispatcher("nauth/course/list.jsp").forward(request, response);
    }
}

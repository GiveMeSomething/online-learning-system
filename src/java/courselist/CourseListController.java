/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courselist;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        int cateID = Integer.parseInt(request.getParameter("cID"));
//        HttpSession ses = request.getSession();
//        ses.setAttribute("cateID", cateID);
//        List<Course> course = courseListService.getCourseByCateID(cateID);
//        List<Course> courseFeature = courseListService.getCourseFeature(cateID);
//        String searchName = request.getParameter("searchCourse");
//        int id = 0;
//        for (int i = 0; i < courseFeature.size(); i++) {
//            id = courseFeature.get(0).getId();
//        }
//        request.setAttribute("course", course); //3
//        switch (cateID) {
//            case 1: {
//                request.setAttribute("title", "Software Enginneering");
//                break;
//            }
//            case 2: {
//                request.setAttribute("title", "Economy");
//                break;
//            }
//            case 3: {
//                request.setAttribute("title", "Digital Marketing");
//                break;
//            }
//            case 4: {
//                request.setAttribute("title", "Artificial Intelligence");
//                break;
//            }
//            case 5: {
//                request.setAttribute("title", "Information Assurance");
//                break;
//            }
//            case 6: {
//                request.setAttribute("title", "Language");
//                break;
//            }
//
//        }
//
//        //MỚI THÊM VÀO
//        int total = courseListService.countingCourseList(cateID);
//        int endPage = 0;
//        if (total % 8 == 0) {
//            endPage = courseListService.countingCourseList(cateID) / 8;
//        } else {
//            endPage = (courseListService.countingCourseList(cateID) / 8) + 1;
//        }
//
//        String index = request.getParameter("index");
//        if (index == null) {
//            index = "1";
//        }
//        int indexPage = Integer.parseInt(index);
//        List<Course> listCoursePaging = courseListService.pagingCourseList(cateID, indexPage);
//        List<Category> listC = homeService.getAllCategory();
//        //FILTER PRICE,ALPHA
//
//        String price = request.getParameter("price");
//        String alpha = request.getParameter("alpha");
//        if (price != null) {
//            List<Course> listPrice = courseListService.sortCoursePrice(cateID, price);
//            request.setAttribute("course", listPrice);
//        } else if (alpha != null) {
//            List<Course> listAlpha = courseListService.sortCourseAlpha(cateID, alpha);
//            request.setAttribute("course", listAlpha);
//        } else {
//            request.setAttribute("course", listCoursePaging);
//        }
//
//        HttpSession currentSession = request.getSession();
//        currentSession.setAttribute("price", price);
//        currentSession.setAttribute("alpha", alpha);
//        request.setAttribute("listC", listC);
//        request.setAttribute("tag", index);
//        request.setAttribute("price", price);
//        request.setAttribute("end", endPage);
//        request.setAttribute("cateID", cateID);
//        request.setAttribute("courseFeature", courseFeature);
//        currentSession.removeAttribute("searchName");
//        request.setAttribute("id", id);
//        request.getRequestDispatcher("nauth/course/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}

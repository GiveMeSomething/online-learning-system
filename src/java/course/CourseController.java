package course;

import common.entities.Course;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.entities.Category;
import common.entities.PricePackage;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String courseId = request.getParameter("courseId");
        String categoryId = request.getParameter("cID");
        if (courseId != null) {
            Course courseDetail = courseService.getCourse(Integer.parseInt(courseId));
            List<Course> siderCourse = courseService.getSiderCourseDetail();
            List<Category> categoryList = courseService.getAllCategory();
            ArrayList<PricePackage> coursePackages = courseService.getCoursePackage(courseDetail.getId());

            request.setAttribute("detail", courseDetail);
            request.setAttribute("siderCourse", siderCourse);
            request.setAttribute("listC", categoryList);
            request.setAttribute("coursePackages", coursePackages);

            request.getRequestDispatcher("nauth/course/detail.jsp").forward(request, response);
        } else if (categoryId != null) {
            int categoryIndicator = Integer.parseInt(categoryId);
            HttpSession session = request.getSession();
            session.setAttribute("categoryId", categoryId);
            List<Course> courseFeature = courseService.getCourseFeature(categoryIndicator);
            String searchName = request.getParameter("searchName");
            int idFeature = 0;
            for (int i = 0; i < courseFeature.size(); i++) {
                idFeature = courseFeature.get(0).getId();
            }
            //Get title corresponding to category id
            getTitle(request, response, categoryIndicator);

            String index = request.getParameter("index");
            if (index == null) {
                index = "1";
            }
            int indexPage = Integer.parseInt(index);

            List<Category> categoryList = courseService.getAllCategory();
            //FILTER PRICE,ALPHA
            String price = request.getParameter("price");
            String alpha = request.getParameter("alpha");
            if (price != null && alpha != null && price != "" && alpha != "") {
                List<Course> listCoursePagingDetail = courseService.pagingCourseList(categoryIndicator, searchName, indexPage, price, alpha);
                request.setAttribute("course", listCoursePagingDetail);
            } else if (price != null && price != "") {
                List<Course> listCoursePagingDetail = courseService.pagingCourseList(categoryIndicator, searchName, indexPage, price, alpha);
                request.setAttribute("course", listCoursePagingDetail);
            } else if (alpha != null && alpha != "") {
                List<Course> listAlpha = courseService.sortCourseAlpha(categoryIndicator, alpha);
                List<Course> listCoursePagingDetail = courseService.pagingCourseList(categoryIndicator, searchName, indexPage, price, alpha);
                request.setAttribute("course", listCoursePagingDetail);
            } else {
                if (price == null && alpha == null) {
                    List<Course> listCoursePaging = courseService.pagingCourseList(categoryIndicator, indexPage);
                    request.setAttribute("course", listCoursePaging);
                } else {
                    List<Course> listCoursePagingDetail = courseService.pagingCourseList(categoryIndicator, searchName, indexPage, price, alpha);
                    request.setAttribute("course", listCoursePagingDetail);
                }

            }
            session.setAttribute("price", price);
            session.setAttribute("alpha", alpha);
            request.setAttribute("listC", categoryList);
            request.setAttribute("tag", index);
            request.setAttribute("price", price);
            request.setAttribute("end", getTotalPage(request, response, categoryIndicator));
            request.setAttribute("cateID", categoryId);
            request.setAttribute("courseFeature", courseFeature);
            session.removeAttribute("searchName");
            request.setAttribute("id", idFeature);
            request.getRequestDispatcher("nauth/course/list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");
        if (operation.equals("REGISTER")) {

        }

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

    private void getTitle(HttpServletRequest request, HttpServletResponse response, int categoryId)
            throws ServletException, IOException {
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

    private int getTotalPage(HttpServletRequest request, HttpServletResponse response, int categoryId)
            throws ServletException, IOException {
        int total = courseService.countingCourseList(categoryId);
        int endPage = 0;
        if (total % 8 == 0) {
            endPage = courseService.countingCourseList(categoryId) / 8;
        } else {
            endPage = (courseService.countingCourseList(categoryId) / 8) + 1;
        }
        return endPage;
    }

    private int getTotalPageSearch(HttpServletRequest request, HttpServletResponse response, int categoryId, String searchName)
            throws ServletException, IOException {
        int total = courseService.countingCourseListSearch(categoryId, searchName);
        int endPage = 0;
        if (total % 8 == 0) {
            endPage = courseService.countingCourseListSearch(categoryId, searchName) / 8;
        } else {
            endPage = (courseService.countingCourseListSearch(categoryId, searchName) / 8) + 1;
        }
        return endPage;

    }

}

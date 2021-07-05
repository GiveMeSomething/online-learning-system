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
import common.entities.User;
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
        String operation = request.getParameter("operation");
        if (courseId != null) {
            Course courseDetail = courseService.getCourse(Integer.parseInt(courseId));
            List<Course> siderCourse = courseService.getSiderCourseDetail();
            List<Category> categoryList = courseService.getAllCategory();
            ArrayList<PricePackage> coursePackages = courseService.getCoursePackage(courseDetail.getId());

            request.setAttribute("detail", courseDetail);
            request.setAttribute("siderCourse", siderCourse);
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("coursePackages", coursePackages);

            request.getRequestDispatcher("nauth/course/detail.jsp").forward(request, response);
        } else if (categoryId != null) {
            int categoryIndicator = Integer.parseInt(categoryId);
            HttpSession session = request.getSession();
            session.setAttribute("categoryId", categoryId);
            List<Course> courseFeature = courseService.getCourseFeature(categoryIndicator);
            String searchName = request.getParameter("searchName");
            //assign first slider image active to display in UI
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
            //show the category list dropdown
            List<Category> categoryList = courseService.getAllCategory();
            //filter price,alpha
            String price = request.getParameter("price");
            String alpha = request.getParameter("alpha");
            //handle filter
            handleFilterPriceAlpha(request, response, categoryIndicator, searchName, indexPage, price, alpha);
            session.setAttribute("price", price);
            session.setAttribute("alpha", alpha);
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("tag", index);
            request.setAttribute("price", price);
            if (searchName != null) {
                request.setAttribute("end", getTotalPageSearch(request, response, categoryIndicator, searchName));
            } else {
                request.setAttribute("end", getTotalPage(request, response, categoryIndicator));
            }
            request.setAttribute("categoryId", categoryId);
            request.setAttribute("courseFeature", courseFeature);
            session.removeAttribute("searchName");
            request.setAttribute("id", idFeature);
            request.getRequestDispatcher("nauth/course/list.jsp").forward(request, response);
        }else if(operation.equals("VIEWMYCOURSE")){
            getMyCourse(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");
        if (operation.equals("REGISTER")) {

        } else if (operation.equals("SEARCHCOURSE")) {
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
            List<Course> courseFeature = courseService.getCourseFeature(categoryId);
            List<Category> categoryList = courseService.getAllCategory();
            request.setAttribute("categoryList", categoryList);
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

    private void handleFilterPriceAlpha(HttpServletRequest request,
            HttpServletResponse response, int categoryIndicator, String searchName,
            int indexPage, String price, String alpha)
            throws ServletException, IOException {
        //When user click both filter by price and alpha
        if (price != null && alpha != null && price != "" && alpha != "") {
            List<Course> listCoursePagingDetail = courseService.pagingCourseList(categoryIndicator, searchName, indexPage, price, alpha);
            request.setAttribute("course", listCoursePagingDetail);
        } else if (price != null && price != "") { //When user click filter by price
            List<Course> listCoursePagingDetail = courseService.pagingCourseList(categoryIndicator, searchName, indexPage, price, alpha);
            request.setAttribute("course", listCoursePagingDetail);
        } else if (alpha != null && alpha != "") { //When user click filter by alpha
            List<Course> listCoursePagingDetail = courseService.pagingCourseList(categoryIndicator, searchName, indexPage, price, alpha);
            request.setAttribute("course", listCoursePagingDetail);
        } else { //When user do not click fiter radio button
            if (price == null && alpha == null) {
                List<Course> listCoursePaging = courseService.pagingCourseList(categoryIndicator, indexPage);
                request.setAttribute("course", listCoursePaging);
            } else { //When paging search
                List<Course> listCoursePagingDetail = courseService.pagingCourseList(categoryIndicator, searchName, indexPage, price, alpha);
                request.setAttribute("course", listCoursePagingDetail);
            }

        }
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

    private void getMyCourse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        // Get current user (teacher/admin) id to get the according subject list
        int userId;
        if (currentSession.getAttribute("isAdmin") == null) {
            userId = -1;
        } else {
            userId = ((User) currentSession.getAttribute("user")).getId();
        }
        List<Course> myCourse = courseService.getMyCourse(userId);
        List<Category> categoryList = courseService.getAllCategory();
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("myCourse", myCourse);
        System.out.println(myCourse);
        request.getRequestDispatcher("/auth/user/course/my-course.jsp").forward(request, response);
    }
}

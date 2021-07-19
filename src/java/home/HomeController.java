/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import common.entities.Course;
import common.entities.Category;
import common.entities.Slider;
import course.CourseService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import slider.SliderService;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Home_Controller", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    private CourseService courseService;
    private SliderService sliderService;
    private final int subjectPerpage = 8;

    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
        sliderService = new SliderService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String operation = request.getParameter("operation");

        if (operation == null) {
            List<Course> itCourse = courseService.getCourses("Software Engineering");
            List<Course> bizCourse = courseService.getCourses("Economy");
            List<Course> marCourse = courseService.getCourses("Digital Marketing");
            List<Course> aiCourse = courseService.getCourses("Artificial Intelligence");
            List<Course> iaCourse = courseService.getCourses("Information Assurance");
            List<Course> langCourse = courseService.getCourses("Language");
            List<Course> studentsViewCourse = courseService.getFeaturedCourse();
            List<Slider> sliderList = sliderService.displaySliders();

            request.setAttribute("itCourse", itCourse);
            request.setAttribute("bizCourse", bizCourse);
            request.setAttribute("marCourse", marCourse);
            request.setAttribute("aiCourse", aiCourse);
            request.setAttribute("iaCourse", iaCourse);
            request.setAttribute("langCourse", langCourse);
            request.setAttribute("studentsViewCourse", studentsViewCourse);
            List<Category> categoryList = courseService.getAllCategory();
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("sliderList", sliderList);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else if (operation.equals("PAGINATION")) {
            String searchName = request.getParameter("searchCourse");
            session.setAttribute("searchName", searchName);

            int page = Integer.parseInt(request.getParameter("page"));
            List<Category> categoryList = courseService.getAllCategory();
            List<Course> pageItems = getSearchPerPage((List<Course>) session.getAttribute("course"), page);
            if (pageItems != null) {
                session.setAttribute("searchName", searchName);
                request.setAttribute("categoryList", categoryList);
                request.setAttribute("pageItems", pageItems);
                request.getRequestDispatcher("homeSearch.jsp").forward(request, response);
            } else {
                response.sendRedirect("/nauth/404.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation.equals("SEARCHCOURSE")) {
            processInputForSearch(request, response);
        }
    }

    public void processInputForSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String keyword = request.getParameter("searchCourse");
        session.setAttribute("selectedKeyword", keyword);
        

        if (keyword == null || keyword.equals("")) {
            keyword = "";
        }

        getSearchList(request, response, keyword);
    }

    public void getSearchList(HttpServletRequest request, HttpServletResponse response, String keyword)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<Course> course = courseService.searchHome(keyword);
        List<Category> categoryList = courseService.getAllCategory();

        session.setAttribute("course", course);

        if (course == null || course.size() == 0) {
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("course", course);
            request.getRequestDispatcher("homeSearch.jsp").forward(request, response);
        }
        int page = processPageParameter(request, response, course.size());
        List<Course> pageItems = getSearchPerPage(course, page);

        if (pageItems != null) {
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("pageItems", pageItems);
            request.getRequestDispatcher("homeSearch.jsp").forward(request, response);
        } else {
            response.sendRedirect("/nauth/404.jsp");
        }
    }

    private List<Course> getSearchPerPage(List<Course> course, int page) {
        if (page > ((course.size() / 8) + 1)) {
            return null;
        }
        int startSearch = (page - 1) * subjectPerpage;
        int endSearch = (startSearch + subjectPerpage) > course.size() ? course.size() : startSearch + subjectPerpage;

        List<Course> searchInPage = new ArrayList<>();
        for (int i = startSearch; i < endSearch; i++) {
            searchInPage.add(course.get(i));
        }
        return searchInPage;
    }

    private int processPageParameter(HttpServletRequest request, HttpServletResponse response, int listSize)
            throws ServletException, IOException {
        // If not yet receive page param (first time in page) change it to 1
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1 || page > ((listSize / subjectPerpage) + 1)) {
                response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " at ~96 SubjectController");
        }

        return page;
    }

}

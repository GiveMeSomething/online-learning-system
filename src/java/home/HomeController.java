/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import common.entities.Course;
import common.entities.Category;
import course.CourseService;
import java.io.IOException;
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

    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Course> itCourse = courseService.getITCourse();
        List<Course> bizCourse = courseService.getBusinessCourse();
        List<Course> marCourse = courseService.getMarketingCourse();
        List<Course> aiCourse = courseService.getAICourse();
        List<Course> iaCourse = courseService.getIACourse();
        List<Course> langCourse = courseService.getLanguageCourse();
        List<Course> studentsViewCourse = courseService.getFeaturedCourse();

        request.setAttribute("itCourse", itCourse);
        request.setAttribute("bizCourse", bizCourse);
        request.setAttribute("marCourse", marCourse);
        request.setAttribute("aiCourse", aiCourse);
        request.setAttribute("iaCourse", iaCourse);
        request.setAttribute("langCourse", langCourse);
        request.setAttribute("studentsViewCourse", studentsViewCourse);
        List<Category> listC = courseService.getAllCategory();
        request.setAttribute("listC", listC);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}

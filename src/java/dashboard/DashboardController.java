/**
 * Jun 26, 2021
 *
 * @author Hoang Tien Minh
 */
package dashboard;

import course.CourseService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import slider.SliderService;
import user_course.UserCourseService;

@WebServlet(name = "DashboardController", urlPatterns = {"/auth/admin/dashboard"})
public class DashboardController extends HttpServlet {

    private CourseService courseService;
    private SliderService sliderService;
    private UserCourseService userCourseService;

    @Override
    public void init() throws ServletException {
        // Initialize Services here
        courseService = new CourseService();
        sliderService = new SliderService();
        userCourseService = new UserCourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if (operation == null) {
            countingTotalCourse(request, response);
            countingNewCourse(request, response);
            countingTotalRegistration(request, response);
            countingCancelRegistration(request, response);
            countingSubmittedRegistration(request, response);
            countingSuccessfulRegistration(request, response);
            countingTotalSuccessfulRegistration(request, response);
            countingTotalProfit(request, response);
            countingTotalProfitByCategoryId(request, response);
            request.getRequestDispatcher("/auth/admin/dashboard.jsp").forward(request, response);
        } else {
            switch (operation) {
                default:
                    response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if (operation == null) {

        } else {
            switch (operation) {
                default:
                    response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
                    break;
            }
        }
    }

    protected void countingTotalCourse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int totalCourseBefore6Day = courseService.countTotalCourse(6);
        int totalCourseBefore5Day = courseService.countTotalCourse(5);
        int totalCourseBefore4Day = courseService.countTotalCourse(4);
        int totalCourseBefore3Day = courseService.countTotalCourse(3);
        int totalCourseBefore2Day = courseService.countTotalCourse(2);
        int totalCourseBefore1Day = courseService.countTotalCourse(1);
        int totalCourseNow = courseService.countTotalCourse(0);
        request.setAttribute("totalCourseNow", totalCourseNow);
        request.setAttribute("totalCourseBefore1Day", totalCourseBefore1Day);
        request.setAttribute("totalCourseBefore2Day", totalCourseBefore2Day);
        request.setAttribute("totalCourseBefore3Day", totalCourseBefore3Day);
        request.setAttribute("totalCourseBefore4Day", totalCourseBefore4Day);
        request.setAttribute("totalCourseBefore5Day", totalCourseBefore5Day);
        request.setAttribute("totalCourseBefore6Day", totalCourseBefore6Day);

    }

    protected void countingTotalRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int totalRegistrationBefore6Day = userCourseService.countingTotalRegistration(6);
        int totalRegistrationBefore5Day = userCourseService.countingTotalRegistration(5);
        int totalRegistrationBefore4Day = userCourseService.countingTotalRegistration(4);
        int totalRegistrationBefore3Day = userCourseService.countingTotalRegistration(3);
        int totalRegistrationBefore2Day = userCourseService.countingTotalRegistration(2);
        int totalRegistrationBefore1Day = userCourseService.countingTotalRegistration(1);
        int totalRegistrationNow = userCourseService.countingTotalRegistration(0);
        request.setAttribute("totalRegistrationNow", totalRegistrationNow);
        request.setAttribute("totalRegistrationBefore1Day", totalRegistrationBefore1Day);
        request.setAttribute("totalRegistrationBefore2Day", totalRegistrationBefore2Day);
        request.setAttribute("totalRegistrationBefore3Day", totalRegistrationBefore3Day);
        request.setAttribute("totalRegistrationBefore4Day", totalRegistrationBefore4Day);
        request.setAttribute("totalRegistrationBefore5Day", totalRegistrationBefore5Day);
        request.setAttribute("totalRegistrationBefore6Day", totalRegistrationBefore6Day);
    }

    protected void countingNewCourse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int countNewCourse = courseService.countNewCourse();
        request.setAttribute("newCourse", countNewCourse);
    }

    protected void countingCancelRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int countCancelRegistration = userCourseService.countRegistationStatus(0, 6);
        request.setAttribute("cancelRegistration", countCancelRegistration);
    }

    protected void countingSubmittedRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int countSubmittedRegistration = userCourseService.countRegistationStatus(1, 6);
        request.setAttribute("submittedRegistration", countSubmittedRegistration);
    }

    protected void countingSuccessfulRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int countSuccessfulRegistration = userCourseService.countRegistationStatus(2, 6);
        request.setAttribute("successfulRegistration", countSuccessfulRegistration);
    }
    
    protected void countingTotalSuccessfulRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int successfulRegistrationBefore6Day = userCourseService.countTotalRegistationSuccess(6);
        int successfulRegistrationBefore5Day = userCourseService.countTotalRegistationSuccess(5);
        int successfulRegistrationBefore4Day = userCourseService.countTotalRegistationSuccess(4);
        int successfulRegistrationBefore3Day = userCourseService.countTotalRegistationSuccess(3);
        int successfulRegistrationBefore2Day = userCourseService.countTotalRegistationSuccess(2);
        int successfulRegistrationBefore1Day = userCourseService.countTotalRegistationSuccess(1);
        int successfulRegistrationNow = userCourseService.countTotalRegistationSuccess(0);
    
        request.setAttribute("successNow", successfulRegistrationNow);
        request.setAttribute("successBefore1Day", successfulRegistrationBefore1Day);
        request.setAttribute("successBefore2Day", successfulRegistrationBefore2Day);
        request.setAttribute("successBefore3Day", successfulRegistrationBefore3Day);
        request.setAttribute("successBefore4Day", successfulRegistrationBefore4Day);
        request.setAttribute("successBefore5Day", successfulRegistrationBefore5Day);
        request.setAttribute("successBefore6Day", successfulRegistrationBefore6Day);
 
    }
    
    protected void countingTotalProfit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int totalProfit = userCourseService.countingTotalProfit();
    
        request.setAttribute("totalProfit", totalProfit);
 
    }
    
    protected void countingTotalProfitByCategoryId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int softwareEngineering = userCourseService.countingTotalProfitByCategoryId(1);
        int economy = userCourseService.countingTotalProfitByCategoryId(2);
        int digitalMarketing = userCourseService.countingTotalProfitByCategoryId(3);
        int artificialIntelligence = userCourseService.countingTotalProfitByCategoryId(4);
        int informationAssurance = userCourseService.countingTotalProfitByCategoryId(5);
        int language = userCourseService.countingTotalProfitByCategoryId(6);
    
        request.setAttribute("softwareEngineering", softwareEngineering);
        request.setAttribute("economy", economy);
        request.setAttribute("digitalMarketing", digitalMarketing);
        request.setAttribute("artificialIntelligence", artificialIntelligence);
        request.setAttribute("informationAssurance", informationAssurance);
        request.setAttribute("language", language);
 
    }

}

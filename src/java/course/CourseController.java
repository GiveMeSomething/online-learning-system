
package course;

import common.entities.Course;
import home.HomeService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CourseController extends HttpServlet {

    private CourseService courseDetailService;
    @Override
    public void init() throws ServletException{
        courseDetailService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("cid");
        Course courseDetail = courseDetailService.getCourse(Integer.parseInt(id));
        HomeService homeService = new HomeService();
        List<Course> siderCourse = homeService.getSiderCourseDetail();
        request.setAttribute("detail", courseDetail);
        request.setAttribute("siderDetail", siderCourse);
        request.getRequestDispatcher("courseDetail.jsp").forward(request, response);          
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }

}

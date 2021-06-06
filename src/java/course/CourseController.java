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

    private CourseService courseService;
    

    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
      
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("cid");
        Course courseDetail = courseService.getCourse(Integer.parseInt(id));
        
       
        List<Course> siderCourse = courseService.getSiderCourseDetail();
        
        request.setAttribute("detail", courseDetail);
        request.setAttribute("siderDetail", siderCourse);
        request.getRequestDispatcher("nauth/course/detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}

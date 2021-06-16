/**
 * Jun 16, 2021
 *
 * @author Vu Duy Anh
 */
package subject;
import course.CourseService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class SubjectController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        CourseService courseService = new CourseService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }
}
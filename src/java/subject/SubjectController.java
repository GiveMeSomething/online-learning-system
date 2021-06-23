/**
 * Jun 22, 2021
 *
 * @author Dinh Kong Thanh
 */
package subject;
import common.entities.Category;
import common.entities.Course;
import common.entities.User;
import course.CourseService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.UserService;
public class SubjectController extends HttpServlet {

    private UserService userService;
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation == null) {
            request.setAttribute("activeId", 1);
            String subjectId = request.getParameter("subjectId");
            Course subject = courseService.getSubject(2);
            User ownerName = userService.getUserById(subject.getOwnerId());
            List<Category> categoryList = courseService.getAllCategory();
            List<User> authorList = userService.getAuthor();
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("ownerName", ownerName);
            request.setAttribute("authorList", authorList);
            request.setAttribute("detail", subject);
                
            request.getRequestDispatcher("/auth/teacher/subject/detail.jsp").forward(request, response);
        }

    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        String subjectId = request.getParameter("subjectId");
        Course subject = courseService.getSubject(2);

        if (operation.equals("changeSubjectInformation")) {
            int id = subject.getId();
            String subjectName = request.getParameter("subjectName");
            int categoryBox = Integer.parseInt(request.getParameter("categoryBox"));
            String[] isFeatured = request.getParameterValues("featuredSubject");
            int status = Integer.parseInt(request.getParameter("status"));
            String description = request.getParameter("description");
            int courseOwner = Integer.parseInt(request.getParameter("courseOwner"));
            if (isFeatured != null && isFeatured[0] != null) {
                courseService.updateSubjectInformation(subjectName, description, courseOwner, status, categoryBox, 1, 2);
            } else {
                courseService.updateSubjectInformation(subjectName, description, courseOwner, status, categoryBox, 0, 2);
            }
            request.setAttribute("activeId", 1);
            response.sendRedirect(request.getContextPath() + "/auth/teacher/subject");
        }
    }
}
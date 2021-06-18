/**
 * Jun 15, 2021
 *
 * @author Nguyen Khanh Toan
 */
package subject;

import common.entities.Dimension;
import common.entities.DimensionType;
import course.CourseService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubjectController extends HttpServlet {

    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
    }

    protected void displayData(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Display subject dimesion detail of specified course
        int courseId = 1;
        List<Dimension> listDimension = courseService.getSubjectDimensionByCourseId(courseId);
        request.setAttribute("listDimension", listDimension);
        request.getRequestDispatcher("detail.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation == null) {
            request.setAttribute("activeId", 1);
            displayData(request, response);
        } else if (operation.equals("DELETESUBJECT")) {
            int courseIdDelete = Integer.parseInt(request.getParameter("courseId"));
            int dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
            courseService.deleteSubjectDimensionByCourseId(courseIdDelete, dimensionId);
            request.setAttribute("activeId", 2);
            displayData(request, response);
        } else if (operation.equals("GETDIMENSIONDETAIL")) {
            int dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
            Dimension dimensionDetail = courseService.getDimensionDetail(dimensionId);
            request.setAttribute("dimensionDetail", dimensionDetail);
            request.setAttribute("dimensionId", dimensionId);
            request.getRequestDispatcher("dimensionEditInfo.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int courseId = 1;
        String operation = request.getParameter("operation");
        if (operation.equals("ADDSUBJECT")) {
            String type = request.getParameter("type");
            String dimension = request.getParameter("dimension");
            int typeInt = 0;
            if (type.equals("Domain")) {
                typeInt = 1;
            } else if(type.equals("Group")) {
                typeInt = 2;
            } else {
                courseService.addDimensionType(type);
                typeInt = courseService.getDimensionTypeDetail(type).getId();
            }
            String description = request.getParameter("description");
            courseService.addDimension(typeInt, dimension, description);
            Dimension dimensionAdd = courseService.getDimensionId(dimension);
            courseService.addDimensionCourse(courseId, dimensionAdd.getId());
            request.setAttribute("activeId", 2);
            displayData(request, response);
        } else if (operation.equals("UPDATESUBJECT")) {
            int dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
            String type = request.getParameter("type");
            int typeInt = 0;
            if (type.equals("domain")) {
                typeInt = 1;
            } else if(type.equals("group")) {
                typeInt = 2;
            } else {
                typeInt = courseService.getDimensionTypeDetail(type).getId();
            }
            String dimension = request.getParameter("dimension");
            String description = request.getParameter("description");
            courseService.updateSubjectDimension(typeInt, dimension, description, dimensionId);
            request.setAttribute("activeId", 2);
            displayData(request, response);
        }
    }
}

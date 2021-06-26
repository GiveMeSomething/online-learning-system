/**
 * Jun 26, 2021
 *
 * @author Hoang Tien Minh
 */
package slider;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SliderController extends HttpServlet {

    private SliderService sliderService;

    private final int itemInPage = 5;

    @Override
    public void init() throws ServletException {
        sliderService = new SliderService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if (operation == null) {

        } else {
            switch (operation) {
                case "VIEWDETAIL":
                    processViewDetail(request, response);
                    break;
                case "PAGINATION":
                    processPagination(request, response);
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
                case "UPDATESLIDER":
                    processUpdateSlider(request, response);
                    break;
                case "FILTERSLIDER":
                    processFilterSlider(request, response);
                    break;
                case "ADDSLIDER":
                    processAddSlider(request, response);
                    break;
                case "DELETESLIDER":
                    processDeleteSlider(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
                    break;
            }
        }
    }

    private void processViewDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void processUpdateSlider(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void processPagination(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void processFilterSlider(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void processAddSlider(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void processDeleteSlider(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}

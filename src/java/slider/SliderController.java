/**
 * Jun 26, 2021
 *
 * @author Hoang Tien Minh
 */
package slider;

import common.entities.Slider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class SliderController extends HttpServlet {

    private SliderService sliderService;
    private final int itemInPage = 5;
    private static final long SerialVersionUID = 1L;
    private static final String UPLOAD_DIR = "assets/bannerImg/";

    @Override
    public void init() throws ServletException {
        sliderService = new SliderService();
    }
    
    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName = "";
        try {
            Part filePart = request.getPart("photo");
            fileName = (String) getFileName(filePart);
            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }

        } catch (Exception e) {
            fileName = "";
        }
        return fileName;
    }

    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("*****partHeader :" + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if (operation == null) {
            processViewDetail(request, response); //Khi merge code sẽ bỏ dòng này
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
                case "UPLOADIMAGE":
                    processUploadImage(request, response);
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
        HttpSession currentSession = request.getSession();
        int sliderId = 2;
        currentSession.setAttribute("sliderId", sliderId);
        Slider sliderDetail = sliderService.getSliderDetail(sliderId);
        String image = sliderDetail.getImage();
        request.setAttribute("sliderDetail", sliderDetail);
        request.setAttribute("image", image);
        request.getRequestDispatcher("/auth/admin/slider/detail.jsp").forward(request, response);
        //Sau này chỉnh lại link
    }

    private void processUpdateSlider(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();
        int sliderId = Integer.parseInt(currentSession.getAttribute("sliderId") + "");
        String image = request.getParameter("image");
        String title = request.getParameter("title");
        int statusId = Integer.parseInt(request.getParameter("status"));
        String notes = request.getParameter("notes");
        sliderService.updateSliderDetail(image, title, statusId, notes, sliderId);
        response.sendRedirect(request.getContextPath()+"/auth/admin/slider"); //Sau này chỉnh lại link
    }
    
    private void processUploadImage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();
        int sliderId = Integer.parseInt(currentSession.getAttribute("sliderId") + "");
        Slider sliderDetail = sliderService.getSliderDetail(sliderId);
        request.setAttribute("sliderDetail", sliderDetail);
        request.setAttribute("image", uploadFile(request));
        request.getRequestDispatcher("/auth/admin/slider/detail.jsp").forward(request, response);
        //Sau này chỉnh lại link
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

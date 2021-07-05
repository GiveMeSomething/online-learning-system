/**
 * Jun 26, 2021
 *
 * @author Hoang Tien Minh
 */
package slider;

import common.entities.Slider;
import common.entities.Status;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;




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
        HttpSession session = request.getSession();
        String operation = request.getParameter("operation");
        int sliderId = 1;
        if (request.getParameter("sliderId") != null) {
            sliderId = Integer.parseInt(request.getParameter("sliderId"));
            session.setAttribute("sliderId", sliderId);
        }

        if (operation == null) {
            processInputForSlider(request, response);

        } else {
            switch (operation) {
                case "VIEWDETAIL":
                    processViewDetail(request, response);
                    break;
                case "PAGINATION":
                    processPagination(request, response);
                case "DELETESLIDER":
                    processDeleteSlider(request, response, sliderId);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String operation = request.getParameter("operation");
        int sliderId = 1;
        if (request.getParameter("sliderId") != null) {
            sliderId = Integer.parseInt(request.getParameter("sliderId"));
            session.setAttribute("sliderId", sliderId);
        }

        if (operation == null) {
            processInputForSlider(request, response);
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
                default:
                    response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
                    break;
            }
        }
    }

    private void processViewDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentSession = request.getSession();
        int sliderId = Integer.parseInt(request.getParameter("sliderId"));
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
        response.sendRedirect(request.getContextPath()+"/auth/admin/slider?operation=VIEWDETAIL&&sliderId="+sliderId); //Sau này chỉnh lại link
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
        HttpSession session = request.getSession();

        int page = Integer.parseInt(request.getParameter("page"));

        List<Slider> pageItems = getSliderPerPage((List<Slider>) session.getAttribute("sliderList"), page);

        if (pageItems != null) {
            request.setAttribute("pageItems", pageItems);
            request.getRequestDispatcher("/auth/admin/slider/list.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
        }

    }

    private void processFilterSlider(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processInputForSlider(request, response);
    }

    private void processAddSlider(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String forwardTo = request.getParameter("previousPage");
//        String image = request.getParameter("image");
        String title = request.getParameter("title");
        Status status = Status.valueOf(request.getParameter("status"));
        String note = request.getParameter("note");

        InputStream inputStream = null;
        Part thumbnail = request.getPart("image");
        if (thumbnail != null) {
            // prints out some information for debugging
            System.out.println(thumbnail.getName());
            System.out.println(thumbnail.getSize());
            System.out.println(thumbnail.getContentType());

            // obtains input stream of the upload file
            inputStream = thumbnail.getInputStream();
        }

        Slider slider = new Slider(title, status, note);
        
            sliderService.addNewSlider(slider, inputStream);
            // Navigating to subject list
            response.sendRedirect(request.getContextPath() + "/auth/admin/slider");

    }

    private void processDeleteSlider(HttpServletRequest request, HttpServletResponse response, int sliderId)
            throws ServletException, IOException {
        sliderService.deleteSliderById(sliderId);
        processInputForSlider(request, response);
    }

    private void processInputForSlider(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String statusString = request.getParameter("status");
        Status status;

        if (statusString != null && !statusString.equals("")) {
            status = Status.valueOf(statusString);
        } else {
            status = null;
        }

        // Save selected value to request
        request.setAttribute("selectedStatus", statusString);
        request.setAttribute("selectedKeyword", keyword);

        if (keyword == null || keyword.equals("")) {
            keyword = "";
        }

        // We don't need to process status because null is consider as status's default value
        getSliderList(request, response, keyword, status);
    }

    public void getSliderList(HttpServletRequest request, HttpServletResponse response,
            String keyword, Status status)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<Slider> sliderList = sliderService.getSlidersList(keyword, status);
        session.setAttribute("sliderList", sliderList);

        if (sliderList == null || sliderList.size() == 0) {
            request.setAttribute("sliderList", sliderList);
            request.getRequestDispatcher("/auth/admin/slider/list.jsp").forward(request, response);
        }
        int page = processPageParameter(request, response, sliderList.size());
        List<Slider> pageItems = getSliderPerPage(sliderList, page);

        if (pageItems != null) {
            request.setAttribute("pageItems", pageItems);
            request.getRequestDispatcher("/auth/admin/slider/list.jsp").forward(request, response);
        } else {
            response.sendRedirect("/nauth/404.jsp");
        }
    }

//    private void getSliderPerPage(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//    }
    // Get the right data for the right page
    private List<Slider> getSliderPerPage(List<Slider> sliderList, int page) {
        if (page > ((sliderList.size() / 5) + 1)) {
            return null;
        }
        int startSlider = (page - 1) * itemInPage;
        int endSlider = (startSlider + itemInPage) > sliderList.size() ? sliderList.size() : startSlider + itemInPage;

        List<Slider> sliderInPage = new ArrayList<>();
        for (int i = startSlider; i < endSlider; i++) {
            sliderInPage.add(sliderList.get(i));
        }
        return sliderInPage;
    }

    private int processPageParameter(HttpServletRequest request, HttpServletResponse response, int listSize)
            throws ServletException, IOException {
        // If not yet receive page param (first time in page) change it to 1
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));

            int pageNum;
            if (listSize % itemInPage == 0) {
                pageNum = listSize / itemInPage;
            } else {
                pageNum = (listSize / itemInPage) + 1;
            }

            if (page < 1 || page > pageNum) {
                response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
                return -1;
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " at ~96 LessonController");
        }

        return page;
    }

}

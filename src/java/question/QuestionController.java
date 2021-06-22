/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(maxFileSize = 16177215)

public class QuestionController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ImportQuestionController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ImportQuestionController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String operation = request.getParameter("operation");
        switch (operation) {
            case "Import":
                importQuestion(request, response);
                break;
            default:
                response.sendRedirect("ImportQuestion.jsp");
                break;
        }
    }

    public void importQuestion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        InputStream inputStream = null;
        Part filePart = request.getPart("fileExcel");
        if (filePart != null) {
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
        Workbook workbook = new XSSFWorkbook(inputStream);
        QuestionService qs = new QuestionService();

        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = firstSheet.iterator();
        rowIterator.next(); // skip the header row
        int status = 0;
        String content = null,
                media = null,
                explaination = null,
                answer = null,
                option1 = null,
                option2 = null,
                option3 = null,
                option4 = null;
        while (rowIterator.hasNext()) {
            Row nextRow = rowIterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();

                int columnIndex = nextCell.getColumnIndex();

                switch (columnIndex) {
                    case 0:
                        status = (int) nextCell.getNumericCellValue();
                        break;
                    case 1:
                        content = nextCell.getStringCellValue();
                        break;
                    case 2:
                        media = nextCell.getStringCellValue();
                        break;
                    case 3:
                        explaination = nextCell.getStringCellValue();
                        break;
                    case 4:
                        answer = nextCell.getStringCellValue();
                        break;
                    case 5:
                        option1 = nextCell.getStringCellValue();
                        break;
                    case 6:
                        option2 = nextCell.getStringCellValue();
                        break;
                    case 7:
                        option3 = nextCell.getStringCellValue();
                        break;
                    case 8:
                        option4 = nextCell.getStringCellValue();
                        break;
                }

            }
            qs.addQuestion(status, content, media, explaination, answer, option1, option2, option3, option4);
        }
        /*đoạn dưới này là đã import xong, muốn quay về trang nào thì code*/
        out.print("Import Successfully OK!");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

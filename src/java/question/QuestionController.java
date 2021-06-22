/**
 * Jun 13, 1011
 *
 * @author Hoang Tien Minh
 */
package question;

import common.entities.Question;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.HEAD;
import static javax.ws.rs.core.Response.status;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@MultipartConfig(maxFileSize = 16177215)
public class QuestionController extends HttpServlet {

    private QuestionService questionService;

    @Override
    public void init() throws ServletException {
        questionService = new QuestionService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String subjectId = request.getParameter("63");

        int subjectIndicator = 63;
//        HttpSession session = request.getSession();
//        session.setAttribute("sId", subjectId);
        String index = request.getParameter("index");
        if (index == null) {
            index = "1";
        }
        int indexPage = Integer.parseInt(index);
        List<Question> questionList = questionService.getQuestions(63, indexPage);
        request.setAttribute("end", getTotalPage(request, response, subjectIndicator));
        request.setAttribute("tag", index);
        request.setAttribute("questionList", questionList);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");
        if (operation == null) {
            response.sendRedirect("ImportQuestion.jsp");
        } else {
            switch (operation) {
                case "Import":
                    importQuestion(request, response);
                    break;
                case "SEARCHQUESTION":
                    String subjectId = request.getParameter("63");
                    int subjectIndicator = 63;
                    String searchName = request.getParameter("searchQuestion");
                    HttpSession ses = request.getSession();
                    ses.setAttribute("searchName", searchName);
                    String index = request.getParameter("index");
                    if (index == null) {
                        index = "1";
                    }
                    int indexPage = Integer.parseInt(index);

                    List<Question> questionList = questionService.searchQuestion(63, searchName, indexPage);

                    request.setAttribute("end", getTotalPageSearch(request, response, 63, searchName));
                    request.setAttribute("tag", index);
                    request.setAttribute("questionList", questionList);
                    request.getRequestDispatcher("list.jsp").forward(request, response);
                    break;
                default:
                    response.sendRedirect("ImportQuestion.jsp");
                    break;
            }
        }
    }

    private int getTotalPage(HttpServletRequest request, HttpServletResponse response, int courseId)
            throws ServletException, IOException {
        int total = questionService.countTotalQuestion(63);
        int endPage = 0;
        if (total % 5 == 0) {
            endPage = questionService.countTotalQuestion(63) / 5;
        } else {
            endPage = (questionService.countTotalQuestion(63) / 5) + 1;
        }
        return endPage;
    }

    private int getTotalPageSearch(HttpServletRequest request, HttpServletResponse response, int courseId, String searchName)
            throws ServletException, IOException {
        int total = questionService.countingQuestionListSearch(63, searchName);
        int endPage = 0;
        if (total % 5 == 0) {
            endPage = questionService.countingQuestionListSearch(63, searchName) / 8;
        } else {
            endPage = (questionService.countingQuestionListSearch(63, searchName) / 8) + 1;
        }
        return endPage;
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

}

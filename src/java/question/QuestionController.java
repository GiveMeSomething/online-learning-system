/**
 * Jun 13, 1011
 *
 * @author Hoang Tien Minh
 */
package question;

import common.entities.Level;
import common.entities.Question;
import common.entities.Status;
import java.io.IOException;
import java.util.ArrayList;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@MultipartConfig(maxFileSize = 16177215)
public class QuestionController extends HttpServlet {

  private QuestionService questionService;
    private final int questionPerpage = 5;

    @Override
    public void init() throws ServletException {
        questionService = new QuestionService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String operation = request.getParameter("operation");
        int courseId = 2;
        if (operation == null) {
            processInputForQuestion(request, response);

        } else if (operation.equals("PAGINATION")) {
            int page = Integer.parseInt(request.getParameter("page"));

            List<Question> pageItems = getQuestionPerPage((List<Question>) session.getAttribute("questionList"), page);
            if (pageItems != null) {
                request.setAttribute("dimensionList", questionService.getDimensionList(courseId));
                request.setAttribute("pageItems", pageItems);
                request.getRequestDispatcher("/auth/teacher/question/list.jsp").forward(request, response);
            } else {
                response.sendRedirect("/nauth/404.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation.equals("SEARCHQUESTION")) {
            processInputForQuestion(request, response);
        }
    }

    public void processInputForQuestion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String dimensionName = "";
        String levelString = request.getParameter("level");
        String statusString = request.getParameter("status");

        Level level;
        Status status;

        if (levelString != null && !levelString.equals("")) {
            level = Level.valueOf(levelString);
        } else {
            level = null;
        }

        if (statusString != null && !statusString.equals("")) {
            status = Status.valueOf(statusString);
        } else {
            status = null;
        }

        try {
            String dimension = request.getParameter("dimension");
            dimensionName = dimension;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " at ~89 QuestionController");
        }

        request.setAttribute("selectedDimension", dimensionName);
        request.setAttribute("selectedStatus", statusString);
        request.setAttribute("selectedLevel", levelString);
        request.setAttribute("selectedKeyword", keyword);

//        int courseId = Integer.parseInt(request.getParameter("thong nhat sau"));
        int courseId = 2;
        if (keyword == null || keyword.equals("")) {
            keyword = "";
        }

        getQuestionList(request, response, courseId, keyword, level, status, dimensionName);
    }

    public void getQuestionList(HttpServletRequest request, HttpServletResponse response,
            int courseId, String keyword, Level level, Status status, String dimensionName)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<Question> questionList = questionService.getQuestionsWithCondition(courseId, keyword, level, status, dimensionName);
        session.setAttribute("questionList", questionList);

        if (questionList == null || questionList.size() == 0) {
            request.setAttribute("dimensionList", questionService.getDimensionList(courseId));
            request.setAttribute("questionList", questionList);
            request.getRequestDispatcher("/auth/teacher/question/list.jsp").forward(request, response);
        }
        int page = processPageParameter(request, response, questionList.size());
        List<Question> pageItems = getQuestionPerPage(questionList, page);

        if (pageItems != null) {
            request.setAttribute("dimensionList", questionService.getDimensionList(courseId));
            request.setAttribute("pageItems", pageItems);
            request.getRequestDispatcher("/auth/teacher/question/list.jsp").forward(request, response);
        } else {
            response.sendRedirect("/nauth/404.jsp");
        }
    }

    private List<Question> getQuestionPerPage(List<Question> questionList, int page) {
        if (page > ((questionList.size() / 5) + 1)) {
            return null;
        }
        int startQuestion = (page - 1) * questionPerpage;
        int endQuestion = (startQuestion + questionPerpage) > questionList.size() ? questionList.size() : startQuestion + questionPerpage;

        List<Question> questionInPage = new ArrayList<>();
        for (int i = startQuestion; i < endQuestion; i++) {
            questionInPage.add(questionList.get(i));
        }
        return questionInPage;
    }

    private int processPageParameter(HttpServletRequest request, HttpServletResponse response, int listSize)
            throws ServletException, IOException {
        // If not yet receive page param (first time in page) change it to 1
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1 || page > ((listSize / questionPerpage) + 1)) {
                response.sendRedirect(request.getContextPath() + "/nauth/404.jsp");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " at ~96 SubjectController");
        }

        return page;
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
            questionService.addQuestion(status, content, media, explaination, answer, option1, option2, option3, option4);
        }
        /*đoạn dưới này là đã import xong, muốn quay về trang nào thì code*/
        out.print("Import Successfully OK!");
    }

}

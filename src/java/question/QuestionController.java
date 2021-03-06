/**
 * Jun 13, 1011
 *
 * @author Hoang Tien Minh + Nguyen Khanh Toan
 */
package question;

import common.entities.Level;
import common.entities.Question;
import common.entities.Status;
import common.entities.Course;
import common.entities.Dimension;
import common.entities.Lesson;
import common.entities.Question;
import course.CourseService;
import java.io.*;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class QuestionController extends HttpServlet {

    private QuestionService questionService;
    private final int questionPerpage = 5;
    private CourseService courseService;
    private static final long SerialVersionUID = 1L;
    private static final String UPLOAD_DIR = "assets/questionImg/";

    @Override
    public void init() throws ServletException {
        questionService = new QuestionService();
        courseService = new CourseService();
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

    private void processUserInterface(HttpServletRequest request,
            HttpServletResponse response, int questionId, int categoryId, int subjectId)
            throws ServletException, IOException {
        Question questionDetail = questionService.getQuestionDetails(questionId);
        List<Course> courseList = courseService.getCourseByCateID(categoryId);
        List<Lesson> lessonList = questionService.getLessonByCourseId(subjectId);
        List<Dimension> dimensionList = courseService.getSubjectDimensionByCourseId(subjectId);
        Question answer = questionService.getAnswerByQuestionId(questionId);
        request.setAttribute("courseList", courseList);
        request.setAttribute("answer", answer);
        request.setAttribute("dimensionList", dimensionList);
        request.setAttribute("lessonList", lessonList);
        request.setAttribute("questionDetail", questionDetail);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String operation = request.getParameter("operation");
        int questionId = 1;
        int categoryId = 1;
        int subjectId = 1;
        if (request.getParameter("questionId") != null) {
            questionId = Integer.parseInt(request.getParameter("questionId"));
            session.setAttribute("questionId", questionId);
        }
        if (request.getParameter("subjectId") != null) {
            subjectId = Integer.parseInt(request.getParameter("subjectId"));
            session.setAttribute("subjectId", subjectId);
            categoryId = Integer.parseInt(courseService.getCategoryByCourseId(subjectId).getCategory());
        }
        if (operation == null) {
            processInputForQuestion(request, response);
        } else if (operation.equals("MANAGEQUESTION")) {
            processInputForQuestion(request, response);
        } else if (operation.equals("PAGINATION")) {
            int page = Integer.parseInt(request.getParameter("page"));
            List<Question> pageItems = getQuestionPerPage((List<Question>) session.getAttribute("questionList"), page);
            if (pageItems != null) {
                request.setAttribute("dimensionList", questionService.getDimensionList(subjectId));
                request.setAttribute("pageItems", pageItems);
                request.getRequestDispatcher("/auth/teacher/question/list.jsp").forward(request, response);
            } else {
                response.sendRedirect("/nauth/404.jsp");
            }
        } else if (operation.equals("VIEW")) {
            subjectId = Integer.parseInt(session.getAttribute("subjectId") + "");
            categoryId = Integer.parseInt(courseService.getCategoryByCourseId(Integer.parseInt(session.getAttribute("subjectId") + "")).getCategory());
            processUserInterface(request, response, questionId, categoryId, subjectId);
            Question questionDetail = questionService.getQuestionDetails(questionId);
            String image = questionDetail.getMedia();
            int totalAnswerOptions = questionService.countAnswerOptions();
            request.setAttribute("totalAnswerOptions", totalAnswerOptions);
            request.setAttribute("image", image);
            request.getRequestDispatcher("/auth/teacher/question/detail.jsp").forward(request, response);
            //sau khi merge code ch???nh l???i ???????ng d???n auth/teacher/question/detail.jsp
        } else if (operation.equals("DELETEQUESTION")) {
            deleteQuestion(request, response, questionId);
            //sau khi merge code ch???nh l???i ???????ng d???n auth/teacher/question/detail.jsp
        } else if (operation.equals("DELETEANSWER")) {

            String column = request.getParameter("column");
            questionService.deleteAnswerOptions(column, questionId);
            int totalAnswerOptions = questionService.countAnswerOptions();
            Question questionDetail = questionService.getQuestionDetails(questionId);
            String media = request.getParameter("media");
            request.setAttribute("image", media);
            request.setAttribute("totalAnswerOptions", totalAnswerOptions);
            processUserInterface(request, response, questionId, categoryId, subjectId);
            request.getRequestDispatcher("/auth/teacher/question/detail.jsp").forward(request, response);
            //sau khi merge code ch???nh l???i ???????ng d???n auth/teacher/question/detail.jsp
        } else if (operation.equals("EDITANSWER")) {

            String column = request.getParameter("column");
            int id = Integer.parseInt(request.getParameter("id"));

            Question answerDetail = questionService.getAnswerDetail(column, questionId);
            if (column.equals("option1")) {
                request.setAttribute("answerDetail", answerDetail.getOption1());
            } else if (column.equals("option2")) {
                request.setAttribute("answerDetail", answerDetail.getOption2());
            } else if (column.equals("option3")) {
                request.setAttribute("answerDetail", answerDetail.getOption3());
            } else if (column.equals("option4")) {
                request.setAttribute("answerDetail", answerDetail.getOption4());
            } else if (column.equals("option5")) {
                request.setAttribute("answerDetail", answerDetail.getOption5());
            }
            request.setAttribute("id", id);
            request.setAttribute("column", column);
            request.getRequestDispatcher("/auth/teacher/question/answerEditInfo.jsp").forward(request, response);
            //sau khi merge code ch???nh l???i ???????ng d???n auth/teacher/question/answerEditInfo.jsp
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        String columnUpdated = request.getParameter("columnUpdated");
        HttpSession session = request.getSession();
        int questionId = 1;
        if (session.getAttribute("questionId") != null) {
            questionId = Integer.parseInt(session.getAttribute("questionId") + "");
        }

        int subjectId = Integer.parseInt(session.getAttribute("subjectId") + "");
        int categoryId = Integer.parseInt(courseService.getCategoryByCourseId(subjectId).getCategory());

        if (operation.equals("SEARCHQUESTION")) {
            processInputForQuestion(request, response);
        } else if (operation.equals("UPDATEANSWER")) {
            String content = request.getParameter("content");
            questionService.updateAnswerOptions(columnUpdated, content, questionId);
            processUserInterface(request, response, questionId, categoryId, subjectId);
            Question questionDetail = questionService.getQuestionDetails(questionId);
            String image = questionDetail.getMedia();
            int totalAnswerOptions = questionService.countAnswerOptions();
            request.setAttribute("totalAnswerOptions", totalAnswerOptions);
            request.setAttribute("image", image);
            request.getRequestDispatcher("/auth/teacher/question/detail.jsp").forward(request, response);
//            response.sendRedirect(request.getContextPath() + "/auth/teacher/question"); //sau khi merge code ch???nh l???i ???????ng d???n
        } else if (operation.equals("UPDATEQUESTION")) {
            int dimensionId = Integer.parseInt(request.getParameter("dimension"));
            int lessonId = Integer.parseInt(request.getParameter("lesson"));
            int statusId = Integer.parseInt(request.getParameter("status"));
            subjectId = Integer.parseInt(request.getParameter("subject"));
            String content = request.getParameter("content");
            String media = request.getParameter("media");
            String option1 = request.getParameter("option1");
            String option2 = request.getParameter("option2");
            String option3 = request.getParameter("option3");
            String option4 = request.getParameter("option4");
            String option5 = request.getParameter("option5");
            String answer = request.getParameter("answer");
            String explaination = request.getParameter("explaination");
            questionService.updateQuestionBankByQuestionId(statusId, content, media,
                    option1, option2, option3, option4, option5, explaination, answer, questionId);
            questionService.updateQuestionCourseDimLes(subjectId, dimensionId, lessonId, questionId);
            //NEED
            processUserInterface(request, response, questionId, categoryId, subjectId);
            Question questionDetail = questionService.getQuestionDetails(questionId);
            String image = questionDetail.getMedia();
            int totalAnswerOptions = questionService.countAnswerOptions();
            request.setAttribute("totalAnswerOptions", totalAnswerOptions);
            request.setAttribute("image", image);
            request.getRequestDispatcher("/auth/teacher/question/detail.jsp").forward(request, response);
//            response.sendRedirect(request.getContextPath() + "/auth/teacher/question"); //sau khi merge code ch???nh l???i ???????ng d???n
        } else if (operation.equals("ADDANSWER")) {
            HttpSession currentSession = request.getSession();
            Question option1 = questionService.getAnswerDetail("option1", questionId);
            Question option2 = questionService.getAnswerDetail("option2", questionId);
            Question option3 = questionService.getAnswerDetail("option3", questionId);
            Question option4 = questionService.getAnswerDetail("option4", questionId);
            Question option5 = questionService.getAnswerDetail("option5", questionId);
            String answerContent = request.getParameter("answerContent");
            if (option1.getOption1().equals("")) {
                questionService.addAnswer("option1", answerContent, questionId);
            } else if (option2.getOption2().equals("")) {
                questionService.addAnswer("option2", answerContent, questionId);
            } else if (option3.getOption3().equals("")) {
                questionService.addAnswer("option3", answerContent, questionId);
            } else if (option4.getOption4().equals("")) {
                questionService.addAnswer("option4", answerContent, questionId);
            } else {
//                questionService.addColumnAnswer("option5", "option4");
                questionService.addAnswer("option5", answerContent, questionId);
            }
            processUserInterface(request, response, questionId, categoryId, subjectId);
            Question questionDetail = questionService.getQuestionDetails(questionId);
            String image = questionDetail.getMedia();
            int totalAnswerOptions = questionService.countAnswerOptions();
            request.setAttribute("totalAnswerOptions", totalAnswerOptions);
            request.setAttribute("image", image);
            request.getRequestDispatcher("/auth/teacher/question/detail.jsp").forward(request, response);
//            response.sendRedirect(request.getContextPath() + "/auth/teacher/question"); //sau khi merge code ch???nh l???i ???????ng d???n
        } else if (operation.equals("UPLOADMEDIA")) {
            int totalAnswerOptions = questionService.countAnswerOptions();
            request.setAttribute("totalAnswerOptions", totalAnswerOptions);
            request.setAttribute("image", uploadFile(request));

            processUserInterface(request, response, questionId, categoryId, subjectId);
            request.getRequestDispatcher("/auth/teacher/question/detail.jsp").forward(request, response);
            //sau khi merge code ch???nh l???i ???????ng d???n auth/teacher/question/detail.jsp
        } else if (operation.equals("Import")) {
            importQuestion(request, response);
        } else {
            switch (operation) {
                case "Import":
                    importQuestion(request, response);
                    break;
                default:
                    response.sendRedirect("ImportQuestion.jsp");
                    break;
            }
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
        HttpSession session = request.getSession();

        int subjectId = Integer.parseInt(session.getAttribute("subjectId") + "");

//        int subjectId = 1;
//        if (request.getParameter("subjectId") != null) {
//            subjectId = Integer.parseInt(request.getParameter("subjectId"));
//        }
        if (keyword == null || keyword.equals("")) {
            keyword = "";
        }

        getQuestionList(request, response, subjectId, keyword, level, status, dimensionName);
    }

    public void getQuestionList(HttpServletRequest request, HttpServletResponse response,
            int subjectId, String keyword, Level level, Status status, String dimensionName)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<Question> questionList = questionService.getQuestionsWithCondition(subjectId, keyword, level, status, dimensionName);
        session.setAttribute("questionList", questionList);

        if (questionList == null || questionList.size() == 0) {
            request.setAttribute("dimensionList", questionService.getDimensionList(subjectId));
            request.setAttribute("questionList", questionList);
            request.getRequestDispatcher("/auth/teacher/question/list.jsp").forward(request, response);
        }
        int page = processPageParameter(request, response, questionList.size());
        List<Question> pageItems = getQuestionPerPage(questionList, page);

        if (pageItems != null) {
            request.setAttribute("dimensionList", questionService.getDimensionList(subjectId));
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
        int level_id = 0;
        int course_id = 0;
        int lesson_id = 0;
        String content = null,
                media = null,
                explaination = null,
                answer = null,
                option1 = null,
                option2 = null,
                option3 = null,
                option4 = null,
                option5 = null;
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
                    case 9:
                        option5 = nextCell.getStringCellValue();
                        break;
                    case 10:
                        level_id = (int) nextCell.getNumericCellValue();
                        break;
                    case 11:
                        course_id = (int) nextCell.getNumericCellValue();
                        break;
                    case 12:
                        lesson_id = (int) nextCell.getNumericCellValue();
                        break;
                    //12 case
                }

            }
            questionService.addQuestion(status, content, media, explaination, answer, option1, option2, option3, option4, option5, level_id, course_id, lesson_id);
        }
        /*??o???n d?????i n??y l?? ???? import xong, mu???n quay v??? trang n??o th?? code*/
        out.print("Import Successfully OK!");
    }

    private void deleteQuestion(HttpServletRequest request, HttpServletResponse response, int questionId) throws ServletException, IOException {
        questionService.deleteQuestionById(questionId);
        processInputForQuestion(request, response);
    }
}

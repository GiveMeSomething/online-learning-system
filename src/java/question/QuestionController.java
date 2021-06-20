/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package question;

import common.entities.Course;
import common.entities.Dimension;
import common.entities.Lesson;
import common.entities.Question;
import course.CourseService;
import java.util.List;

import java.io.*;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.Part;

import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class QuestionController extends HttpServlet {

    private QuestionService questionService;
    private CourseService courseService;
    private static final long SerialVersionUID = 1L;
    private static final String UPLOAD_DIR = "assets";

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
            HttpServletResponse response, int questionId, int categoryId, int courseId)
            throws ServletException, IOException {
        Question questionDetail = questionService.getQuestionDetails(questionId);
        List<Course> courseList = courseService.getCourseByCateID(categoryId);
        List<Lesson> lessonList = questionService.getLessonByCourseId(courseId);
        List<Dimension> dimensionList = courseService.getSubjectDimensionByCourseId(courseId);
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
        String operation = request.getParameter("operation");
        int questionId = 2;
        int categoryId = 6;
        int courseId = 72;
        if (operation == null) {
            processUserInterface(request, response, questionId, categoryId, courseId);
            Question questionDetail = questionService.getQuestionDetails(questionId);
            String image = questionDetail.getMedia();
            int totalAnswerOptions = questionService.countAnswerOptions();
            request.setAttribute("totalAnswerOptions", totalAnswerOptions);
            request.setAttribute("image", image);
            request.getRequestDispatcher("QuestionDetail.jsp").forward(request, response);
        } else if (operation.equals("DELETEANSWER")) {
            String column = request.getParameter("column");
            questionService.deleteAnswerOptions(column, questionId);
            int totalAnswerOptions = questionService.countAnswerOptions();
            Question questionDetail = questionService.getQuestionDetails(questionId);
            String media = request.getParameter("media");
            request.setAttribute("image", media);
            request.setAttribute("totalAnswerOptions", totalAnswerOptions);
            processUserInterface(request, response, questionId, categoryId, courseId);
            request.getRequestDispatcher("QuestionDetail.jsp").forward(request, response);
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
            } else {
                request.setAttribute("answerDetail", answerDetail.getOption5());
            }
            request.setAttribute("id", id);
            request.setAttribute("column", column);
            request.getRequestDispatcher("AnswerEditInfo.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        String columnUpdated = request.getParameter("columnUpdated");
        int questionId = 2;
        int categoryId = 6;
        int courseId = 72;
        if (operation.equals("UPDATEANSWER")) {
            String content = request.getParameter("content");
            questionService.updateAnswerOptions(columnUpdated, content, questionId);
            response.sendRedirect("question");
        } else if (operation.equals("UPDATEQUESTION")) {
            int subjectId = Integer.parseInt(request.getParameter("subject"));
            int dimensionId = Integer.parseInt(request.getParameter("dimension"));
            int lessonId = Integer.parseInt(request.getParameter("lesson"));
            int statusId = Integer.parseInt(request.getParameter("status"));
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
            response.sendRedirect("question");
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
                questionService.addColumnAnswer("option5", "option4");
                questionService.addAnswer("option5", answerContent, questionId);

            }
            response.sendRedirect("question");
        } else if (operation.equals("UPLOADMEDIA")) {
            int totalAnswerOptions = questionService.countAnswerOptions();
            request.setAttribute("totalAnswerOptions", totalAnswerOptions);
            request.setAttribute("image", uploadFile(request));
            processUserInterface(request, response, questionId, categoryId, courseId);
            request.getRequestDispatcher("QuestionDetail.jsp").forward(request, response);
        }
    }
}

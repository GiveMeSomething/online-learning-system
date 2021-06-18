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
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionController extends HttpServlet {

    private QuestionService questionService;
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        questionService = new QuestionService();
        courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        int questionId = 2;
        int categoryId = 6;
        int courseId = 72;
        if (operation == null) {
            Question questionDetail = questionService.getQuestionDetails(questionId);
            List<Course> courseList = courseService.getCourseByCateID(categoryId);
            List<Lesson> lessonList = questionService.getLessonByCourseId(courseId);
            List<Dimension> dimensionList = courseService.getSubjectDimensionByCourseId(courseId);
            request.setAttribute("courseList", courseList);
            request.setAttribute("dimensionList", dimensionList);
            request.setAttribute("lessonList", lessonList);
            request.setAttribute("questionDetail", questionDetail);
            request.getRequestDispatcher("QuestionDetail.jsp").forward(request, response);
        } else if (operation.equals("DELETEANSWER")) {
            String column = request.getParameter("column");
            questionService.deleteAnswerOptions(column, questionId);
            Question questionDetail = questionService.getQuestionDetails(questionId);
            List<Course> courseList = courseService.getCourseByCateID(categoryId);
            List<Lesson> lessonList = questionService.getLessonByCourseId(courseId);
            List<Dimension> dimensionList = courseService.getSubjectDimensionByCourseId(courseId);
            request.setAttribute("courseList", courseList);
            request.setAttribute("dimensionList", dimensionList);
            request.setAttribute("lessonList", lessonList);
            request.setAttribute("questionDetail", questionDetail);
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
            } else {
                request.setAttribute("answerDetail", answerDetail.getOption4());
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
        if (operation.equals("UPDATEANSWER")) {
            String content = request.getParameter("content");
            questionService.updateAnswerOptions(columnUpdated, content, questionId);
            response.sendRedirect("question");
        } else if (operation.equals("UPDATEQUESTION")) {
            int subjectId = Integer.parseInt(request.getParameter("subject")) ;
            int dimensionId = Integer.parseInt(request.getParameter("dimension")) ;
            int lessonId = Integer.parseInt(request.getParameter("lesson"));
            int statusId = Integer.parseInt(request.getParameter("status"));
            String content = request.getParameter("content");
            String media = request.getParameter("media");
            String option1 = request.getParameter("option1");
            String option2 = request.getParameter("option2");
            String option3 = request.getParameter("option3");
            String option4 = request.getParameter("option4");
            String explaination = request.getParameter("explaination");
            questionService.updateQuestionBankByQuestionId(statusId, content, media,
                    option1, option2, option3, option4, explaination, questionId);
            questionService.updateQuestionCourseDimLes(subjectId, dimensionId, lessonId, questionId);
            response.sendRedirect("question");
        }
    }
}

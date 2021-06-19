/**
 * Jun 16, 2021
 *
 * @author Vu Duy Anh
 */
package lesson;

import common.entities.Lesson;
import common.entities.LessonType;
import common.entities.Quiz;
import course.CourseService;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import quiz.QuizService;

public class LessonController extends HttpServlet {

    private LessonService lessonService;
    private CourseService courseService;
    private QuizService quizService;

    @Override
    public void init() throws ServletException {
        lessonService = new LessonService();
        courseService = new CourseService();
        quizService = new QuizService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation == null) {
            int courseId = 1;
            String id = request.getParameter("id");
            int lessonId;
            if (id == null) {
                Lesson lesson = null;
                viewLesson(request, response, lesson);
            } else {
                lessonId = Integer.parseInt(id);
                Lesson lesson = lessonService.getLesson(lessonId);
                viewLesson(request, response, lesson);
            }
        } else if (operation.equals("ADDNEWLESSON")) {
            String id = request.getParameter("id");
            if (id == null) {
                addLesson(request, response);
            } else {
                editLesson(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void addLesson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lessonName = request.getParameter("lesson-name");
        LessonType lessonType = LessonType.valueOf(request.getParameter("type"));
        int order = Integer.parseInt(request.getParameter("order"));
        int courseId = Integer.parseInt(request.getParameter("course"));

        if (lessonType.equals(LessonType.valueOf("SUBJECT_TOPIC"))) {
            Lesson lesson = new Lesson(lessonName, order, lessonType, courseId);
            if (lessonService.checkLessonExist(lesson) == null) {
                lessonService.addLessonDetail(lesson);
            }
        } else if (lessonType.equals(LessonType.valueOf("LESSON"))) {
            String videoLink = request.getParameter("video-link");
            String html = request.getParameter("html-content");
            Lesson lesson = new Lesson(lessonName, order, lessonType, courseId, videoLink, html);
            if (lessonService.checkLessonExist(lesson) == null) {
                lessonService.addLessonDetail(lesson);
            }
        } else {
            int quizId = Integer.parseInt(request.getParameter("quiz"));
            String html = request.getParameter("html-quiz");
            Lesson lesson = new Lesson(lessonName, order, lessonType, courseId, html, quizId);
            if (lessonService.checkLessonExist(lesson) == null) {
                lessonService.addLessonDetail(lesson);
            }
        }
        response.sendRedirect("auth/teacher/subject/lesson/list.jsp");
    }

    private void editLesson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String lessonName = request.getParameter("lesson-name");
        LessonType lessonType = LessonType.valueOf(request.getParameter("type"));
        int order = Integer.parseInt(request.getParameter("order"));
        int courseId = Integer.parseInt(request.getParameter("course"));
        Lesson lesson = lessonService.getLesson(order);
        request.setAttribute("lesson", lesson);
        if (lessonType.equals(LessonType.valueOf("SUBJECT_TOPIC"))) {
            lesson = new Lesson(lessonName, order, lessonType, courseId);
            lessonService.updateLessonDetail(lesson, id);
        } else if (lessonType.equals(LessonType.valueOf("LESSON"))) {
            String videoLink = request.getParameter("video-link");
            String html = request.getParameter("html-content");
            lesson = new Lesson(lessonName, order, lessonType, courseId, videoLink, html);
            lessonService.updateLessonDetail(lesson, id);
        } else {
            int quizId = Integer.parseInt(request.getParameter("quiz"));
            String html = request.getParameter("html-quiz");
            lesson = new Lesson(lessonName, order, lessonType, courseId, html, quizId);
            lessonService.updateLessonDetail(lesson, id);
        }
        response.sendRedirect("auth/teacher/subject/lesson/list.jsp");
    }

    private void viewLesson(HttpServletRequest request, HttpServletResponse response, Lesson lesson)
            throws ServletException, IOException {
        HashMap<Integer, String> getCourses = courseService.getCourses();
        ArrayList<Quiz> quizsList = quizService.getQuizList(1);
        request.setAttribute("course", getCourses);
        request.setAttribute("quiz", quizsList);
        request.setAttribute("lesson", lesson);
        request.getRequestDispatcher("detail.jsp").forward(request, response);
    }

}

/**
 * Jun 16, 2021
 *
 * @author Vu Duy Anh
 */
package lesson;

import common.entities.Lesson;
import common.entities.LessonType;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LessonController", urlPatterns = {"/lesson"})
public class LessonController extends HttpServlet {

    private LessonService lessonService;

    @Override
    public void init() throws ServletException {
        lessonService = new LessonService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            String html = request.getParameter("html-content");
            Lesson lesson = new Lesson(lessonName, order, lessonType, courseId, html, quizId);
            if (lessonService.checkLessonExist(lesson) == null) {
                lessonService.addLessonDetail(lesson);
            }
        }
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
            lessonService.updateLessonDetail(lesson);
        } else if (lessonType.equals(LessonType.valueOf("LESSON"))) {
            String videoLink = request.getParameter("video-link");
            String html = request.getParameter("html-content");
            lesson = new Lesson(lessonName, order, lessonType, courseId, videoLink, html);
            lessonService.updateLessonDetail(lesson);
        } else {
            int quizId = Integer.parseInt(request.getParameter("quiz"));
            String html = request.getParameter("html-content");
            lesson = new Lesson(lessonName, order, lessonType, courseId, html, quizId);
            lessonService.updateLessonDetail(lesson);
        }
        request.getRequestDispatcher("").forward(request, response);
    }

    private void viewLesson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}

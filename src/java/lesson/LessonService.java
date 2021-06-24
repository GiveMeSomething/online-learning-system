/**
 * Jun 16, 2021
 *
 * @author Vu Duy Anh
 */
package lesson;

import common.entities.Lesson;
import common.entities.Status;
import java.sql.SQLException;
import java.util.ArrayList;

public class LessonService {

    private final LessonRepository lessonRepository;

    public LessonService() {
        lessonRepository = new LessonRepository();
    }

    public boolean addLessonDetail(Lesson lesson) {
        try {
            return lessonRepository.addLessonDetail(lesson);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateLessonDetail(Lesson lesson, int id) {
        try {
            return lessonRepository.updateLessonDetail(lesson, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Lesson checkLessonExist(Lesson lesson) {
        try {
            return lessonRepository.checkLessonExist(lesson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Lesson getLesson(int id) {
        try {
            return lessonRepository.getLesson(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Lesson> getLessonList(int subjectId, String keyword) {
        try {
            ArrayList<Lesson> result = this.lessonRepository.getLessonList(subjectId, keyword);

            if (result.size() <= 0) {
                return null;
            } else {
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateLessonStatus(int lessonId, Status status) {
        try {
            return this.lessonRepository.updateLessonStatus(lessonId, status);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
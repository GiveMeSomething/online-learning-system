/**
 * Jun 16, 2021
 *
 * @author Vu Duy Anh
 */
package lesson;

import common.entities.Lesson;
import java.sql.SQLException;

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

    public boolean updateLessonDetail(Lesson lesson) {
        try {
            return lessonRepository.updateLessonDetail(lesson);
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

    public Lesson getLesson(int id){
        try {
            return lessonRepository.getLesson(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

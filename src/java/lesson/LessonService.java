/**
 * Jun 17, 2021
 *
 * @author Hoang Tien Minh
 */
package lesson;

import common.entities.Lesson;
import common.entities.Status;
import java.util.ArrayList;

public class LessonService {

    private final LessonRepository lessonRepository;

    public LessonService() {
        this.lessonRepository = new LessonRepository();
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

/**
 * Jun 17, 2021
 *
 * @author Hoang Tien Minh
 */
package lesson;

import common.entities.Lesson;
import java.util.ArrayList;

public class LessonService {

    private final LessonRepository lessonRepository;

    public LessonService() {
        this.lessonRepository = new LessonRepository();
    }

    public ArrayList<Lesson> getLessonList(int subjectId) {
        try {
            ArrayList<Lesson> result = this.lessonRepository.getLessonList(subjectId);

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
}

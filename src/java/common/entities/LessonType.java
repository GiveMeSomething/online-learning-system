/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package common.entities;

import java.util.HashMap;
import java.util.Map;

public enum LessonType {
    SUBJECT_TOPIC,
    LESSON,
    QUIZ;

    private static final Map<LessonType, Integer> LESSON_TYPE_VALUE = new HashMap<>();

    static {
        LESSON_TYPE_VALUE.put(SUBJECT_TOPIC, 1);
        LESSON_TYPE_VALUE.put(LESSON, 2);
        LESSON_TYPE_VALUE.put(QUIZ, 3);
    }

    public static int valueOf(LessonType lessonType) {
        return LESSON_TYPE_VALUE.get(lessonType);
    }
}

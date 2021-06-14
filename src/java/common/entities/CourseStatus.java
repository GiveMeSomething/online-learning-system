/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package common.entities;

import java.util.HashMap;
import java.util.Map;

public enum CourseStatus {
    PUBLISHED,
    UNPUBLISHED;

    private static final Map<CourseStatus, Integer> COURSE_STATUS_VALUE = new HashMap<>();

    static {
        COURSE_STATUS_VALUE.put(PUBLISHED, 1);
        COURSE_STATUS_VALUE.put(UNPUBLISHED, 0);
    }

    public static int valueOf(CourseStatus courseStatus) {
        return COURSE_STATUS_VALUE.get(courseStatus);
    }
}

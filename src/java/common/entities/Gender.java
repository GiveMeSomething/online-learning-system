/**
 * May 31, 2021
 *
 * @author Hoang Tien Minh
 */
package common.entities;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
    MALE,
    FEMALE;

    private static final Map<Gender, Integer> GENDER_VALUE = new HashMap<>();

    static {
        GENDER_VALUE.put(MALE, 1);
        GENDER_VALUE.put(FEMALE, 0);
    }

    public static int valueOf(Gender gender) {
        return GENDER_VALUE.get(gender);
    }
}

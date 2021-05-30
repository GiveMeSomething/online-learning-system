/**
 * May 31, 2021
 *
 * @author Hoang Tien Minh
 */
package common.entities;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
    Male,
    Female;

    private static final Map<Gender, Integer> GENDER_VALUE = new HashMap<>();

    static {
        GENDER_VALUE.put(Male, 1);
        GENDER_VALUE.put(Female, 0);
    }

    public static int valueOf(Gender gender) {
        return GENDER_VALUE.get(gender);
    }
}

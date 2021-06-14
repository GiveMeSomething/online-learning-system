/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package common.entities;

import java.util.HashMap;
import java.util.Map;

public enum TestType {
    SIMULATION,
    TEST,
    QUIZ;

    private static final Map<TestType, Integer> TEST_TYPE_VALUE = new HashMap<>();

    static {
        TEST_TYPE_VALUE.put(SIMULATION, 1);
        TEST_TYPE_VALUE.put(TEST, 2);
        TEST_TYPE_VALUE.put(QUIZ, 3);
    }

    public static int valueOf(TestType type) {
        return TEST_TYPE_VALUE.get(type);
    }
}

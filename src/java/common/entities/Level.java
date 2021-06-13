/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package common.entities;

import java.util.HashMap;
import java.util.Map;

public enum Level {
    EASY,
    MEDIUM,
    HARD;

    private static final Map<Level, Integer> LEVEL_VALUE = new HashMap<>();

    static {
        LEVEL_VALUE.put(EASY, 1);
        LEVEL_VALUE.put(MEDIUM, 2);
        LEVEL_VALUE.put(HARD, 3);
    }

    public static int valueOf(Level level) {
        return LEVEL_VALUE.get(level);
    }
}

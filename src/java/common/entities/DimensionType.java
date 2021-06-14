/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package common.entities;

import java.util.HashMap;
import java.util.Map;

public enum DimensionType {
    DOMAIN,
    GROUP,
    SUBJECT_TOPIC;

    private static final Map<DimensionType, Integer> DIMENSION_VALUE = new HashMap<>();

    static {
        DIMENSION_VALUE.put(DOMAIN, 1);
        DIMENSION_VALUE.put(GROUP, 2);
        DIMENSION_VALUE.put(SUBJECT_TOPIC, 3);
    }
}

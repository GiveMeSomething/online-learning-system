/**
 * May 31, 2021
 *
 * @author Hoang Tien Minh
 */
package common.entities;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    ACTIVE,
    INACTIVE;

    private static final Map<Status, Integer> STATUS_VALUE = new HashMap<>();

    static {
        STATUS_VALUE.put(INACTIVE, 0);
        STATUS_VALUE.put(ACTIVE, 1);
    }

    public static int valueOf(Status status) {
        return STATUS_VALUE.get(status);
    }

}

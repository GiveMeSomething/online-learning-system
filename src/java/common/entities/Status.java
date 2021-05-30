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

    private static final Map<Role, Integer> STATUS_VALUE = new HashMap<>();

    static {
        STATUS_VALUE.put(INACTIVE, 0);
        STATUS_VALUE.put(ACTIVE, 1);
    }

    public static Role valueOf(Status status) {
        return STATUS_VALUE.get(status);
    }

}

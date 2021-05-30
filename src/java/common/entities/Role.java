/**
 * May 28, 2021
 *
 * @author Hoang Tien Minh
 */
package common.entities;

import java.util.HashMap;
import java.util.Map;

public enum Role {
    ADMIN,
    TEACHER,
    STUDENT;

    private static final Map<Role, Integer> ROLE_VALUE = new HashMap<>();

    static {
        ROLE_VALUE.put(ADMIN, 0);
        ROLE_VALUE.put(TEACHER, 1);
        ROLE_VALUE.put(STUDENT, 2);
    }

    public static int valueOf(Role role) {
        return ROLE_VALUE.get(role);
    }

}

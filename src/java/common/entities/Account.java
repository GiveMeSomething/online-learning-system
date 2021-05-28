package common.entities;

import common.utilities.Role;
import common.utilities.Role;
import common.utilities.Role;

/**
 *
 * @author Hoang Tien Minh
 */
public class Account {

    private String email;
    private String password;
    private Role role;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }
}

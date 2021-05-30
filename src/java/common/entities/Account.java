package common.entities;

/**
 *
 * @author Hoang Tien Minh
 */
public class Account {

    private String email;
    private String password;
    private Role role;
    private String token;

    // Use this constructor when use register (without salt, token)
    // Salt is not use anywhere in the system -> remove
    // Token is use for authorize
    public Account(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

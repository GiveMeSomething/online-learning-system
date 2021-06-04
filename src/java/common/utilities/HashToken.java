/**
 * May 30, 2021
 *
 * @author Hoang Tien Minh
 */
package common.utilities;

public class HashToken {

    public static String getToken(String email, int salt, String role) {
        StringBuilder token = new StringBuilder((Integer.toString((email + role).hashCode())));
        token.insert(token.length() / 2, Integer.toString(salt));

        return token.toString();
    }

}

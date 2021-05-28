/**
 * May 29, 2021
 *
 * @author Hoang Tien Minh
 */
package common.utilities;

public class HashPassword {

    public static String getHashPassword(String password, String salt) {
        StringBuilder builder = new StringBuilder(Integer.toString(password.hashCode()));

        builder.insert(builder.length() / 2, salt);

        return builder.toString();
    }

    public static boolean validatePassword(String password, String salt, String inputPassword) {
        return password.equals(getHashPassword(inputPassword, salt));
    }
}

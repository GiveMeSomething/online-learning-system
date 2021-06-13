/**
 * May 29, 2021
 *
 * @author Hoang Tien Minh
 */
package common.utilities;

public class HashPassword {

    // We need to include email to ensure that all hashPassword is different
    public static String getHashPassword(String email, String password, int salt) {
        StringBuilder builder = new StringBuilder(
                Integer.toString(email.hashCode())
                + Integer.toString(password.hashCode())
        );

        builder.insert(builder.length() / 2, Integer.toString(salt));

        return builder.toString();
    }

    public static boolean validatePassword(String email, String password, String salt, String inputPassword) {
        return password.equals(getHashPassword(email, inputPassword, Integer.parseInt(salt)));
    }

    public static void main(String[] args) {
        // For testing hashPassword method

        System.out.println(HashPassword.validatePassword("minhhthe150277@fpt.edu.vn", "2083632174748690", "17", "123"));
    }
}

/**
 * May 29, 2021
 *
 * @author Hoang Tien Minh
 */
package common.utilities;

public class HashPassword {

    public static String getHashPassword(String password, int salt) {
        StringBuilder builder = new StringBuilder(Integer.toString(password.hashCode()));

        builder.insert(builder.length() / 2, Integer.toString(salt));

        return builder.toString();
    }

    public static boolean validatePassword(String password, String salt, String inputPassword) {
        return password.equals(getHashPassword(inputPassword, Integer.parseInt(salt)));
    }

    public static void main(String[] args) {
        System.out.println(validatePassword("150689442", "68", "1234"));
    }
}

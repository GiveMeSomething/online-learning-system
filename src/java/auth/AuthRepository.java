/**
 * 28/05/2021
 *
 * @author Admin
 */
package auth;

import common.utilities.Repository;
import common.entities.User;

public class AuthRepository extends Repository {

    public void login(User user) {

    }

    public void register(User user) {

    }

    public boolean isDuplicateUser(String email) {
        return false;
    }
}

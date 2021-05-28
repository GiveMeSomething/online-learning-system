/**
 * May 28, 2021
 *
 * @author Hoang Tien Minh
 */
package common.utilities;

import java.sql.Connection;
import java.sql.SQLException;

import database.DBContext;

public class Repository {

    protected Connection connection;

    protected void initConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = new DBContext().getConnection();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

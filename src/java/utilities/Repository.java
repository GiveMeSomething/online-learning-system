/**
 * 22/5/2021 - Online Learning System
 *
 * @author Hoang Tien Minh
 */
package utilities;

import database.DBContext;
import java.sql.Connection;
import java.sql.SQLException;

public class Repository {

    protected Connection connection;

    protected void connectDatabase() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = new DBContext().getConnection();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void disconnectDatabase() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

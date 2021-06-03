package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {

    private final String serverName = "localhost";
    private final String dbName = "db_ite1";
    private final String userID = "root";
    private final String password = "123456";

    public Connection getConnection() throws Exception {
        String url = "jdbc:mysql://" + serverName + "/" + dbName + "?useSSL=false";
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        return DriverManager.getConnection(url, userID, password);
    }
}

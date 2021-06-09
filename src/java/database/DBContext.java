package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {

    private final String serverName = "localhost";
    private final String dbName = "db_ite1";
    private final String userID = "root";
    private final String password = "root";

    public Connection getConnection() throws Exception {
        String url = "jdbc:mysql://" + serverName + "/" + dbName + "?useSSL=false&allowPublicKeyRetrieval=true";
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        return DriverManager.getConnection(url, userID, password);
    }

    public static void main(String[] args) {
        // Test connection
        try {
            System.out.println(new DBContext().getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

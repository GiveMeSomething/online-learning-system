package database;

import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBContext {

    //USE BELOW METHOD FOR YOUR DATABASE CONNECTION FOR BOTH SINGLE AND MULTILPE SQL SERVER INSTANCE(s)
    //DO NOT EDIT THE BELOW METHOD, YOU MUST USE ONLY THIS ONE FOR YOUR DATABASE CONNECTION*/
    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://" + serverName + "/" + dbName + "?useSSL=false";
        return DriverManager.getConnection(url, user, password);
    }

    //Insert your other code right after this comment
    //Change/update information of your database connection, DO NOT change name of instance variables in this class
    private final String serverName = "localhost";
    private final String dbName = "db_ite1";
    private final String user = "root";
    private final String password = "root"; // password here

    public static void main(String[] args) {
        // Test connection
        try {
            System.out.println(new DBContext().getConnection());
        } catch (Exception e) {
        }
    }
}

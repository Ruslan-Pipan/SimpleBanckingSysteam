package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Connection to MySQL database is a singleton.
 *
 * @author Ruslan Pipan
 * @version 1.0
 * */
public class ConnectionBank {
    private static final String URL = "jdbc:mysql://localhost:3306/bank?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&serverTimezone=UTC";
    private static final String PASSWORD = "Ruslan12619";
    private static final String USER_NAME = "root";

    private ConnectionBank(){}

    /**
     * Open connection.
     * */
    public static synchronized Connection getConn() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL,USER_NAME,PASSWORD);
    }
}

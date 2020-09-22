package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBank {
    private static final String URL = "jdbc:mysql://localhost:3306/bank?useUnicode=true&serverTimezone=UTC";
    private static final String PASSWORD = "Ruslan12619";
    private static final String USER_NAME = "root";

    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL,USER_NAME,PASSWORD);
    }
}

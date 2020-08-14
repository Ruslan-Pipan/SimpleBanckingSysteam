package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBank {
    private static Connection connection = null;
    private static String URL = "jdbc:mysql://localhost:3306/bank?useUnicode=true&serverTimezone=UTC";
    private static String PASSWORD = "Ruslan12619";
    private static String USER_NAME = "root";

    protected static Connection conn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        if (connection != null){
            return connection;
        }
        else {
            connection = DriverManager.getConnection(URL,USER_NAME, PASSWORD);
            return connection;
        }
    }
}

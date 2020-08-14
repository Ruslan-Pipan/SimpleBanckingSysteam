package db;


import java.sql.Connection;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) {
        try (Connection conn = ConnectionBank.conn();){
            System.out.print("Ok");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

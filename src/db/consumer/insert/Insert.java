package db.consumer.insert;

import db.ConnectionBank;
import mod1.com.mybanck.domain.Consumer;
import mod1.com.mybanck.domain.bankException.DontInitialisation;

import java.sql.*;

public class Insert {
    public boolean insert(Consumer consumer){
        String sql = "INSERT INTO consumer (first_name, last_name, email, phone_number, password, adress) VALUES(?, ? ,? , ?, ?, ? )";

        try (Connection conn = ConnectionBank.getConn();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ){
          preparedStatement.setString(1,consumer.getFirstName());
          preparedStatement.setString(2,consumer.getLastName());
          preparedStatement.setString(3, consumer.getEmail());
          preparedStatement.setString(4,consumer.getPhoneNumber());
          preparedStatement.setString(5, consumer.getPassword());
          preparedStatement.setString(6,consumer.getAdress());
          preparedStatement.executeUpdate();
          System.out.println("Insert good.");

        } catch (SQLException | ClassNotFoundException | DontInitialisation throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}

package db.consumer.insert;

import db.ConnectionBank;
import db.Insert;
import db.account.insert.InsertAcc;
import mod1.entety.Consumer;
import mod1.bankException.DontInitialisation;

import java.sql.*;

public class InsertCunsumer implements Insert {

    private static final String sql = "INSERT INTO consumer (first_name, last_name, email, phone_number, password, adress) VALUES(?, ? ,? , ?, ?, ? )";
    private static boolean mark;

    public boolean insert(Consumer consumer){
        String[] returnId = {"BATCHID"};

        try (Connection conn = ConnectionBank.getConn();
             PreparedStatement preparedStatement1 = conn.prepareStatement(sql, returnId);
        ){

            conn.setAutoCommit(false);
          preparedStatement1.setString(1,consumer.getFirstName());
          preparedStatement1.setString(2,consumer.getLastName());
          preparedStatement1.setString(3, consumer.getEmail());
          preparedStatement1.setString(4,consumer.getPhoneNumber());
          preparedStatement1.setString(5, consumer.getPassword());
          preparedStatement1.setString(6,consumer.getAdress());
          preparedStatement1.executeUpdate();


         try(ResultSet keys = preparedStatement1.getGeneratedKeys()) {
             if (keys.next())
                 consumer.setId(keys.getInt(1));
             else{
                 conn.rollback();
                 throw new SQLException("Creating user failed, no ID obtained.");
             }

         }

         Insert insert = new InsertAcc();

         mark = insert.insert(consumer);

         System.out.println("id in Cunsumer" + consumer.getId());

         if (mark){
             conn.commit();
             System.out.println("Insert good.");
         } else
             conn.rollback();

        } catch (SQLException | ClassNotFoundException | DontInitialisation throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}

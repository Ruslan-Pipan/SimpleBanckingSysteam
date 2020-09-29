package dao.consumer.select;

import dao.ConnectionBank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SekectAll extends SelectDefault{

    @Override
    public void select() {
        String sql = "Select * from consumer;";

        try (Connection conn = ConnectionBank.getConn();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
        ){
            System.out.println("id | \t Name \t\t Last Name \t\t Email \t\t\t\t Phone Number \t\t Password \t\t\t Adress1 \n\n");
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String firstName = resultSet.getNString(2);
                String lastName = resultSet.getNString(3);
                String email = resultSet.getNString(4);
                String phoneNumber = resultSet.getNString(5);
                String password = resultSet.getNString(6);
                String adress = resultSet.getNString(7);

                System.out.println(id + "\t\t" + firstName + ",\t\t" + lastName + ",\t\t" + email + ",\t\t" + phoneNumber + ",\t\t" + password + ",\t\t" + adress );
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}

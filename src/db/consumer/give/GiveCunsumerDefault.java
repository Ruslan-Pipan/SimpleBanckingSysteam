package db.consumer.give;

import db.ConnectionBank;
import mod1.com.mybanck.domain.Consumer;
import mod1.com.mybanck.domain.bankException.BadVerification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract class GiveCunsumerDefault implements GiveCunsumer{

    protected Consumer giveBySql(String sql){
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        ){
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                String password = resultSet.getString("password");
                String adress = resultSet.getString("adress");

                return new Consumer.CunsumerBild(firstName,lastName).setId(id).setPass(password).setEmail(email).setAdress(adress).setNumber(phoneNumber).build();
            }

        } catch (SQLException | ClassNotFoundException | BadVerification throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}

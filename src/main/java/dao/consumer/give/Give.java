package dao.consumer.give;

import dao.ConnectionBank;
import entety.Consumer;
import bankException.BadVerification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract class Give{

    protected int id;
    protected String email;

    protected String sql;


    abstract Consumer give();


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

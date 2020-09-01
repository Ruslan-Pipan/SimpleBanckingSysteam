package db.account.insert;

import db.ConnectionBank;
import db.Insert;
import db.account.GenerateBankAcc;
import mod1.com.mybanck.domain.Consumer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


abstract class InsertDefault implements Insert {

    protected boolean insertDefoult(Consumer consumer, String sql){
        try(Connection conn = ConnectionBank.getConn();
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql);
        ) {
            System.out.println("id in Account" + consumer.getId());
            preparedStatement2.setInt(1, consumer.getId());
            preparedStatement2.setDouble(2,consumer.getAccount(0).getBalance());
            consumer.getAccount(0).setBankAccount(GenerateBankAcc.generate());
            preparedStatement2.setLong(3,consumer.getAccount(0).getBankAccount());
            Thread.sleep(2000);
            preparedStatement2.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException | InterruptedException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

}


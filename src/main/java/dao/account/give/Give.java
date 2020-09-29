package dao.account.give;

import dao.ConnectionBank;
import entety.accounts.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public abstract class Give {
    protected int id;
    protected long bank_acc;
    protected String sql;

    public abstract Account give();


    protected Account giveDefaultAcc(){
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                int idCunsumer = resultSet.getInt("idCunsumer");
                double balanse = resultSet.getDouble("balance");
                long bankAcc = resultSet.getLong("bank_acc");
                Account account = new Account(balanse);
                account.setId(id);
                account.setIdCunsumer(idCunsumer);
                account.setBankAccount(bankAcc);
                return account;
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

        }
        return null;
    }
}

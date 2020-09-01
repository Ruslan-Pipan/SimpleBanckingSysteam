package db.account.give.byId;

import db.ConnectionBank;
import db.account.give.Give;
import mod1.com.mybanck.domain.accounts.Account;
import mod1.com.mybanck.domain.accounts.CheckingAccount;
import mod1.com.mybanck.domain.accounts.SavingAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GiveChecking extends Give {

    public GiveChecking(int id){
        this.id = id;
        this.sql = SQLByID.CHHECKING.getSql() + id;
    }

    @Override
    public CheckingAccount give() {
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        ) {
            if (resultSet.next()){
                Account account = giveDefaultAcc();
                double interestRate  = resultSet.getDouble("overdraft_amount");
                CheckingAccount checkingAccount = new CheckingAccount(account);
                checkingAccount.setOverdraftAmount(interestRate);
                return checkingAccount;
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}

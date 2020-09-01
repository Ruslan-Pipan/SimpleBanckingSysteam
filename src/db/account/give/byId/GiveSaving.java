package db.account.give.byId;

import db.ConnectionBank;
import db.account.give.Give;
import mod1.com.mybanck.domain.accounts.Account;
import mod1.com.mybanck.domain.accounts.SavingAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GiveSaving extends Give {

    public GiveSaving(int id) {
        this.id = id;
        this.sql = SQLByID.SAVING.getSql() + id;
    }

    @Override
    public SavingAccount give() {
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)
        ) {
            if (resultSet.next()){
                Account account = giveDefaultAcc();
                double interestRate  = resultSet.getDouble("interest_rate");
                SavingAccount savingAccount = new SavingAccount(account);
                savingAccount.setInterestRate(interestRate);
                return savingAccount;
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}

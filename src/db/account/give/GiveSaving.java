package db.account.give;

import db.ConnectionBank;
import mod1.com.mybanck.domain.accounts.Account;
import mod1.com.mybanck.domain.accounts.SavingAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GiveSaving extends Give {

    private GiveSaving(){}

    public static GiveSaving id(int id){
        GiveSaving giveSaving = new GiveSaving();
        giveSaving.id = id;
        giveSaving.sql = SQLByID.SAVING.getSql() + id;
        return giveSaving;
    }

    public static GiveSaving bankAcc(long bankAcc){
        GiveSaving giveSaving = new GiveSaving();
        giveSaving.bank_acc = bankAcc;
        giveSaving.sql = SQLByBankAcc.SAVING.getSql() + bankAcc;
        return giveSaving;
    }


    @Override
    public Account give() {
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

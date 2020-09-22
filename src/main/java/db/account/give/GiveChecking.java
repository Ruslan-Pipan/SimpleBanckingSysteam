package db.account.give;

import db.ConnectionBank;
import mod1.entety.accounts.Account;
import mod1.entety.accounts.CheckingAccount;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GiveChecking extends Give {

    private GiveChecking(){}


    public static GiveChecking id(int id){
        GiveChecking giveChecking = new GiveChecking();
        giveChecking.id = id;
        giveChecking.sql = SQLByID.CHHECKING.getSql() + id;
        return giveChecking;
    }

    public static GiveChecking bankAcc(long bankAcc){
        GiveChecking giveChecking = new GiveChecking();
        giveChecking.bank_acc = bankAcc;
        giveChecking.sql = SQLByBankAcc.CHEKING.getSql() + bankAcc;
        return giveChecking;
    }


    @Override
    public Account give() {
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)
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

package dao.repositories.implementations;

import entety.accounts.Account;
import entety.accounts.CheckingAccount;
import entety.accounts.SavingAccount;
import dao.ConnectionBank;
import dao.repositories.enums.SQLByBankAcc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class HandlerRepository {
    private static HandlerRepository handler = new HandlerRepository();

    private HandlerRepository() {
    }

    public static synchronized HandlerRepository  getInstance() {
        return handler;
    }

    protected  Account giveAcc(String sql){
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
                account.setIdConsumer(idCunsumer);
                account.setBankAccount(bankAcc);
                return account;
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

        }
        return null;
    }

    protected  SavingAccount giveSavingAcc(String sql){
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)
        ) {
            if (resultSet.next()){
                Account account = giveAcc(SQLByBankAcc.SAVING.getSql());
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

    protected CheckingAccount giveCheckingAcc(String sql){
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)
        ) {
            if (resultSet.next()){
                Account account = giveAcc(SQLByBankAcc.CHEKING.getSql());
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

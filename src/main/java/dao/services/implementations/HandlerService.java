package dao.services.implementations;

import bankException.DontInitialisation;
import bankException.OverdraftExeption;
import entety.accounts.Account;
import dao.ConnectionBank;

import java.sql.*;

class HandlerService<T extends Account> {
    protected HandlerService() {
    }

    protected boolean removeAccount(T account, String sql) {
        final int id = account.getId();
        boolean flag = false;

        try(Connection connection = ConnectionBank.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1,account.getId());
            preparedStatement.executeUpdate();
            flag = true;
            account = (T) new Account();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


        return flag;
    }


    protected boolean addAccount(T account, String sql) throws DontInitialisation {

        try(Connection connection = ConnectionBank.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, account.getIdConsumer());
            preparedStatement.setDouble(2, account.getBalance());
            preparedStatement.setLong(3, getLastBankAcc());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    protected boolean transaction(T accountFrom, T accountTo, double awt, String sql) {
        try {
            boolean flag =  accountFrom.withdraw(awt);
            if (flag){
                accountTo.deposit(awt);
                updateBalance(accountFrom,sql);
                updateBalance(accountTo,sql);
            }
            else
                throw new OverdraftExeption(awt - accountFrom.getBalance(), "Error! There are not enough funds on the balance sheet on account "
                        + accountFrom.getBalance() + " you you tried to remove " + awt);
        } catch (OverdraftExeption overdraftExeption) {
            overdraftExeption.printStackTrace();
        }
        return false;
    }


    protected boolean updateBalance(T account, String sql) {
        try (Connection connection = ConnectionBank.getConn();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setDouble(1,account.getBalance());
            preparedStatement.setInt(2,account.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    private long getLastBankAcc() throws DontInitialisation {
        generateNewBanckAcc();
        final String SQL_GET_BANK_ACC = "SELECT number_acc FROM last_number_acc;";
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_BANK_ACC);
        ){
            resultSet.next();
            return resultSet.getLong("number_acc");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        throw new DontInitialisation("Problem to initialization Bank Acc");
    }

    private void generateNewBanckAcc(){
        final String SQL_GENERATE_NEW_LAST_BANK_ACC = "UPDATE last_number_acc SET number_acc = number_acc + 1 WHERE id = 1";
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
        ){
            statement.execute(SQL_GENERATE_NEW_LAST_BANK_ACC);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}

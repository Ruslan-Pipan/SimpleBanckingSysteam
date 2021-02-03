package dao.services.implementations.accounts;

import exceptions.DontInitialisation;
import exceptions.OverdraftExeption;
import entety.accounts.Account;
import dao.ConnectionBank;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HandlerService{
    static final String SQL_WHERE_ID_CONSUMER = "SELECT * FROM accounts WHERE id_consumer = ";
    static final String SQL_WHERE_ID = "SELECT * FROM accounts WHERE id = ";
    static final String SQL_WHERE_BANK_ACC = "SELECT * FROM accounts WHERE bank_acc = ";
    static final String SQL_FOR_UPDATE = "UPDATE accounts SET balance = ? WHERE id = ?";
    protected HandlerService() {
    }

    protected boolean removeAccount(Account account, String SQL_TABLE) {
        String SQL_FOR_DELETE_ACC = "DELETE FROM accounts WHERE id = ?";
        Connection connection = null;
        try {
            connection = ConnectionBank.getConn();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try(PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_TABLE);
            PreparedStatement preparedStatement2 = connection.prepareStatement(SQL_FOR_DELETE_ACC);
        ){
            connection.setAutoCommit(false);
            preparedStatement1.setInt(1,account.getId());
            preparedStatement1.executeUpdate();

            preparedStatement2.setInt(1,account.getId());
            preparedStatement2.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return false;
    }

    protected boolean transaction(Account accountFrom, Account accountTo, double awt) {
        Connection connection = null;
        try { connection = ConnectionBank.getConn(); } catch (SQLException throwables) { throwables.printStackTrace(); }
        try(PreparedStatement pFor = connection.prepareStatement(SQL_FOR_UPDATE);
            PreparedStatement pTo = connection.prepareStatement(SQL_FOR_UPDATE);
        ){
            if (accountFrom != null && accountTo != null && accountFrom.getBalance() >= awt){
                connection.setAutoCommit(false);
                pFor.setDouble(1,accountFrom.getBalance() - awt);
                pFor.setInt(2,accountFrom.getId());
                pFor.executeUpdate();

                pTo.setDouble(1,accountTo.getBalance()+awt);
                pTo.setInt(2,accountTo.getId());
                pFor.executeUpdate();

                connection.commit();

                accountFrom.withdraw(awt);
                accountTo.deposit(awt);
                return true;
            }
        } catch (SQLException | OverdraftExeption throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        return false;
    }

    protected boolean updateBalance(Account account, String sql) {
        try (Connection connection = ConnectionBank.getConn();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setDouble(1,account.getBalance());
            preparedStatement.setInt(2,account.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException  throwable) {
            throwable.printStackTrace();
        }
        return false;
    }


    protected long getLastBankAcc() throws DontInitialisation {
        generateNewBankAcc();
        final String SQL_GET_BANK_ACC = "SELECT number_acc FROM last_number_acc;";
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_BANK_ACC);
        ){
            resultSet.next();
            return resultSet.getLong("number_acc");
        } catch (SQLException  throwables) {
            throwables.printStackTrace();
        }
        throw new DontInitialisation("Problem to initialization Bank Acc");
    }

    private void generateNewBankAcc(){
        final String SQL_GENERATE_NEW_LAST_BANK_ACC = "UPDATE last_number_acc SET number_acc = number_acc + 1 WHERE id = 1";
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
        ){
            statement.execute(SQL_GENERATE_NEW_LAST_BANK_ACC);
        } catch (SQLException  throwables) {
            throwables.printStackTrace();
        }
    }

    protected Account giveAcc(String sql){
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                int idConsumer = resultSet.getInt("id_consumer");
                double balance = resultSet.getDouble("balance");
                long bankAcc = resultSet.getLong("bank_acc");
                Account account = new Account(balance);
                account.setId(id);
                account.setIdConsumer(idConsumer);
                account.setBankAccount(bankAcc);
                return account;
            }

        } catch (SQLException  throwables) {
            throwables.printStackTrace();

        }
        return null;
    }

    protected List<Account> getListAccByIdConsumer(int id_consumer){
        List<Account> list = new ArrayList<>();
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_WHERE_ID_CONSUMER + id_consumer);
        ) {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int idConsumer = resultSet.getInt("id_consumer");
                double balance = resultSet.getDouble("balance");
                long bankAcc = resultSet.getLong("bank_acc");

                Account account = new Account();
                account.setId(id);
                account.setIdConsumer(idConsumer);
                account.setBalance(balance);
                account.setBankAccount(bankAcc);
                list.add(account);
            }
            if (list.isEmpty())
                return Collections.emptyList();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

}

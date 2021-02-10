package dao.services.implementations.accounts;

import exceptions.DontInitialisation;
import exceptions.OverdraftExeption;
import entety.accounts.Account;
import dao.ConnectionBank;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Services class which helps to avoid duplicate codes.
 * Used {@link dao.services.implementations.accounts.AccountServiceImpl }, {@link dao.services.implementations.accounts.CheckingAccountServiceImpl} and {@link dao.services.implementations.accounts.SavingAccountServiceImpl}
 *
 * @author Ruslan Pipan
 * @version 1.3
 * */
class HandlerService{
    static final String SQL_WHERE_ID_CONSUMER = "SELECT * FROM accounts WHERE id_consumer = ";
    static final String SQL_WHERE_ID = "SELECT * FROM accounts WHERE id = ";
    static final String SQL_WHERE_BANK_ACC = "SELECT * FROM accounts WHERE bank_acc = ";
    static final String SQL_FOR_UPDATE_BALANCE = "UPDATE accounts SET balance = ? WHERE id = ?";
    protected HandlerService() {
    }

    /** Removing account via JDBC transaction, because some type accounts have own table in DB and general, for that removed need delete two records with different tables.
     * @param account which needs to remove.
     * @param SQL_TABLE SQL query with a specific table.
     * */
    protected boolean removeAccount(Account account, String SQL_TABLE) {
        String SQL_FOR_DELETE_ACC = "DELETE FROM accounts WHERE id = ?";
        Connection connection = null;
        try {
            connection = ConnectionBank.getConn();

        try(PreparedStatement preparedStatementForDifferentTypeAccount = connection.prepareStatement(SQL_TABLE);
            PreparedStatement preparedStatementForAccount = connection.prepareStatement(SQL_FOR_DELETE_ACC);
        ){
            connection.setAutoCommit(false);
            preparedStatementForDifferentTypeAccount.setInt(1,account.getId());
            preparedStatementForDifferentTypeAccount.executeUpdate();

            preparedStatementForAccount.setInt(1,account.getId());
            preparedStatementForAccount.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    /**
     * Transaction.
     * @param accountFrom the account from witch money will be withdraw
     * @param accountTo the account to which the money comes.
     * @param awt amount of money.
     * */
    protected boolean transaction(Account accountFrom, Account accountTo, double awt) {
        Connection connection = null;
        try { connection = ConnectionBank.getConn(); } catch (SQLException throwables) { throwables.printStackTrace(); }
        try(PreparedStatement pFor = connection.prepareStatement(SQL_FOR_UPDATE_BALANCE);
            PreparedStatement pTo = connection.prepareStatement(SQL_FOR_UPDATE_BALANCE);
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
        } catch (SQLException | OverdraftExeption throwable) {
            throwable.printStackTrace();
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

    /**
     * Method for update balance.
     * @param account which need to update balance.
     * */
    protected boolean updateBalance(Account account) {
        try (Connection connection = ConnectionBank.getConn();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_UPDATE_BALANCE);
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

    /**
     * The method take the last bank account with the specific table.
     * */
    protected long getLastBankAcc() throws DontInitialisation {
        generateNewBankAcc();
        final String SQL_GET_BANK_ACC = "SELECT number_acc FROM last_number_acc;";
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_BANK_ACC);
        ){
            resultSet.next();
            return resultSet.getLong("number_acc");
        } catch (SQLException  throwable) {
            throwable.printStackTrace();
        }
        throw new DontInitialisation("Problem to initialization Bank Acc");
    }

    /**
     * The method generates a new unique bank acc the specific table.
     * */
    private void generateNewBankAcc(){
        final String SQL_GENERATE_NEW_LAST_BANK_ACC = "UPDATE last_number_acc SET number_acc = number_acc + 1 WHERE id = 1";
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
        ){
            statement.execute(SQL_GENERATE_NEW_LAST_BANK_ACC);
        } catch (SQLException  throwable) {
            throwable.printStackTrace();
        }
    }
    /**
     * Give account by SQL query.
     * @param sql query.
     * */
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

        } catch (SQLException  throwable) {
            throwable.printStackTrace();

        }
        return null;
    }

    /**
     * Get list accounts by id consumer.
     * @param id_consumer id consumer.
     * */
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

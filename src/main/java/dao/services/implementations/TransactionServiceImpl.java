package dao.services.implementations;

import dao.ConnectionBank;
import dao.services.implementations.accounts.AccountServiceImpl;
import dao.services.interfaces.AccountService;
import dao.services.interfaces.TransactionService;
import entety.accounts.Account;
import entety.accounts.Transaction;
import dao.ServiceConstants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    private static final AccountService<Account> acc = new AccountServiceImpl();
    @Override
    public void createTransaction(Transaction transaction) {
        final String SQL = "INSERT INTO transaction(account_to, account_from, money_transfer) VALUES (?,?,?)";
        try(Connection connection = ConnectionBank.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ) {
            preparedStatement.setInt(1,transaction.getAccountTo().getId());
            preparedStatement.setInt(2,transaction.getAccountFrom().getId());
            preparedStatement.setDouble(3,transaction.getRemittance());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Transaction> getLastSentTransactionById(int idAcc) {
        String SQL = "SELECT * FROM transaction WHERE account_from = " + idAcc;
        return getTransactionBySQL(SQL);
    }

    @Override
    public List<Transaction> getLastReceivedTransactionById(int idAcc) {
        String SQL = "SELECT * FROM transaction WHERE account_to = " + idAcc;
        return getTransactionBySQL(SQL);
    }

    @Override
    public List<Transaction> getTransactionById(int idAcc) {
        List<Transaction> transactions = new ArrayList<>(getLastReceivedTransactionById(idAcc));
        transactions.addAll(getLastSentTransactionById(idAcc));
        return transactions;
    }

    @Override
    public List<Transaction> getTransactionByAcc(long acc) {
        Account account = ServiceConstants.ACCOUNT_SERVICE.findAccountByBankAccount(acc);
        return getTransactionById(account.getId());
    }

    @Override
    public List<Transaction> getLastSentTransactionByAcc(long acc) {
        Account account = ServiceConstants.ACCOUNT_SERVICE.findAccountByBankAccount(acc);
        return getLastSentTransactionById(account.getId());
    }

    @Override
    public List<Transaction> getLastReceivedTransactionByAcc(long acc) {
        Account account = ServiceConstants.ACCOUNT_SERVICE.findAccountByBankAccount(acc);
        return getLastReceivedTransactionById(account.getId());
    }

    private List<Transaction> getTransactionBySQL(String SQL){
        List<Transaction> transactions = new ArrayList<>();
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
        ){
            while (resultSet.next()){
                Account accountTo = acc.findAccountById(resultSet.getInt("account_to"));
                Account accountFrom = acc.findAccountById(resultSet.getInt("account_from"));

                Transaction transaction = new Transaction();
                transaction.setAccountTo(accountTo);
                transaction.setAccountFrom(accountFrom);
                transaction.setId(resultSet.getInt("id"));
                transaction.setRemittance(resultSet.getDouble("money_transfer"));

                transactions.add(transaction);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return transactions;
    }
}

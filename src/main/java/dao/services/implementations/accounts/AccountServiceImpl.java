package dao.services.implementations.accounts;

import dao.ConnectionBank;
import dao.services.interfaces.AccountRepository;
import exceptions.DontInitialisation;
import entety.Consumer;
import entety.accounts.Account;
import dao.services.interfaces.AccountService;

import java.sql.*;
import java.util.List;

public class AccountServiceImpl implements AccountService<Account>, AccountRepository<Account> {
    private final static HandlerService handler = new HandlerService();
    public AccountServiceImpl() { }
    @Override
    public boolean removeAccount(Account account) {
        String SQL_FOR_DELETE = "DELETE FROM accounts WHERE id = ?";
        boolean flag = false;
        try(Connection connection = ConnectionBank.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_DELETE);
        ){
            preparedStatement.setInt(1,account.getId());
            preparedStatement.executeUpdate();
            flag = true;
            account = new Account();
        } catch (SQLException  throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addAccount(Account account) {
        String SQL_FOR_INSERT = "INSERT INTO accounts(id_consumer, balance, bank_acc) VALUES(?,?,?)";
        try(Connection connection = ConnectionBank.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FOR_INSERT);
        ) {
            preparedStatement.setInt(1, account.getIdConsumer());
            preparedStatement.setDouble(2, account.getBalance());
            preparedStatement.setLong(3, handler.getLastBankAcc());
            return true;
        } catch (SQLException | DontInitialisation throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean transaction(Account accountFrom, Account accountTo, double awt) {
      return handler.transaction(accountFrom,accountTo,awt);
    }

    @Override
    public boolean updateBalance(Account account) {
        return handler.updateBalance(account,HandlerService.SQL_FOR_UPDATE);
    }

    @Override
    public Account findAccountByBankAccount(long bankAcc) {
        return handler.giveAcc(HandlerService.SQL_WHERE_BANK_ACC + bankAcc);
    }

    @Override
    public List<Account> findAccountsByConsumer(int consumerId) {

        return handler.getListAccByIdConsumer(consumerId);
    }

    @Override
    public Account findAccountById(int id) {
        return handler.giveAcc(HandlerService.SQL_WHERE_ID + id);
    }

    @Override
    public List<Account> findAccountsByConsumer(Consumer consumer) {
        return handler.getListAccByIdConsumer(consumer.getId());
    }

}

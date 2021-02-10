package dao.services.implementations.accounts;

import dao.ConnectionBank;
import dao.services.interfaces.AccountRepository;
import entety.accounts.Account;
import exceptions.DontInitialisation;
import entety.Consumer;
import entety.accounts.CheckingAccount;
import dao.services.interfaces.AccountService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Service implements {@link dao.services.interfaces.AccountService} and {@link dao.services.interfaces.AccountRepository}
 * this service is singleton.
 * @author Ruslan Pipan
 * @version 1.3
 * */
public class CheckingAccountServiceImpl implements AccountService<CheckingAccount>, AccountRepository<CheckingAccount> {

    private final String SQL_FOR_UPDATE = "UPDATE accounts SET balance = ? WHERE id = ?";

    /** Handler {@link dao.services.implementations.accounts.HandlerService}*/
    private final HandlerService handler = new HandlerService();
    private static final AccountService<CheckingAccount> AS = new CheckingAccountServiceImpl();
    /** Get instance.*/
    public static AccountService<CheckingAccount> getInstance(){return AS;}

    private CheckingAccountServiceImpl() {
    }

    @Override
    public boolean removeAccount(CheckingAccount account) {
        String SQL_FOR_DELETE = "DELETE FROM checkin_acc WHERE id_acc = ?";
        return handler.removeAccount(account,SQL_FOR_DELETE);
    }

    @Override
    public boolean addAccount(CheckingAccount account)  {
        String SQL_FOR_INSERT_OVERDRAFT = "INSERT INTO checkin_acc(id_acc,overdraft,id_consumer) VALUES(LAST_INSERT_ID(),?,?)";
        String SQL_FOR_INSERT = "INSERT INTO accounts(id_consumer, balance, bank_acc) VALUES(?,?,?)";
        Connection connection = null;
        try {
            connection = ConnectionBank.getConn();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try(PreparedStatement preparedStatementAcc = connection.prepareStatement(SQL_FOR_INSERT);
            PreparedStatement preparedStatementCheck = connection.prepareStatement(SQL_FOR_INSERT_OVERDRAFT);
        ) {
            connection.setAutoCommit(false);
            preparedStatementAcc.setInt(1, account.getIdConsumer());
            preparedStatementAcc.setDouble(2, account.getBalance());
            preparedStatementAcc.setLong(3, handler.getLastBankAcc());
            preparedStatementAcc.executeUpdate();

            preparedStatementCheck.setDouble(1, account.getOverdraftAmount());
            preparedStatementCheck.setInt(2,account.getIdConsumer());
            preparedStatementCheck.executeUpdate();
            connection.commit();

            connection.setAutoCommit(false);

            return true;
        } catch (SQLException dontInitialisation) {
            dontInitialisation.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (DontInitialisation dontInitialisation) {
            dontInitialisation.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean transaction(CheckingAccount accountFrom, CheckingAccount  accountTo, double awt) {
        return handler.transaction(accountFrom,accountTo,awt);
    }

    @Override
    public boolean updateBalance(CheckingAccount account) {
        return handler.updateBalance(account);
    }

    @Override
    public CheckingAccount findAccountByBankAccount(long bankAcc) {
        return getAccBySQL(HandlerService.SQL_WHERE_BANK_ACC + bankAcc);
    }


    @Override
    public CheckingAccount findAccountById(int id) {
        return getAccBySQL(HandlerService.SQL_WHERE_ID + id);
    }


    @Override
    public List<CheckingAccount> findAccountsByConsumer(int consumerId) {
        String SQL = "SELECT * FROM checkin_acc WHERE id_consumer = " + consumerId;
        List<Account> accounts = handler.getListAccByIdConsumer(consumerId);
        List<CheckingAccount> checkingAccounts = new ArrayList<>();
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
        ){
            while (resultSet.next()){
                int id_acc = resultSet.getInt("id_acc");
                for (Account a: accounts) {
                    if (a.getId() == id_acc){
                       CheckingAccount checkingAccount = new CheckingAccount(a);
                       checkingAccount.setOverdraftAmount(resultSet.getDouble("overdraft"));
                       checkingAccounts.add(checkingAccount);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return checkingAccounts;
    }

    @Override
    public List<CheckingAccount> findAccountsByConsumer(Consumer consumer) {
        return findAccountsByConsumer(consumer.getId());
    }

    /**
     * Get saving acc by SQL.
     * Uses {@link dao.services.implementations.accounts.HandlerService} for obtain general account. Whereupon obtain checkin acc.
     * @param SQL_WHERE condition for issuing a record.
     * */
    private CheckingAccount getAccBySQL(String SQL_WHERE){
        Account account = handler.giveAcc(SQL_WHERE);
        if (account!=null){
            String SQL = "SELECT * FROM checkin_acc WHERE id_acc = " + account.getId();
            try(Connection connection = ConnectionBank.getConn();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL);
            ){
                resultSet.next();
                CheckingAccount checkingAccount = new CheckingAccount(account);
                checkingAccount.setOverdraftAmount(resultSet.getDouble("overdraft"));
                return checkingAccount;
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }
}

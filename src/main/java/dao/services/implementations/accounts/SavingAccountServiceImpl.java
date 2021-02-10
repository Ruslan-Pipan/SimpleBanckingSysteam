package dao.services.implementations.accounts;

import dao.ConnectionBank;
import dao.services.interfaces.AccountRepository;
import entety.accounts.Account;
import exceptions.DontInitialisation;
import entety.Consumer;
import entety.accounts.SavingAccount;
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
public class SavingAccountServiceImpl implements AccountService<SavingAccount>, AccountRepository<SavingAccount> {

    private final String SQL_FOR_UPDATE = "UPDATE saving_accounts SET balance = ? WHERE id = ?";

    /** Handler {@link dao.services.implementations.accounts.HandlerService}*/
    private final HandlerService handler = new HandlerService();
    private static AccountService<SavingAccount> AS = new SavingAccountServiceImpl();

    private SavingAccountServiceImpl() { }
    /** Get instance.*/
    public static AccountService<SavingAccount> getInstance(){return AS;}
    @Override
    public boolean removeAccount(SavingAccount account) {
        String SQL_FOR_DELETE = "DELETE FROM saving_accounts WHERE id = ?";
        return handler.removeAccount(account, SQL_FOR_DELETE);
    }

    @Override
    public boolean addAccount(SavingAccount account)  {
        //
        String SQL_FOR_INSERT_OVERDRAFT = "INSERT INTO saving_acc(id_acc,saving,id_consumer) VALUES(LAST_INSERT_ID(),?,?)";
        //
        String SQL_FOR_INSERT = "INSERT INTO accounts(id_consumer, balance, bank_acc) VALUES(?,?,?)";

        Connection connection = null;
        try {
            connection = ConnectionBank.getConn();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try(PreparedStatement preparedStatementAcc = connection.prepareStatement(SQL_FOR_INSERT);
            PreparedStatement preparedStatementSave = connection.prepareStatement(SQL_FOR_INSERT_OVERDRAFT);
        ) {
            connection.setAutoCommit(false);
            preparedStatementAcc.setInt(1, account.getIdConsumer());
            preparedStatementAcc.setDouble(2, account.getBalance());
            preparedStatementAcc.setLong(3, handler.getLastBankAcc());
            preparedStatementAcc.executeUpdate();

            preparedStatementSave.setDouble(1, account.getInterestRate());
            preparedStatementSave.setInt(2,account.getIdConsumer());

            preparedStatementSave.executeUpdate();
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
    public boolean transaction(SavingAccount accountFrom, SavingAccount accountTo, double awt) {
        return handler.transaction(accountFrom,accountTo,awt);
    }

    @Override
    public boolean updateBalance(SavingAccount account) {
        return handler.updateBalance(account);
    }


    @Override
    public SavingAccount findAccountByBankAccount(long bankAcc) {
        return getSavingAccBySQL(HandlerService.SQL_WHERE_BANK_ACC + bankAcc);
    }

    @Override
    public SavingAccount findAccountById(int id) {
        return getSavingAccBySQL(HandlerService.SQL_WHERE_ID + id);
    }

    @Override
    public List<SavingAccount> findAccountsByConsumer(int consumerId) {
        String SQL = "SELECT * FROM saving_acc WHERE id_consumer = " + consumerId;
        List<Account> accounts = handler.getListAccByIdConsumer(consumerId);
        List<SavingAccount> savingAccounts = new ArrayList<>();
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
        ){
            while (resultSet.next()){
                int id_acc = resultSet.getInt("id_acc");
                for (Account a: accounts) {
                    if (a.getId() == id_acc){
                        SavingAccount savingAccount = new SavingAccount(a);
                        savingAccount.setInterestRate(resultSet.getDouble("saving"));
                        savingAccounts.add(savingAccount);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return savingAccounts;
    }

    @Override
    public List<SavingAccount> findAccountsByConsumer(Consumer consumer) {
       return findAccountsByConsumer(consumer.getId());
    }

    /**
     * Get saving acc by SQL.
     * Uses {@link dao.services.implementations.accounts.HandlerService} for obtain general account. Whereupon obtain saving acc.
     * @param SQL_WHERE condition for issuing a record.
     * */
    private SavingAccount getSavingAccBySQL(String SQL_WHERE){
        Account account = handler.giveAcc(SQL_WHERE);
        if (account!=null){
            String SQL = "SELECT * FROM saving_acc WHERE id_acc = " + account.getId();
            try(Connection connection = ConnectionBank.getConn();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL);
            ){
                resultSet.next();
                SavingAccount savingAccount = new SavingAccount(account);
                savingAccount.setInterestRate(resultSet.getDouble("saving"));
                return savingAccount;
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }

}

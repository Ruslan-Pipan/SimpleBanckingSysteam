package dao.repositories.implementations;


import dao.repositories.implementations.accounts.AccountRepositoryImpl;
import dao.repositories.implementations.accounts.CheckingAccountRepositoryImpl;
import dao.repositories.implementations.accounts.SavingAccountRepositoryImpl;
import exceptions.BadVerification;
import entety.Consumer;
import service.Verification;
import entety.accounts.Account;
import entety.accounts.CheckingAccount;
import entety.accounts.SavingAccount;
import dao.ConnectionBank;
import dao.repositories.interfaces.AccountRepository;
import dao.repositories.interfaces.ConsumerRepository;

import java.sql.*;
import java.util.*;

public class ConsumerRepositoryImpl implements ConsumerRepository {

    private static final String SQL_SELECT_ALL_CONSUMERS = "SELECT * FROM consumer";

    public ConsumerRepositoryImpl() {
    }

    @Override
    public List<Consumer> getConsumersList() {
        List<Consumer> consumers = new ArrayList<>();
        try (Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CONSUMERS);
        ){
           while (resultSet.next()){
                consumers.add(createConsumer(resultSet));
           }
           return consumers;
        } catch (SQLException  | BadVerification throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Set<Consumer> getConsumersSet() {
        Set<Consumer> consumers = new HashSet<>();
        
        try (Connection connection = ConnectionBank.getConn();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CONSUMERS);
        ){
            while (resultSet.next()){
                consumers.add(createConsumer(resultSet));
            }
            return consumers;
        } catch (SQLException  | BadVerification throwables) {
            throwables.printStackTrace();
        }

        return Collections.emptySet();
    }


    @Override
    public Consumer findConsumerById(int id) {
        final String SQL = "SELECT * FROM consumer WHERE id =" + id;
        return giveConsumerBySQL(SQL);
    }

    @Override
    public Consumer findConsumerByBankAcc(long banckAcc) {
        final String SQL = "select accounts.bank_acc, accounts.idCunsumer From accounts WHERE bank_acc = " + banckAcc  +" \n" +
                "union\n" +
                "select checking_accounts.bank_acc, checking_accounts.idCunsumer from checking_accounts WHERE bank_acc = " + banckAcc  + "\n" +
                "union\n" +
                "select saving_accounts.bank_acc, saving_accounts.idCunsumer from saving_accounts WHERE bank_acc = " + banckAcc  +";";
        try (Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
        ){
            int id;
            if (resultSet.next()){
                id = resultSet.getInt(2);
                return findConsumerById(id);
            }
            throw new BadVerification("");
        } catch (SQLException  | BadVerification throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Consumer findConsumerByEmail(String email) throws BadVerification {
        boolean flag = Verification.verifyEmail(email);
        if (flag){
            final String SQL = "SELECT * FROM consumer WHERE email = '" + email + "';";
            return giveConsumerBySQL(SQL);
        }
        throw new BadVerification("Bad email");
    }


    private Consumer giveConsumerBySQL(String sql)  {
        try (Connection connection = ConnectionBank.getConn();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
        ){
            resultSet.next();
            return createConsumer(resultSet);
        } catch (SQLException  | BadVerification throwables) {
            throwables.printStackTrace();
        }
        return new Consumer.CunsumerBild("0","0").build();
    }


    private Consumer createConsumer(ResultSet resultSet) throws SQLException, BadVerification {
        int id = resultSet.getInt("id");
        String first_name = resultSet.getString("first_name");
        String last_name = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        String phone_number = resultSet.getString("phone_number");
        String password = resultSet.getString("password");
        String adress = resultSet.getString("adress");

        Consumer consumer = new Consumer.CunsumerBild(first_name,last_name).setId(id).setEmail(email).setNumber(phone_number).setPass(password).setAdress(adress).build();

        List<Account> list = getAllAccounts(id);

        for (Account a:list) {
            consumer.addAccount(a);
        }

        return consumer;
    }


    private List<Account> getAllAccounts(int consumerId){
        AccountRepository<Account> accountRepository = new AccountRepositoryImpl();
        AccountRepository<CheckingAccount> checkingAccountRepository = new CheckingAccountRepositoryImpl();
        AccountRepository<SavingAccount> savingAccountRepository = new SavingAccountRepositoryImpl();

        List<Account> accountList = new ArrayList<>();

        accountList.addAll(accountRepository.findAccountsByConsumerId(consumerId));
        accountList.addAll(checkingAccountRepository.findAccountsByConsumerId(consumerId));
        accountList.addAll(savingAccountRepository.findAccountsByConsumerId(consumerId));

        return accountList;
    }
}

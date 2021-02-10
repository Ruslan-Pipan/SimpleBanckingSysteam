package dao.services.implementations;

import dao.services.implementations.accounts.AccountServiceImpl;
import entety.accounts.Account;
import exceptions.BadVerification;
import entety.Consumer;
import dao.ConnectionBank;
import dao.services.interfaces.ConsumerService;
import service.Cryptography;


import java.sql.*;
import java.util.*;
/**
 * Service implements {@link dao.services.interfaces.ConsumerService} and {@link dao.services.interfaces.ConsumerRepository}
 * this service is singleton.
 * @author Ruslan Pipan
 * @version 1.1
 * */
public class ConsumerServiceImp implements ConsumerService {

    private static final String SQL_SELECT_ALL_CONSUMERS = "SELECT * FROM consumer";
    /**
     * {@link service.Cryptography} use for encrypt and decrypt data.
     * */
    private static final Cryptography cryptography = Cryptography.getInstance();
    private static final ConsumerService CS = new ConsumerServiceImp();

    private ConsumerServiceImp() { }

    /**
     * Get instance.
     * */
    public static ConsumerService getInstance(){return CS;}

    @Override
    public boolean removeConsumer(Consumer consumer) {
        final String SQL = "DELETE FROM consumer WHERE id = ?";
        try(Connection connection = ConnectionBank.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        ) {
            preparedStatement.setInt(1,consumer.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException  throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addConsumer(Consumer consumer) {
        final String SQL = "INSERT INTO consumer(first_name,last_name,email,phone_number,password,adress) VALUES (?,?,?,?,?,?)";
        try (Connection connection = ConnectionBank.getConn();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ){
            preparedStatement.setString(1,consumer.getFirstName());
            preparedStatement.setString(2,consumer.getLastName());
            preparedStatement.setString(3,cryptography.encrypt(consumer.getEmail()));
            preparedStatement.setString(4,cryptography.encrypt(consumer.getPhoneNumber()));
            preparedStatement.setString(5,cryptography.encrypt(consumer.getPassword()));
            preparedStatement.setString(6,cryptography.encrypt(consumer.getAddress()));
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException  throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editConsumerEmail(Consumer consumer, String email) {
        final String SQL = "UPDATE consumer SET email = ? WHERE id = ?";
        return editBySQL(SQL, consumer.getId(), cryptography.encrypt(email));
    }

    @Override
    public boolean editConsumerPhone(Consumer consumer, String phone) {
        final String SQL = "UPDATE consumer SET phone_number = ? WHERE id = ?";
        return editBySQL(SQL,consumer.getId(),cryptography.encrypt(phone));
    }

    @Override
    public boolean editConsumerPassword(Consumer consumer, String pass) {
        final String SQL = "UPDATE consumer SET password = ? WHERE id = ?";
        return editBySQL(SQL,consumer.getId(),cryptography.encrypt(pass));
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
        final String SQL = "SELECT * FROM accounts WHERE bank_acc = " + banckAcc;
        try (Connection connection = ConnectionBank.getConn();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL);
        ){
            int id;
            if (resultSet.next()){
                id = resultSet.getInt("id");
                return this.findConsumerById(id);
            }
            throw new BadVerification("");
        } catch (SQLException  | BadVerification throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public Consumer findConsumerByEmail(String email){
        final String SQL = "SELECT * FROM consumer WHERE email = '" + cryptography.encrypt(email) + "';";
        return giveConsumerBySQL(SQL);
    }


    /**
     * Edit consumer by SQL.
     * @param sql sql that will be updated.
     * @param id id consumer.
     * @param updateValue updateValue.
     * */
    private boolean editBySQL(String sql, int id, String updateValue){
        try (Connection connection = ConnectionBank.getConn();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1,updateValue);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }
    /**
     * Give consumer by sql.
     * @param sql sql that will by select.
     * */
    private Consumer giveConsumerBySQL(String sql)  {
        try (Connection connection = ConnectionBank.getConn();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
        ){
            resultSet.next();
            return createConsumer(resultSet);
        } catch (SQLException  | BadVerification throwable) {
            throwable.printStackTrace();
        }
        return new Consumer.ConsumerBuild("0","0").build();
    }

    /**
     * Create consumer and decrypt date.
     * @param resultSet resultSet with consumer record DB.
     * */
    private Consumer createConsumer(ResultSet resultSet) throws SQLException, BadVerification {
        int id = resultSet.getInt("id");
        String first_name = resultSet.getString("first_name");
        String last_name = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        String phone_number = resultSet.getString("phone_number");
        String password = resultSet.getString("password");
        String adress = resultSet.getString("adress");

        Consumer consumer = new Consumer.ConsumerBuild(first_name,last_name)
                .setId(id)
                .setEmail(cryptography.decrypt(email))
                .setNumber(cryptography.decrypt(phone_number))
                .setPass(cryptography.decrypt(password))
                .setAddress(cryptography.decrypt(adress))
                .build();
        List<Account> list = getAllAccounts(id);

        for (Account a:list) {
            consumer.addAccount(a);
        }
        return consumer;
    }

    /**
     * Get all accounts consumers.
     * */
    private List<Account> getAllAccounts(int consumerId){
        return AccountServiceImpl.getInstance().findAccountsByConsumer(consumerId);
    }
}

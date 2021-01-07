package dao.services.implementations;

import bankException.BadVerification;
import bankException.DontInitialisation;
import entety.Consumer;
import dao.ConnectionBank;
import dao.repositories.interfaces.ConsumerRepository;
import dao.services.interfaces.ConsumerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

public class ConsumerServiceImp implements ConsumerService {

    private ConsumerRepository repository;

    public ConsumerServiceImp(ConsumerRepository consumerRepository) {
        this.repository = consumerRepository;
    }

    @Override
    public boolean removeConsumer(Consumer consumer) {
        final String SQL = "DELETE FROM consumer WHERE id = " + consumer.getId();
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
        ) {
            statement.execute(SQL);
            return true;
        } catch (SQLException | ClassNotFoundException throwables) {
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
            preparedStatement.setString(3,consumer.getEmail());
            preparedStatement.setString(4,consumer.getPhoneNumber());
            preparedStatement.setString(5,consumer.getPassword());
            preparedStatement.setString(6,consumer.getAdress());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException | DontInitialisation throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editConsumerEmail(Consumer consumer) {
        final String SQL = "UPDATE consumer SET email = ? WHERE id = ?";
        try {
            return editBySQL(SQL, consumer.getId(), consumer.getEmail());
        } catch (DontInitialisation dontInitialisation) {
            dontInitialisation.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editConsumerPhone(Consumer consumer) {
        final String SQL = "UPDATE consumer SET phone_number = ? WHERE id = ?";
        try {
            return editBySQL(SQL,consumer.getId(),consumer.getPhoneNumber());
        } catch (DontInitialisation dontInitialisation) {
            dontInitialisation.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editConsumerPassword(Consumer consumer) {
        final String SQL = "UPDATE consumer SET password = ? WHERE id = ?";
        return editBySQL(SQL,consumer.getId(),consumer.getPassword());
    }

    @Override
    public List<Consumer> getConsumersList() {
        return repository.getConsumersList();
    }

    @Override
    public Set<Consumer> getConsumersSet() {
        return repository.getConsumersSet();
    }

    @Override
    public Consumer findConsumerById(int id) {
        return repository.findConsumerById(id);
    }

    @Override
    public Consumer findConsumerByBankAcc(long banckAcc) {
        return findConsumerByBankAcc(banckAcc);
    }

    @Override
    public Consumer findConsumerByEmail(String email) throws BadVerification {
        return repository.findConsumerByEmail(email);
    }

    private boolean editBySQL(String sql, int id, String updateValue){
        try (Connection connection = ConnectionBank.getConn();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1,updateValue);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}

package dao.repositories.implementations.accounts;

import entety.Consumer;
import entety.accounts.SavingAccount;
import dao.ConnectionBank;
import dao.repositories.enums.SQLByBankAcc;
import dao.repositories.enums.SQLByID;
import dao.repositories.interfaces.AccountRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SavingAccountRepositoryImpl implements AccountRepository<SavingAccount> {

    private HandlerRepository handler = HandlerRepository.getInstance();

    @Override
    public SavingAccount findAccountByBankAccount(long bankAcc) {
        return handler.giveSavingAcc(SQLByBankAcc.SAVING.getSql() + bankAcc);
    }

    @Override
    public List<SavingAccount> findAccountsByConsumerId(int consumerId) {
        final String SQL = "SELECT * FROM saving_accounts WHERE idCunsumer = " + consumerId;
        return getConsumersByConsumerId(SQL);
    }

    @Override
    public SavingAccount findAccountById(int id) {
        return handler.giveSavingAcc(SQLByID.SAVING.getSql() + id);
    }

    @Override
    public List<SavingAccount> findAccountsConsumer(Consumer consumer) {
        final String SQL = "SELECT * FROM saving_accounts WHERE idCunsumer = " + consumer.getId();
        return getConsumersByConsumerId(SQL);
    }

    private List<SavingAccount> getConsumersByConsumerId(String sql){
        List<SavingAccount> list = new ArrayList<>();
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        ) {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int idConsumer = resultSet.getInt("idCunsumer");
                double balance = resultSet.getDouble("balance");
                long bankAcc = resultSet.getLong("bank_acc");
                double interest_rate = resultSet.getDouble("interest_rate");

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(id);
                savingAccount.setIdConsumer(idConsumer);
                savingAccount.setBalance(balance);
                savingAccount.setBankAccount(bankAcc);
                savingAccount.setInterestRate(interest_rate);
                list.add(savingAccount);
            }
            if (list.isEmpty())
                return Collections.emptyList();

        } catch (SQLException  throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

}

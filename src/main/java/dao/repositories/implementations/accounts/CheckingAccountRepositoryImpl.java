package dao.repositories.implementations.accounts;

import entety.Consumer;
import entety.accounts.CheckingAccount;
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

public class CheckingAccountRepositoryImpl implements AccountRepository<CheckingAccount> {
    private HandlerRepository handler = HandlerRepository.getInstance();


    @Override
    public CheckingAccount findAccountByBankAccount(long bankAcc) {
        return handler.giveCheckingAcc(SQLByBankAcc.CHEKING.getSql() + bankAcc);
    }

    @Override
    public List<CheckingAccount> findAccountsByConsumerId(int consumerId) {
        final String SQL = "SELECT * FROM checking_accounts WHERE idCunsumer = " + consumerId;
        return getConsumersByConsumerId(SQL);
    }

    @Override
    public CheckingAccount findAccountById(int id) {
        return handler.giveCheckingAcc(SQLByID.CHHECKING.getSql() + id);
    }

    @Override
    public List<CheckingAccount> findAccountsConsumer(Consumer consumer) {
        final String SQL = "SELECT * FROM checking_accounts WHERE idCunsumer = " + consumer.getId();
        return getConsumersByConsumerId(SQL);
    }

    private List<CheckingAccount> getConsumersByConsumerId(String sql){
        List<CheckingAccount> list = new ArrayList<>();
        try(Connection connection = ConnectionBank.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        ) {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int idConsumer = resultSet.getInt("idCunsumer");
                double balance = resultSet.getDouble("balance");
                long bankAcc = resultSet.getLong("bank_acc");

                double overdraftAmount = resultSet.getDouble("overdraft_amount");
                CheckingAccount checkingAccount = new CheckingAccount();
                checkingAccount.setId(id);
                checkingAccount.setIdConsumer(idConsumer);
                checkingAccount.setBalance(balance);
                checkingAccount.setBankAccount(bankAcc);
                checkingAccount.setOverdraftAmount(overdraftAmount);
                list.add(checkingAccount);
            }
            if (list.isEmpty())
                return Collections.emptyList();

        } catch (SQLException  throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

}

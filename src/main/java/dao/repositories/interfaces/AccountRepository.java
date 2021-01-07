package dao.repositories.interfaces;

import entety.Consumer;
import entety.accounts.Account;

import java.util.List;


public interface AccountRepository<T extends Account>{
    Account findAccountByBankAccount(long bankAcc);
    List<T> findAccountsByConsumerId(int consumerId);
    Account findAccountById(int id);
    List<T> findAccountsConsumer(Consumer consumer);
}

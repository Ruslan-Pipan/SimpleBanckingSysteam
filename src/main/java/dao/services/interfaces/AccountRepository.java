package dao.services.interfaces;

import entety.Consumer;
import entety.accounts.Account;
import entety.accounts.Transaction;

import java.util.List;
import java.util.Queue;


public interface AccountRepository<T extends Account>{
    Account findAccountByBankAccount(long bankAcc);
    Account findAccountById(int id);
    List<T> findAccountsByConsumer(int consumerId);
    List<T> findAccountsByConsumer(Consumer consumer);
}

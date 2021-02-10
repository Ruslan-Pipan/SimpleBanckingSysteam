package dao.services.interfaces;

import entety.Consumer;
import entety.accounts.Account;
import entety.accounts.Transaction;

import java.util.List;
import java.util.Queue;

/**
 * The interface provides a contract between classes on how want interact with the table "account".
 * Interfaces for receiving general accounts.
 * Generic must be extended {@link entety.accounts.Account}.
 *
 * @author Ruslan Pipan
 * @version 1.0
 * */
public interface AccountRepository<T extends Account>{
    /**
     * Is looking for an account in the database by his bank account.
     * @return Account if bank account present in the database, else empty account.
     * @param bankAcc by bank account there will be a search  in the database.
     * */
    Account findAccountByBankAccount(long bankAcc);

    /**
     * Is looking for an account in the database by his primary id.
     * @return Account if bank account present in the database, else empty account.
     * @param id by id there will be a search in the database.
     * */
    Account findAccountById(int id);

    /**
     * Is looking for an accounts in the database by his consumer id.
     * @return List<T extends Account> if bank accounts present in the database, else empty List.
     * @param consumerId by id consumer there will be a search in the database.
     * */
    List<T> findAccountsByConsumer(int consumerId);

    /**
     * Is looking for an accounts in the database by his Consumer object.
     * @return List<T extends Account> if bank accounts present in the database, else empty List.
     * @param consumer by consumer there will be a search in the database.
     * */
    List<T> findAccountsByConsumer(Consumer consumer);
}

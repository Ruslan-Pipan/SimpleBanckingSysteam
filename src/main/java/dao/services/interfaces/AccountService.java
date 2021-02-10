package dao.services.interfaces;

import exceptions.DontInitialisation;
import entety.accounts.Account;
/**
 * The interface provides a contract between classes on how want interact with the table "account".
 * This GRUD interfaces for {@link entety.accounts.Account} control from the database. His extended {@link dao.services.interfaces.AccountRepository}
 * Generic must be extended {@link entety.accounts.Account}.
 *
 * @author Ruslan Pipan
 * @version 1.0
 * */
public interface AccountService<T extends Account> extends AccountRepository<T>{
    /**
     * This method removed account in database.
     *
     * @return true if the account was successfully removed from the database, else false.
     * @param account its generic type extended {@link entety.accounts.Account},
     *                the account which will be passed in parameters to be deleted in database,
     *                and the link will be lost.
     * */
    boolean removeAccount(T account);

    /**
     * Method added account in database.
     *
     * @return true if the account will be added to the database, else false.
     * @param account its generic type extended {@link entety.accounts.Account},
     *                the account which will be passed in parameters will check the correctness of th data,
     *                and encrypted is written to the database.
     * */
    boolean addAccount(T account) throws DontInitialisation;

    /**
     * The method to provides transaction.
     *
     * @return true if the transaction will be successful, else false.
     * @param accountFrom the account from which withdraw money.
     * @param accountTo the account to which the money comes.
     * @param awt amount of money.
     * */
    boolean transaction(T accountFrom, T accountTo, double awt);

    /**
     * The method provides an update balance account.
     *
     * @return true if the update was successful, else false.
     * @param account its generic type extended {@link entety.accounts.Account},
     *                the account which passed in parameters will be updated balance in object and in the database.
     * */
    boolean updateBalance(T account);
}

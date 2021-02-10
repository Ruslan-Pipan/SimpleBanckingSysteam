package dao.services.interfaces;

import entety.accounts.Transaction;

import java.util.List;

/**
 * The interface provides a contract between classes on how want interact with the table "transaction".
 * @author Ruslan Pipan
 * @version 1.1
 * */
public interface TransactionService {

    /**
     * This method create transaction.
     * @param transaction will be added in database.
     * */
    void createTransaction(Transaction transaction);
    /**
     * This method get last sent transactions.
     * @return List<Transaction> if all went well, else empty List.
     * @param idAcc id account which must be returned.
     * */
    List<Transaction> getLastSentTransactionById(int idAcc);

    /**
     * This method get last received transactions.
     * @return List<Transaction> if all went well, else empty List.
     * @param idAcc id account which must be returned.
     * */
    List<Transaction> getLastReceivedTransactionById(int idAcc);

    /**
     * This method get transaction by id.
     * @return List<Transaction> if all went well, else empty List.
     * @param idAcc id account which must be returned.
     * */
    List<Transaction> getTransactionById(int idAcc);

    /**
     * This method get transaction bank account.
     * @return List<Transaction> if all went well, else empty List.
     * @param acc bank account which must be returned.
     * */
    List<Transaction> getTransactionByAcc(long acc);

    /**
     * This method get last sent transaction by bank account.
     * @return List<Transaction> if all went well, else empty List.
     * @param acc bank account which must be returned.
     * */
    List<Transaction> getLastSentTransactionByAcc(long acc);

    /**
     * This method get last received transaction by bank account.
     * @return List<Transaction> if all went well, else empty List.
     * @param acc bank account which must be returned.
     * */
    List<Transaction> getLastReceivedTransactionByAcc(long acc);
}

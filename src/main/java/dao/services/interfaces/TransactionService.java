package dao.services.interfaces;

import entety.accounts.Transaction;

import java.util.List;


public interface TransactionService {
    void createTransaction(Transaction transaction);
    List<Transaction> getLastSentTransactionById(int idAcc);
    List<Transaction> getLastReceivedTransactionById(int idAcc);
    List<Transaction> getTransactionById(int idAcc);
    List<Transaction> getTransactionByAcc(long acc);
    List<Transaction> getLastSentTransactionByAcc(long acc);
    List<Transaction> getLastReceivedTransactionByAcc(long acc);
}

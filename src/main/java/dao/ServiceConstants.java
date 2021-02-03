package dao;
import dao.services.implementations.ConsumerServiceImp;
import dao.services.implementations.TransactionServiceImpl;
import dao.services.implementations.accounts.AccountServiceImpl;
import dao.services.implementations.accounts.CheckingAccountServiceImpl;
import dao.services.implementations.accounts.SavingAccountServiceImpl;
import dao.services.interfaces.AccountService;
import dao.services.interfaces.ConsumerService;
import dao.services.interfaces.TransactionService;
import entety.accounts.Account;
import entety.accounts.CheckingAccount;
import entety.accounts.SavingAccount;

public class ServiceConstants {
    static public final ConsumerService CONSUMER_SERVICE = new ConsumerServiceImp();
    static public final AccountService<Account> ACCOUNT_SERVICE = new AccountServiceImpl();
    static public final AccountService<CheckingAccount> CHECKING_ACCOUNT_SERVICE = new CheckingAccountServiceImpl();
    static public final AccountService<SavingAccount> SAVING_ACCOUNT_SERVICE = new SavingAccountServiceImpl();
    static public final TransactionService TRANSACTION_SERVICE = new TransactionServiceImpl();
}

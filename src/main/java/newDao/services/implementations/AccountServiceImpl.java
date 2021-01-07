package newDao.services.implementations;

import bankException.DontInitialisation;
import entety.Consumer;
import entety.accounts.Account;
import newDao.repositories.interfaces.AccountRepository;
import newDao.services.interfaces.AccountService;

import java.util.List;

public class AccountServiceImpl implements AccountService<Account>{

    private final String SQL_FOR_UPDATE = "UPDATE accounts SET balance = ? WHERE id = ?";

    private final HandlerService<Account> service = new HandlerService<>();
    private final AccountRepository<Account> repository;

    public AccountServiceImpl(AccountRepository<Account> repository) {
        this.repository = repository;
    }

    @Override
    public boolean removeAccount(Account account) {
        String SQL_FOR_DELETE = "DELETE FROM accounts WHERE id = ?";
        return service.removeAccount(account, SQL_FOR_DELETE);
    }

    @Override
    public boolean addAccount(Account account) {
        String SQL_FOR_INSERT = "INSERT INTO accounts(idCunsumer, balance, bank_acc) VALUES(?,?,?)";
        try {
            return service.addAccount(account, SQL_FOR_INSERT);
        } catch (DontInitialisation dontInitialisation) {
            dontInitialisation.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean transaction(Account accountFrom, Account accountTo, double awt) {
      return service.transaction(accountFrom,accountTo,awt,SQL_FOR_UPDATE);
    }

    @Override
    public boolean updateBalance(Account account) {
        return service.updateBalance(account,SQL_FOR_UPDATE);
    }

    @Override
    public Account findAccountByBankAccount(long bankAcc) {
        return repository.findAccountByBankAccount(bankAcc);
    }

    @Override
    public List<Account> findAccountsByConsumerId(int consumerId) {
        return repository.findAccountsByConsumerId(consumerId);
    }

    @Override
    public Account findAccountById(int id) {
        return repository.findAccountById(id);
    }

    @Override
    public List<Account> findAccountsConsumer(Consumer consumer) {
        return repository.findAccountsConsumer(consumer);
    }


}

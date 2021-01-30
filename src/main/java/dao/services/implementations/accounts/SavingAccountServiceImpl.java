package dao.services.implementations.accounts;

import exceptions.DontInitialisation;
import entety.Consumer;
import entety.accounts.SavingAccount;
import dao.repositories.interfaces.AccountRepository;
import dao.services.interfaces.AccountService;

import java.util.List;

public class SavingAccountServiceImpl implements AccountService<SavingAccount> {

    private final String SQL_FOR_UPDATE = "UPDATE saving_accounts SET balance = ? WHERE id = ?";

    private final HandlerService<SavingAccount> service = new HandlerService<>();
    private final AccountRepository<SavingAccount> repository;
    public SavingAccountServiceImpl(AccountRepository<SavingAccount> repository) {
        this.repository = repository;
    }

    @Override
    public boolean removeAccount(SavingAccount account) {
        String SQL_FOR_DELETE = "DELETE FROM saving_accounts WHERE id = ?";
        return service.removeAccount(account, SQL_FOR_DELETE);
    }

    @Override
    public boolean addAccount(SavingAccount account)  {
        String SQL_FOR_INSERT = "INSERT INTO saving_accounts(idCunsumer, balance, bank_acc) VALUES(?,?,?)";
        try {
            return service.addAccount(account, SQL_FOR_INSERT);
        } catch (DontInitialisation dontInitialisation) {
            dontInitialisation.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean transaction(SavingAccount accountFrom, SavingAccount accountTo, double awt) {
        return service.transaction(accountFrom,accountTo,awt,SQL_FOR_UPDATE);
    }

    @Override
    public boolean updateBalance(SavingAccount account) {
        return service.updateBalance(account,SQL_FOR_UPDATE);
    }

    @Override
    public SavingAccount findAccountByBankAccount(long bankAcc) {
        return (SavingAccount) repository.findAccountByBankAccount(bankAcc);
    }

    @Override
    public List<SavingAccount> findAccountsByConsumerId(int consumerId) {
        return repository.findAccountsByConsumerId(consumerId);
    }

    @Override
    public SavingAccount findAccountById(int id) {
        return (SavingAccount) repository.findAccountById(id);
    }

    @Override
    public List<SavingAccount> findAccountsConsumer(Consumer consumer) {
        return repository.findAccountsConsumer(consumer);
    }
}

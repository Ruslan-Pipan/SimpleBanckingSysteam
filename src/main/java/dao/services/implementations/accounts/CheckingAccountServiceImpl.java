package dao.services.implementations.accounts;

import exceptions.DontInitialisation;
import entety.Consumer;
import entety.accounts.CheckingAccount;
import dao.repositories.interfaces.AccountRepository;
import dao.services.interfaces.AccountService;

import java.util.List;

public class CheckingAccountServiceImpl implements AccountService<CheckingAccount> {

    private final String SQL_FOR_UPDATE = "UPDATE checking_accounts SET balance = ? WHERE id = ?";

    private final AccountRepository<CheckingAccount> repository;
    private final HandlerService<CheckingAccount> service = new HandlerService<>();

    public CheckingAccountServiceImpl(AccountRepository<CheckingAccount> repository) {
        this.repository = repository;
    }

    @Override
    public boolean removeAccount(CheckingAccount account) {
        String SQL_FOR_DELETE = "DELETE FROM checking_accounts WHERE id = ?";
        return service.removeAccount(account, SQL_FOR_DELETE);
    }

    @Override
    public boolean addAccount(CheckingAccount account)  {
        String SQL_FOR_INSERT = "INSERT INTO checking_accounts(idCunsumer, balance, bank_acc) VALUES(?,?,?)";
        try {
            return service.addAccount(account, SQL_FOR_INSERT);
        } catch (DontInitialisation dontInitialisation) {
            dontInitialisation.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean transaction(CheckingAccount accountFrom, CheckingAccount  accountTo, double awt) {
        return service.transaction(accountFrom,accountTo,awt,SQL_FOR_UPDATE);
    }

    @Override
    public boolean updateBalance(CheckingAccount account) {
        return service.updateBalance(account,SQL_FOR_UPDATE);
    }

    @Override
    public CheckingAccount findAccountByBankAccount(long bankAcc) {
        return (CheckingAccount) repository.findAccountByBankAccount(bankAcc);
    }

    @Override
    public List<CheckingAccount> findAccountsByConsumerId(int consumerId) {
        return  repository.findAccountsByConsumerId(consumerId);
    }

    @Override
    public CheckingAccount findAccountById(int id) {
        return (CheckingAccount) repository.findAccountById(id);
    }

    @Override
    public List<CheckingAccount> findAccountsConsumer(Consumer consumer) {
        return repository.findAccountsConsumer(consumer);
    }
}

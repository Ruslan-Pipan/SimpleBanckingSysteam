package dao.services.interfaces;

import bankException.DontInitialisation;
import entety.accounts.Account;
import dao.repositories.interfaces.AccountRepository;

public interface AccountService<T extends Account> extends AccountRepository<T>{
    boolean removeAccount(T account);
    boolean addAccount(T account) throws DontInitialisation;
    boolean transaction(T accountFrom, T accountTo, double awt);
    boolean updateBalance(T account);
}

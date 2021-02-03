package dao.services.interfaces;

import exceptions.DontInitialisation;
import entety.accounts.Account;

public interface AccountService<T extends Account> extends AccountRepository<T>{
    boolean removeAccount(T account);
    boolean addAccount(T account) throws DontInitialisation;
    boolean transaction(T accountFrom, T accountTo, double awt);
    boolean updateBalance(T account);
}

package mod1.com.mybanck.domain.accounts;


import loger.BadLog;
import loger.GoodLog;
import mod1.com.mybanck.domain.accounts.Account;
import mod1.com.mybanck.domain.bankException.OverdraftExeption;

import java.io.Serializable;

public class CheckingAccount extends Account implements Serializable {
    private double overdraftAmount;
    private static final long serialVersionUID = 1L;

    public CheckingAccount(double balance, double overdraftAmount) {
        this.balance = balance;
        this.overdraftAmount = overdraftAmount;
        GoodLog.getInstance().log("Create CheckingAccount.");
    }

    public CheckingAccount(double initBalance) {
        this(initBalance,0);
        GoodLog.getInstance().log("Create CheckingAccount with initBalance.");
    }

    @Override
    public boolean withdraw(double amt) throws OverdraftExeption {
        if (amt <= balance + overdraftAmount){
            balance -= amt;
            GoodLog.getInstance().log("CheckingAccount withdraw balance.");
            return true;
        }
        BadLog.getInstance().log("Error! Insufficient funds");
        throw new OverdraftExeption(amt - balance - overdraftAmount, "Error! Insufficient funds");
    }
}

package mod1.com.mybanck.domain.accounts;


import db.account.GenerateBankAcc;
import loger.BadLog;
import loger.GoodLog;
import mod1.com.mybanck.domain.accounts.Account;
import mod1.com.mybanck.domain.bankException.OverdraftExeption;

import java.io.Serializable;

public class CheckingAccount extends Account implements Serializable {
    private static final int version = 1;

    private double overdraftAmount;
    private static final long serialVersionUID = 1L;

    public CheckingAccount(double balance, double overdraftAmount) {
        if (balance >= 0)
            this.balance = balance;
        else
            this.balance = 0;

        if (overdraftAmount > 0)
            this.overdraftAmount = overdraftAmount;
        else
            this.overdraftAmount = 0;

        this.bankAccount = GenerateBankAcc.generate();
        GoodLog.getInstance().log("Create CheckingAccount.");
    }

    public CheckingAccount(double initBalance) {
        this(initBalance,0);
        GoodLog.getInstance().log("Create CheckingAccount with initBalance.");
    }

    public CheckingAccount(Account account){
        this.balance = account.balance;
        this.id = account.id;
        this.idCunsumer = account.idCunsumer;
        this.bankAccount = account.bankAccount;
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

    public void setOverdraftAmount(double overdraftAmount) {
        this.overdraftAmount = overdraftAmount;
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                ", id=" + id +
                ", idCunsumer=" + idCunsumer +
                ", bankAccount=" + bankAccount +
                ", balance=" + balance +
                ", overdraftAmount=" + overdraftAmount +
                '}';
    }
}

package entety.accounts;


import loger.BadLog;
import loger.GoodLog;
import exceptions.OverdraftExeption;

import java.io.Serializable;

public class CheckingAccount extends Account implements Serializable {

    private final static int type = 1;
    private double overdraftAmount;
    private static final long serialVersionUID = 1L;

    public CheckingAccount(){}

    public CheckingAccount(double balance, double overdraftAmount) {
        if (balance >= 0)
            this.balance = balance;
        else
            this.balance = 0;

        if (overdraftAmount > 0)
            this.overdraftAmount = overdraftAmount;
        else
            this.overdraftAmount = 0;
        GoodLog.getInstance().log("Create CheckingAccount.");
    }

    public CheckingAccount(double initBalance) {
        this(initBalance,0);
        GoodLog.getInstance().log("Create CheckingAccount with initBalance.");
    }

    public CheckingAccount(Account account){
        this.id = account.getId();
        this.balance = account.getBalance();
        this.idConsumer = account.getIdConsumer();
        this.bankAccount = account.getBankAccount();
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

    public double getOverdraftAmount() {
        return overdraftAmount;
    }

    public int getType() {
        return type;
    }

}

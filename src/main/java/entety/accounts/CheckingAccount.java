package entety.accounts;


import loger.BadLog;
import loger.GoodLog;
import exceptions.OverdraftExeption;

import java.io.Serializable;
/**
 * This class represent simulation account with overdraft money.
 *
 * @author Ruslan Pipan
 * @version 1.0
 * */
public class CheckingAccount extends Account implements Serializable {

    private final static int type = 1;
    private double overdraftAmount;
    private static final long serialVersionUID = 1L;

    /**
     * Create empty checking account.
     * */
    public CheckingAccount(){
        balance = 0;
        overdraftAmount = 0;
    }

    /**
     * Create checking account with balance and overdraft amount.
     * @param balance balance account.
     * @param overdraftAmount overdraft amount account.
     * */
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

    /**
     * Allow you to create checking account base on {@link entety.accounts.Account}
     * @param account it is account witch needs to be reworked to checking account.
     * */
    public CheckingAccount(Account account){
        this.id = account.getId();
        this.balance = account.getBalance();
        this.idConsumer = account.getIdConsumer();
        this.bankAccount = account.getBankAccount();
    }


    /**
     * Withdraw money from balance.
     * @param amt amount money.
     * @return true if money was withdrawn account, else false.
     * */
    @Override
    public boolean withdraw(double amt) throws OverdraftExeption {
        if (amt <= balance + overdraftAmount && amt > 0){
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

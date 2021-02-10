package entety.accounts;

import loger.GoodLog;

import java.io.Serializable;
/**
 *  This calls simulation saving account with interest rate.
 *
 * @author Ruslan Pipan
 * @version 1.0
 * */
public class SavingAccount extends Account implements Serializable {
    private final static int type = 2;
    private double interestRate;
    private static final long serialVersionUID = 1L;

    /**
     * Create empty saving account.
     * */
    public SavingAccount(){
        balance = 0;
        interestRate = 0;
    }

    /**
     * Create saving account with balance and interest rate.
     * @param balance it is balance account.
     * @param interestRate it is interest rate account.
     * */
    public SavingAccount(double balance, double interestRate) {
        if (balance >= 0)
            this.balance = balance;
        else
            this.balance = 0;

        this.interestRate = interestRate;
        GoodLog.getInstance().log("Create SavingAccount.");
    }

    /**
     * Allow you to create saving account base on {@link entety.accounts.Account}
     * @param account it is account with needs to be reworked to saving account.
     * */
    public SavingAccount(Account account) {
        this.balance = account.balance;
        this.bankAccount = account.bankAccount;
        this.id = account.id;
        this.idConsumer = account.idConsumer;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * Add interest rate.
     * */
    public void addInterestRate(){
        this.balance = this.balance + this.balance * interestRate/100;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getType() {
        return type;
    }

}

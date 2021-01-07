package entety.accounts;

import loger.GoodLog;

import java.io.Serializable;

public class SavingAccount extends Account implements Serializable {

    private double interestRate;
    private static final long serialVersionUID = 1L;


    public SavingAccount(){}

    public SavingAccount(double balance, double interestRate) {
        if (balance >= 0)
            this.balance = balance;
        else
            this.balance = 0;

        this.interestRate = interestRate;
        GoodLog.getInstance().log("Create SavingAccount.");
    }

    public SavingAccount(Account account) {
        this.balance = account.balance;
        this.bankAccount = account.bankAccount;
        this.id = account.id;
        this.idConsumer = account.idConsumer;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void addInterestRate(){
        this.balance = this.balance + this.balance * interestRate/100;
    }

    @Override
    public String toString() {
        return "SavingAccount{" +
                ", id=" + id +
                ", idCunsumer=" + idConsumer +
                ", bankAccount=" + bankAccount +
                ", balance=" + balance +
                ", interestRate=" + interestRate;
    }
}

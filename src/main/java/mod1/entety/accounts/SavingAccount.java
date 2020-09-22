package mod1.entety.accounts;

import db.account.GenerateBankAcc;
import loger.GoodLog;

import java.io.Serializable;

public class SavingAccount extends Account implements Serializable {
    private static final int version = 2;

    private double interestRate;
    private static final long serialVersionUID = 1L;

    public SavingAccount(double balance, double interestRate) {
        if (balance >= 0)
            this.balance = balance;
        else
            this.balance = 0;

        this.interestRate = interestRate;

        this.bankAccount = GenerateBankAcc.generate();
        GoodLog.getInstance().log("Create SavingAccount.");
    }

    public SavingAccount(Account account) {
        this.balance = account.balance;
        this.bankAccount = account.bankAccount;
        this.id = account.id;
        this.idCunsumer = account.idCunsumer;
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
                ", idCunsumer=" + idCunsumer +
                ", bankAccount=" + bankAccount +
                ", balance=" + balance +
                ", interestRate=" + interestRate +
                '}';
    }
}

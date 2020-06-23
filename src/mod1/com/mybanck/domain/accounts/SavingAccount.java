package mod1.com.mybanck.domain.accounts;

import loger.GoodLog;

import java.io.Serializable;

public class SavingAccount extends Account implements Serializable {
    private double interestRate;
    private static final long serialVersionUID = 1L;

    public SavingAccount(double balance, double interestRate) {
        this.balance = balance;
        this.interestRate = interestRate;
        GoodLog.getInstance().log("Create SavingAccount.");
    }

    public void addInterestRate(){
        this.balance = this.balance + this.balance * interestRate/100;
    }
}

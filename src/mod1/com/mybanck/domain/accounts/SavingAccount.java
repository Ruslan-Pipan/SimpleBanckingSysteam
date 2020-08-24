package mod1.com.mybanck.domain.accounts;

import db.account.GenerateBankAcc;
import loger.GoodLog;

import java.io.Serializable;

public class SavingAccount extends Account implements Serializable {
    private static final int version = 2;

    private double interestRate;
    private static final long serialVersionUID = 1L;

    public SavingAccount(double balance, double interestRate) {
        this.balance = balance;
        this.bankAccount = GenerateBankAcc.generate();
        this.interestRate = interestRate;
        GoodLog.getInstance().log("Create SavingAccount.");
    }

    public void addInterestRate(){
        this.balance = this.balance + this.balance * interestRate/100;
    }
}

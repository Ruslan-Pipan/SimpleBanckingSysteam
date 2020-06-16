package mod1.com.mybanck.domain;

import java.io.Serializable;

public class SavingAccount extends Account implements Serializable {
    private double interestRate;
    private static final long serialVersionUID = 1L;

    public SavingAccount(double balance, double interestRate) {
        this.balance = balance;
        this.interestRate = interestRate;
    }

    public void addInterestRate(){
        this.balance = this.balance + this.balance * interestRate/100;
    }
}

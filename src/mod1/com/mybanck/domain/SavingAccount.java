package mod1.com.mybanck.domain;

public class SavingAccount extends Account {
    private double interestRate;

    public SavingAccount(double balance, double interestRate) {
        this.balance = balance;
        this.interestRate = interestRate;
    }

    public void addInterestRate(){
        this.balance = this.balance + this.balance * interestRate/100;
    }
}

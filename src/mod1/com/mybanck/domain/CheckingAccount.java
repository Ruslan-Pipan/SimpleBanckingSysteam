package mod1.com.mybanck.domain;

public class CheckingAccount extends Account {
    private double overdraftAmount;

    public CheckingAccount(double balance, double overdraftAmount) {
        this.balance = balance;
        this.overdraftAmount = overdraftAmount;
    }

    public CheckingAccount(double initBalance) {
        this(initBalance,0);
    }

    @Override
    public boolean withdraw(double amt){
        if (amt <= balance + overdraftAmount){
            balance -= amt;
            return true;
        }
        return false;
    }
}

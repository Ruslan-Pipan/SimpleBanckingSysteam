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
    public boolean withdraw(double amt) throws OverdraftExeption {
        if (amt <= balance + overdraftAmount){
            balance -= amt;
            return true;
        }
        throw new OverdraftExeption(amt - balance - overdraftAmount, "Error! Insufficient funds");
    }
}

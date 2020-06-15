package mod1.com.mybanck.domain;



public class Account {

    protected double balance;

    /**
     * Conctructor that provides initial balance
     * @param  balance a must be positive
     * */
    public Account(double balance) {
        if (balance >= 0){
            this.balance = balance;
        }else {
            this.balance = 0;
        }
    }
    public Account(){
        balance = 0;
    }

    /**
     * Method to add money to account
     * @param amt a positive amount if maney
     * */
    public boolean deposit(double amt){
       if (amt > 0){
           balance += amt;
           return true;
       }
       return false;
    }

    /**
     *  Method to withdraw money
     * @param amt a positive amount of money
     * */
    public boolean withdraw(double amt) throws OverdraftExeption {
       if (amt <= balance){
           balance -= amt;
           return true;
       }
       throw new OverdraftExeption(amt - balance, "Error! There are not enough funds on the balance sheet on account " + balance + " you you tried to remove " + amt);
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }

}

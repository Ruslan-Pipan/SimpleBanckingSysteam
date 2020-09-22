package mod1.entety.accounts;


import loger.BadLog;
import loger.GoodLog;
import mod1.bankException.OverdraftExeption;

import java.io.Serializable;

public class Account implements Serializable {
    private static final int version = 0;

    protected int id;
    protected int idCunsumer;
    protected long bankAccount;
    protected double balance;

    private static final long serialVersionUID = 1L;


    /**
     * Conctructor that provides initial balance
     * @param  balance a must be positive
     * */
    public Account(double balance) {
        if (balance >= 0){
            this.balance = balance;
            GoodLog.getInstance().log("Create account");
        }else {
            this.balance = 0;
        }
//        this.bankAccount = GenerateBankAcc.generate();
    }
    public Account(){
        this.balance = 0;
//        this.bankAccount = GenerateBankAcc.generate();
    }

    /**
     * Method to add money to account
     * @param amt a positive amount if maney
     * */
    public boolean deposit(double amt){
       if (amt > 0){
           balance += amt;
           GoodLog.getInstance().log("Money add account");
           return true;
       }
        BadLog.getInstance().log("Money dont add account");
       return false;
    }

    /**
     *  Method to withdraw money
     * @param amt a positive amount of money
     * */
    public boolean withdraw(double amt) throws OverdraftExeption {
       if (amt <= balance){
           balance -= amt;
           GoodLog.getInstance().log("Monney withdraw");
           return true;
       }
       BadLog.getInstance().log("Monney dont withdraw");
       throw new OverdraftExeption(amt - balance, "Error! There are not enough funds on the balance sheet on account " + balance + " you you tried to remove " + amt);
    }

    public double getBalance() {
        GoodLog.getInstance().log("Return balancs");
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getBankAccount() {
        return bankAccount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCunsumer() {
        return idCunsumer;
    }

    public void setIdCunsumer(int idCunsumer) {
        this.idCunsumer = idCunsumer;
    }

    public void setBankAccount(long bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", idCunsumer=" + idCunsumer +
                ", bankAccount=" + bankAccount +
                ", balance=" + balance +
                '}';
    }
}

package entety.accounts;


import loger.BadLog;
import loger.GoodLog;
import exceptions.OverdraftExeption;

import java.io.Serializable;
import java.util.Objects;


/**
 * If you want to create new type accounts should to extends {@link entety.accounts.Account}*
 * @author Ruslan Pipan
 * @version 1.1
 * */
public class Account implements Serializable {
    protected int id;
    protected int idConsumer;
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
    }
    public Account(){
        this.balance = 0;
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

        if (amt <= balance && amt > 0) {
            balance -= amt;
            GoodLog.getInstance().log("Monney withdraw");
            return true;
        }
        BadLog.getInstance().log("Monney dont withdraw");
        throw new OverdraftExeption(amt,"Its over fro your accounts.");
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

    public int getIdConsumer() {
        return idConsumer;
    }

    public void setIdConsumer(int idConsumer) {
        this.idConsumer = idConsumer;
    }

    public void setBankAccount(long bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "Account: " + bankAccount + "</br>" +
                "id consumer: " + idConsumer + "</br>";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return getId() == account.getId() &&
                getIdConsumer() == account.getIdConsumer() &&
                getBankAccount() == account.getBankAccount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdConsumer(), getBankAccount());
    }
}

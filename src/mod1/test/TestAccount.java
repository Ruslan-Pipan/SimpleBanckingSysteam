package mod1.test;

import mod1.com.mybanck.domain.Account;
import mod1.com.mybanck.domain.CheckingAccount;
import mod1.com.mybanck.domain.OverdraftExeption;

public class TestAccount {
    public static void main(String[] args) {
        Account account = new Account(500);
        CheckingAccount checkingAccount = new CheckingAccount(500,100);

        try {
            checkingAccount.withdraw(500);
        } catch (OverdraftExeption overdraftExeption) {
            overdraftExeption.printStackTrace();
        }
        System.out.println("Account balance is " + checkingAccount.getBalance());
    }
}

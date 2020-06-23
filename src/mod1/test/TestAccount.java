package mod1.test;

import mod1.com.mybanck.domain.accounts.Account;
import mod1.com.mybanck.domain.accounts.CheckingAccount;
import mod1.com.mybanck.domain.bankException.OverdraftExeption;

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

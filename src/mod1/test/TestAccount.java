package mod1.test;

import mod1.com.mybanck.domain.accounts.Account;
import mod1.com.mybanck.domain.accounts.CheckingAccount;
import mod1.com.mybanck.domain.bankException.OverdraftExeption;

public class TestAccount {
    public static void main(String[] args) {
        Account account = new Account(500);
        Account account2 = new Account(500);
        Account account3 = new Account(500);
        CheckingAccount checkingAccount = new CheckingAccount(500,100);

        try {
            checkingAccount.withdraw(500);
        } catch (OverdraftExeption overdraftExeption) {
            overdraftExeption.printStackTrace();
        }
        System.out.println("Account balance is " + checkingAccount.getBalance());
        System.out.println(account.getBankAccount());
        System.out.println(account2.getBankAccount());
        System.out.println(account3.getBankAccount());
        System.out.println(checkingAccount.getBankAccount());
    }
}

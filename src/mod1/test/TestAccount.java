package mod1.test;

import mod1.com.mybanck.domain.Account;

public class TestAccount {
    public static void main(String[] args) {
        Account account = new Account(-55);
        System.out.println("Account balance is " + account.getBalance());
    }
}

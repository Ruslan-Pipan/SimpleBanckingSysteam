package mod1.test;

import mod1.com.mybanck.domain.Bank;
import mod1.com.mybanck.domain.CheckingAccount;
import mod1.com.mybanck.domain.Cunstomer;
import mod1.com.mybanck.domain.SavingAccount;

public class TestBank {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Cunstomer cunstomer1 = new Cunstomer("John Doe");
        Cunstomer cunstomer2 = new Cunstomer("Ruslan Pipan");

        SavingAccount savingCunstumer2 = new SavingAccount(1000,7);
        CheckingAccount checkingCunstumer1 = new CheckingAccount(500,100);
        SavingAccount savingCunstumer1 = new SavingAccount(10_000,7);

        cunstomer1.addAccount(checkingCunstumer1);
        cunstomer1.addAccount(savingCunstumer1);
        cunstomer2.addAccount(savingCunstumer2);

        bank.addCunstomer(cunstomer1);
        bank.addCunstomer(cunstomer2);

        System.out.println(bank.getCunstomer(1));
    }
}

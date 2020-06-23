package mod1.test;

import mod1.com.mybanck.domain.*;
import mod1.com.mybanck.domain.accounts.CheckingAccount;
import mod1.com.mybanck.domain.accounts.SavingAccount;
import mod1.com.mybanck.domain.bankException.BadVerification;

public class TestBank {
    public static void main(String[] args) throws BadVerification {
        Bank bank = Bank.getBank();
        Consumers cunstomer1 = new Consumers.Cunsumer("Ruslan", "Pipan").setAdress("Krovinka").setNumber("380686536489").build();
        Consumers cunstomer2 = new Consumers.Cunsumer("Oleg", "Xolod").setNumber("3806848864").build();

        SavingAccount savingCunstumer2 = new SavingAccount(1000,7);
        CheckingAccount checkingCunstumer1 = new CheckingAccount(500,100);
        SavingAccount savingCunstumer1 = new SavingAccount(10_000,7);

        cunstomer1.addAccount(checkingCunstumer1);
        cunstomer1.addAccount(savingCunstumer1);
        cunstomer2.addAccount(savingCunstumer2);

        bank.addConsumer(cunstomer1);
        bank.addConsumer(cunstomer2);

        System.out.println(bank.getCunstomer(1));
    }
}

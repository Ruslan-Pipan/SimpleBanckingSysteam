package mod1.test;

import mod1.com.mybanck.domain.*;

public class TestBank {
    public static void main(String[] args) throws BadVerification {
        Bank bank = Bank.getBank();
        Cunsumers cunstomer1 = new Cunsumers.Cunsumer("Ruslan", "Pipan").setAdress("Krovinka").setNumber("380686536489").build();
        Cunsumers cunstomer2 = new Cunsumers.Cunsumer("Oleg", "Xolod").setNumber("3806848864").build();

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

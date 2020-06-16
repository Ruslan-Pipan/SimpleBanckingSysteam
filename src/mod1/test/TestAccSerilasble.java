package mod1.test;

import mod1.com.mybanck.domain.BadVerification;
import mod1.com.mybanck.domain.CheckingAccount;
import mod1.com.mybanck.domain.Cunsumers;
import mod1.com.mybanck.domain.SavingAccount;
import mod1.serilaseble.CunsumerDeserilizable;
import mod1.serilaseble.CunsumerSerilizable;

import java.io.*;

public class TestAccSerilasble {
    public static void main(String[] args) throws BadVerification {
        Cunsumers cunstomer = new Cunsumers.Cunsumer("Ruslan", "Pipan").setAdress("Krovinka").setNumber("(380)686 536 489").build();
        SavingAccount saving = new SavingAccount(1000,7);
        CheckingAccount checking = new CheckingAccount(500,100);
        cunstomer.addAccount(saving);
        cunstomer.addAccount(checking);

        File file = new File("D:\\Навчання\\SimpleStsteamBanck\\src\\mod1\\test\\serilasbleTest.bin");
        CunsumerSerilizable.serialesbleCunstomer(file,cunstomer);
        System.out.println(CunsumerDeserilizable.deserilizableCunstomer(file));
    }
}

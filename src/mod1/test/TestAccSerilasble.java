package mod1.test;

import mod1.com.mybanck.domain.bankException.BadVerification;
import mod1.com.mybanck.domain.accounts.CheckingAccount;
import mod1.com.mybanck.domain.Consumer;
import mod1.com.mybanck.domain.accounts.SavingAccount;
import mod1.com.mybanck.domain.serilaseble.CunsumerDeserilizable;
import mod1.com.mybanck.domain.serilaseble.CunsumerSerilizable;

import java.io.*;

public class TestAccSerilasble {
    public static void main(String[] args) throws BadVerification {
        Consumer cunstomer = new Consumer.CunsumerBild("Ruslan", "Pipan").setAdress("Krovinka").setNumber("(380)686 536 489").build();
        SavingAccount saving = new SavingAccount(1000,7);
        CheckingAccount checking = new CheckingAccount(500,100);
        cunstomer.addAccount(saving);
        cunstomer.addAccount(checking);

        File file = new File("D:\\Навчання\\SimpleStsteamBanck\\src\\mod1\\test\\serilasbleTest.bin");
        CunsumerSerilizable.serializationConsumer(file,cunstomer);
        System.out.println(CunsumerDeserilizable.deserializationCunstomer(file));
    }
}

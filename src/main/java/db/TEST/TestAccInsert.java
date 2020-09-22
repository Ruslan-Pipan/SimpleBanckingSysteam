package db.TEST;

import db.Insert;
import db.account.insert.InsertSavingAcc;
import mod1.entety.Consumer;
import mod1.bankException.BadVerification;

public class TestAccInsert {
    public static void main(String[] args) throws BadVerification {
        Insert insert = new InsertSavingAcc();

        Consumer consumer = new Consumer.CunsumerBild("TestSavingAcc","TestSavingAcc").setNumber("380686536489").setPass("TestSavingAcc12").setEmail("TestSavingAcc@mail.ru").build();

    }
}

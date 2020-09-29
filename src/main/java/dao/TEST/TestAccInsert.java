package dao.TEST;

import dao.Insert;
import dao.account.insert.InsertSavingAcc;
import entety.Consumer;
import bankException.BadVerification;

public class TestAccInsert {
    public static void main(String[] args) throws BadVerification {
        Insert insert = new InsertSavingAcc();

        Consumer consumer = new Consumer.CunsumerBild("TestSavingAcc","TestSavingAcc").setNumber("380686536489").setPass("TestSavingAcc12").setEmail("TestSavingAcc@mail.ru").build();

    }
}

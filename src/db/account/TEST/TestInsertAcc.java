package db.account.TEST;

import db.Insert;
import db.account.insert.InsertAcc;
import mod1.com.mybanck.domain.Consumer;
import mod1.com.mybanck.domain.bankException.BadVerification;

public class TestInsertAcc {
    public static void main(String[] args) throws BadVerification {
        Insert insert = new InsertAcc();
        insert.insert(new Consumer.CunsumerBild("Tets45","Tets45").setPass("Test154548").setEmail("test@mail.ru").setNumber("380686536489").build());
    }
}

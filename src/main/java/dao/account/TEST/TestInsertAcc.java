package dao.account.TEST;

import dao.Insert;
import dao.account.insert.InsertAcc;
import entety.Consumer;
import bankException.BadVerification;

public class TestInsertAcc {
    public static void main(String[] args) throws BadVerification {
        Insert insert = new InsertAcc();
        insert.insert(new Consumer.CunsumerBild("Tets45","Tets45").setPass("Test154548").setEmail("test@mail.ru").setNumber("380686536489").build());
    }
}

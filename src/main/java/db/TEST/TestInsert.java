package db.TEST;

import db.Insert;
import db.consumer.insert.InsertCunsumer;
import mod1.entety.Consumer;
import mod1.bankException.BadVerification;


public class TestInsert {
    public static void main(String[] args) throws BadVerification{


        Insert insert = new InsertCunsumer();
        Consumer consumer = new Consumer.CunsumerBild("Test2","Tets17").setEmail("tests20@gmail.com").setNumber("30820161619").setPass("Tetst77777").build();
        System.out.println(consumer.getId());
        insert.insert(consumer);
        System.out.println(consumer.getId());

    }
}

package dao.TEST;

import dao.Insert;
import dao.consumer.insert.InsertCunsumer;
import entety.Consumer;
import bankException.BadVerification;


public class TestInsert {
    public static void main(String[] args) throws BadVerification{


        Insert insert = new InsertCunsumer();
        Consumer consumer = new Consumer.CunsumerBild("Test2","Tets17").setEmail("tests20@gmail.com").setNumber("30820161619").setPass("Tetst77777").build();
        System.out.println(consumer.getId());
        insert.insert(consumer);
        System.out.println(consumer.getId());

    }
}

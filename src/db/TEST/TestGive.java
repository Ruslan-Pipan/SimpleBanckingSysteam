package db.TEST;

import db.consumer.give.GiveById;
import db.consumer.give.GiveCunsumer;

public class TestGive {
    public static void main(String[] args) {
        GiveCunsumer giveCunsumer = new GiveById(4);
        System.out.println(giveCunsumer.give());
    }
}

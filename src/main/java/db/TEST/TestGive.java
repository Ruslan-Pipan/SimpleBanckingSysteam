package db.TEST;

import db.consumer.give.GiveCunsumer;

public class TestGive {
    public static void main(String[] args) {
        GiveCunsumer giveCunsumer = GiveCunsumer.id(1);
        System.out.println(giveCunsumer.give());
    }
}

package db.account.TEST;

import db.account.give.Give;
import db.account.give.GiveChecking;


public class TestGive {
    public static void main(String[] args) {
        Give give = GiveChecking.id(1);
        System.out.println(give.give());
    }
}

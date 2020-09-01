package db.account.TEST;

import db.account.give.Give;
import db.account.give.byId.GiveChecking;
import db.account.give.byId.GiveSaving;

public class TestGive {
    public static void main(String[] args) {

        Give giveAcc = new GiveChecking(1);
        System.out.println(giveAcc.give());
    }
}

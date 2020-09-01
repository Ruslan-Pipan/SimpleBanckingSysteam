package db.account.TEST;

import db.account.give.Give;
import db.account.give.GiveAcc;
import db.account.give.GiveChecking;
import mod1.com.mybanck.domain.accounts.Account;


public class TestGive {
    public static void main(String[] args) {
        Give give = GiveChecking.id(1);
        System.out.println(give.give());
    }
}

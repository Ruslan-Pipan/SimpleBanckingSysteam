package dao.account.TEST;

import dao.account.give.Give;
import dao.account.give.GiveChecking;


public class TestGive {
    public static void main(String[] args) {
        Give give = GiveChecking.id(1);
        System.out.println(give.give());
    }
}

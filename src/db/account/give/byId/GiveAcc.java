package db.account.give.byId;

import db.account.give.Give;
import mod1.com.mybanck.domain.accounts.Account;


public class GiveAcc extends Give {

    public GiveAcc(int id) {
        this.id = id;
        this.sql = SQLByID.ACC.getSql() + id;
    }

    @Override
    public Account give() {
        return giveDefaultAcc();
    }
}

package db.account.give;

import mod1.entety.accounts.Account;


public class GiveAcc extends Give {

    private GiveAcc(){}

    public static GiveAcc bankAcc(long bankAcc){
        GiveAcc giveAcc = new GiveAcc();
        giveAcc.bank_acc = bankAcc;
        giveAcc.sql = SQLByBankAcc.ACC.getSql() + bankAcc;
        return giveAcc;
    }

    public static GiveAcc id(int id){
        GiveAcc giveAcc = new GiveAcc();
        giveAcc.id = id;
        giveAcc.sql = SQLByID.ACC.getSql() + id;
        return giveAcc;
    }

    @Override
    public Account give() {
        return giveDefaultAcc();
    }
}

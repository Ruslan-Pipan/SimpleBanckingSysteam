package db.account.insert;

import mod1.com.mybanck.domain.Consumer;


public class InsertAcc extends InsertDefault {

    private static final String sql = "INSERT INTO accounts(idCunsumer, balance, bank_acc) VALUES(?,?,?)";

    public boolean insert(Consumer consumer){
        return insertDefoult(consumer,sql);
    }

}

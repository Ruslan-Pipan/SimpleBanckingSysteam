package db.account.insert;

import mod1.com.mybanck.domain.Consumer;

public class InsertSavingAcc extends InsertDefault{

    private static final String sql = "INSERT INTO saving_account(idCunsumer, balance, bank_acc) VALUES(?,?,?)";

    @Override
    public boolean insert(Consumer consumer) {
        return insertDefoult(consumer,sql);
    }
}

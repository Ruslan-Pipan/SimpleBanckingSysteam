package db.account.insert;

import mod1.com.mybanck.domain.Consumer;

public class InsertCheckingAcc extends InsertDefault {

    private static final String sql = "INSERT INTO checking_accounts(idCunsumer, balance, bank_acc) VALUES(?,?,?)";

    @Override
    public boolean insert(Consumer consumer) {
        return insertDefoult(consumer,sql);
    }
}

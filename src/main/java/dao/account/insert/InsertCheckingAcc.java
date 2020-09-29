package dao.account.insert;

import entety.Consumer;

public class InsertCheckingAcc extends InsertDefault {

    private static final String sql = "INSERT INTO checking_accounts(idCunsumer, balance, bank_acc) VALUES(?,?,?)";

    @Override
    public boolean insert(Consumer consumer) {
        return insertDefoult(consumer,sql);
    }
}

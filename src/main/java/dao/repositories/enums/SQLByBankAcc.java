package dao.repositories.enums;

public enum SQLByBankAcc {
    ACC("SELECT * FROM accounts WHERE bank_acc = "),
    SAVING("SELECT * FROM saving_accounts WHERE bank_acc = "),
    CHEKING("SELECT * FROM checking_accounts WHERE bank_acc = ");

    private String sql;

    SQLByBankAcc(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}

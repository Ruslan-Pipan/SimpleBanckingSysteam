package db.account.give.byId;

public enum SQLByID {
    ACC("SELECT * FROM accounts WHERE id = "),
    SAVING("SELECT * FROM saving_accounts WHERE id = "),
    CHHECKING("SELECT * FROM checking_accounts WHERE id = ");

    private String sql;

    SQLByID(String sql){
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}

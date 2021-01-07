package newDao.repositories.enums;

public enum  SQLByConsumerId {
    ACC("SELECT * FROM accounts WHERE idCunsumer = "),
    SAVING("SELECT * FROM saving_accounts WHERE idCunsumer  = "),
    CHHECKING("SELECT * FROM checking_accounts WHERE idCunsumer  = ");

    private String sql;

    SQLByConsumerId(String sql){
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}

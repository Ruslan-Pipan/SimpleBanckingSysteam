package dao.account;

import dao.ConnectionBank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public final class GenerateBankAcc {
    private GenerateBankAcc(){}

    public static long generate(){
        return getBankAcc() + 1;
    }

    private static long getBankAcc(){
        String sql = "SELECT bank_acc FROM accounts ORDER BY bank_acc;";
        try(Connection conn = ConnectionBank.getConn();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(sql);
        ) {
            if (resultSet.last()){
                return resultSet.getLong("bank_acc");
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return 0;

    }
}

package db.consumer;

import db.ConnectionBank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class LastId {
    private LastId(){}

    public static int getLastId(){
        String sql = "SELECT id FROM consumer";
        try(Connection conn = ConnectionBank.getConn();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CLOSE_CURSORS_AT_COMMIT);
            ResultSet resultSet = statement.executeQuery(sql);
        ) {
            if (resultSet.first())
                return resultSet.getInt("id");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return 1;
    }
}

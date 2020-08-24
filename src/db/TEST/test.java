package db.TEST;


import db.account.GenerateBankAcc;


import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println(GenerateBankAcc.generate());
    }

}

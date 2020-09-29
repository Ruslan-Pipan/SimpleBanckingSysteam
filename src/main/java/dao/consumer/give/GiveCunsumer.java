package dao.consumer.give;

import entety.Consumer;

public class GiveCunsumer extends Give {

    private GiveCunsumer(){}

    public static GiveCunsumer id(int id){
        GiveCunsumer giveCunsumer = new GiveCunsumer();
        giveCunsumer.id = id;
        giveCunsumer.sql = "SELECT * FROM consumer WHERE id = " + id;
        return giveCunsumer;
    }
    public static GiveCunsumer email(String email, String pass){
        GiveCunsumer giveCunsumer = new GiveCunsumer();
        giveCunsumer.email = email;
        giveCunsumer.sql = "SELECT * FROM consumer WHERE email = '" + email + "' AND password = '" + pass + "'";
        return giveCunsumer;
    }

    @Override
    public Consumer give() {
        return giveBySql(sql);
    }
}

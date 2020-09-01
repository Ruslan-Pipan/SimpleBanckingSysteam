package db.consumer.give;

import mod1.com.mybanck.domain.Consumer;

public class GiveCunsumer extends Give {

    private GiveCunsumer(){}

    public static GiveCunsumer id(int id){
        GiveCunsumer giveCunsumer = new GiveCunsumer();
        giveCunsumer.id = id;
        giveCunsumer.sql = "SELECT * FROM consumer WHERE id = " + id;
        return giveCunsumer;
    }

    @Override
    public Consumer give() {
        return giveBySql(sql);
    }
}

package db.consumer.give;

import mod1.com.mybanck.domain.Consumer;

public class GiveById extends GiveCunsumerDefault {
    private final int id;
    private final String sql;

    public GiveById(int id) {
        this.id = id;
        this.sql = "SELECT * FROM consumer WHERE id = " + id;
    }

    @Override
    public Consumer give() {
        return giveBySql(sql);
    }
}

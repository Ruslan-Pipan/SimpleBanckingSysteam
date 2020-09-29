package dao.consumer.select;


public class SelectByName extends SelectDefault{

    private  String name;
    private  String lasts_name;

    private  String sql;


    public SelectByName(String name, String lasts_name) {
        this.name = name;
        this.lasts_name = lasts_name;
        this.sql = "Select * FROM consumer WHERE first_name = '" + name + "' AND last_name = '" + lasts_name +"'";
    }

    @Override
    public void select() {
        resultDefolt(sql);
    }
}

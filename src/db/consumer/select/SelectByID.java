package db.consumer.select;


public class SelectByID extends Select{
    private  int id;
    private String sql = "Select * FROM consumer WHERe id = " + id;

    public SelectByID(int id) {
        this.id = id;
    }

    @Override
    public void select() {
        resultDefolt(sql);
    }
}

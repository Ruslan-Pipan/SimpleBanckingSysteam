package loger;

public class GoodLog extends Loger {
    private static String green = "green";
    private static final GoodLog goodLog = new GoodLog();
    protected GoodLog() {
        super(green);
    }

    public static GoodLog getInstance() {
        return goodLog;
    }
}

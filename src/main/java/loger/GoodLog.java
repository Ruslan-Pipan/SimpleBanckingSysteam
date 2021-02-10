package loger;

/**
 * This simple relation good log.
 * @author Ruslan Pipan
 * @version 1.0
 * */
public class GoodLog extends Logger {
    private static final String green = "green";
    private static final GoodLog goodLog = new GoodLog();
    /**
     * It is singleton.
     * */
    private GoodLog() {
        super(green);
    }
    /**
     * Get instance.
     * */
    public static GoodLog getInstance() {
        return goodLog;
    }
}

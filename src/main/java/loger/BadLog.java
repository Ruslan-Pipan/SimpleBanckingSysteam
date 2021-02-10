package loger;

/**
 * This simple realization bad log.
 * @author Ruslan Pipan
 * @version 1.0
 * */
public class BadLog extends Logger {
    private static final String red = "red";
    private static final BadLog badLog = new BadLog();

    /**
     * It is a singleton.
     * */
    private BadLog() {
        super(red);
    }

    /**
     * Get instance.
     * */
    public static BadLog getInstance() {
        return badLog;
    }
}

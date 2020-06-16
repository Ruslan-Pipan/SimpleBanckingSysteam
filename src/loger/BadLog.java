package loger;

public class BadLog extends Loger {
    private static String red = "red";
    private static final BadLog badLog = new BadLog();

    protected BadLog() {
        super(red);
    }

    public static BadLog getInstance() {
        return badLog;
    }
}

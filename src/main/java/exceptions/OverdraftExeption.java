package exceptions;


/**
 * Exception for  situations where funds are insufficient.
 */
public class OverdraftExeption extends Exception {
    private double deficit;

    public double getDeficit() {
        return deficit;
    }

    public OverdraftExeption(double deficit, String masseg) {
        super(masseg);
        this.deficit = deficit;
    }

}

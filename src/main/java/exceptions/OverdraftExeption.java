package exceptions;


/**
 * Exception for  situations where funds are insufficient.
 * @author Ruslan Pipan
 * @version 1.0
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

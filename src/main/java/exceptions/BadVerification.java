package exceptions;

/**
 * For bad user input.
 *
 * @author Ruslan Pipan
 * @version 1.0
 * */
public class BadVerification extends Exception {
    public BadVerification(String masseg){
        super(masseg);
    }
}

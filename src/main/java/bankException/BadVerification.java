package bankException;

public class BadVerification extends Exception {
    public BadVerification(String masseg){
        super(masseg);
    }
}

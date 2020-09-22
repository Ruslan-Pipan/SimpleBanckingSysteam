package mod1.bankException;

public class BadVerification extends Exception {
    public BadVerification(String masseg){
        super(masseg);
    }
}

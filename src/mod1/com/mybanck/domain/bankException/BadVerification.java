package mod1.com.mybanck.domain.bankException;

public class BadVerification extends Exception {
    public BadVerification(String masseg){
        super(masseg);
    }
}

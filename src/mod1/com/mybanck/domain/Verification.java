package mod1.com.mybanck.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Verification - verify : email, phone number.
 * */
public class Verification {
    private Verification(){}

    public static boolean verifyEmail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean verifyPhoneNumber(String number){
        String regex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
}

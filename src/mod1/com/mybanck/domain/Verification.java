package mod1.com.mybanck.domain;

import loger.BadLog;
import loger.GoodLog;

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
        if (matcher.matches())
            GoodLog.getInstance().log("Good email");
        else
            BadLog.getInstance().log("Bad email.");
        return matcher.matches();
    }

    public static boolean verifyPhoneNumber(String number){
        String regex = "(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        if (matcher.matches())
            GoodLog.getInstance().log("Good number.");
        else
            BadLog.getInstance().log("Bad number.");
        return matcher.matches();
    }

    public static boolean verifyPassword(String pass){
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pass);
        if (matcher.matches())
            GoodLog.getInstance().log("Good pass.");
        else
            BadLog.getInstance().log("Bad pass.");
        return matcher.matches();
    }
}

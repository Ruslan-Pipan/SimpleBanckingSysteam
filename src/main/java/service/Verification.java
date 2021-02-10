package service;

import loger.BadLog;
import loger.GoodLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provide verify data users.
 *
 * @author Ruslan Pipan
 * @version 1.1
 * */
public class Verification {
    /**
     * It is a service class, it is no possible to create an instance.
     * */
    private Verification(){}

    /**
     * This method for verifying email.
     *
     * @param email string that will be verified.
     * @return true if the email is really email, else false.
     * */
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

    /**
     * This method for verifying phone number.
     *
     * @param number string that will be verified.
     * @return true if the phone number is really phone number, else false.
     * */
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

    /**
     * This method for to check a good password.
     *
     * @param pass string that will be verified.
     * @return true if the password is really good password, else false.
     * */
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

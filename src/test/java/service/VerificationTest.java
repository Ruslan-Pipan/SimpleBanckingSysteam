package service;

import org.junit.Test;

import static org.junit.Assert.*;

public class VerificationTest {

    /*
    * Verifying email.
    * */
    @Test
    public void verifying_empty_email_test(){
        assertFalse(Verification.verifyEmail(""));
    }

    @Test
    public void verifying_email_without_at_sign_test(){
        assertFalse(Verification.verifyEmail("rus.pipan.com"));
    }

    @Test
    public void verifying_email_without_domain_name_test(){
        assertFalse(Verification.verifyEmail("ruspipan@"));
    }

    @Test
    public void verifying_good_email_test(){
        assertTrue(Verification.verifyEmail("ruslan@uker.net"));
    }

    /*
     * Verifying phone number.
     * */

    @Test
    public void verifying_empty_phone_number_test(){
        assertFalse(Verification.verifyPhoneNumber(""));
    }

    @Test
    public void verifying_phone_number_with_not_enough_charters_test(){
        assertFalse(Verification.verifyPhoneNumber("3658865"));
    }


    @Test
    public void verifying_good_phone_number_without_baskets_test(){
        assertTrue(Verification.verifyPhoneNumber("380686536489"));
    }

    @Test
    public void verifying_good_phone_number_with_baskets_and_dash_test(){
        assertTrue(Verification.verifyPhoneNumber("(380)686-536-489"));
    }

    @Test
    public void verifying_good_phone_number_with_baskets_and_dash_and_plus_test(){
        assertTrue(Verification.verifyPhoneNumber("+(380)686-536-489"));
    }

    /*
    * Verifying password.
    * */

    @Test
    public void verifying_empty_password_test(){
        assertFalse(Verification.verifyPassword(""));
    }

    @Test
    public void verifying_password_with_not_enough_charters_test(){
        assertFalse(Verification.verifyPassword("Aa1"));
    }

    @Test
    public void verifying_password_without_numbers_test(){
        assertFalse(Verification.verifyPassword("Dsfdsdaagfa"));
    }

    @Test
    public void verifying_password_without_upper_case_charter_test(){
        assertFalse(Verification.verifyPassword("dsafdsfd12"));
    }

    @Test
    public void verifying_good_password(){
        assertTrue(Verification.verifyPassword("Asdfdgf12"));
    }
}

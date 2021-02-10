package entety.accounts;

import exceptions.OverdraftExeption;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {

    private Account account;

    @Before
    public void createAccount(){
        account = new Account(0);
    }

    @Test
    public void test_create_account_with_negative_number(){
        account = new Account(-55);
        assertEquals(0, account.balance, 0.0);
    }

    @Test
    public void test_deposit_negative_money(){
        assertFalse(account.deposit(-5));
    }

    @Test
    public void test_deposit_zero_money(){
        assertFalse(account.deposit(0));
    }

    @Test
    public void test_deposit_positive_money(){
        assertTrue(account.deposit(150));
    }

    @Test(expected = OverdraftExeption.class)
    public void test_withdraw_when_not_enough_money() throws OverdraftExeption {
        assertFalse(account.withdraw(55));
    }

    @Test(expected = OverdraftExeption.class)
    public void test_withdraw_when_negative_money() throws OverdraftExeption {
        assertFalse(account.withdraw(-55));
    }

}

package junitlab.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import junitlab.bank.impl.GreatSavingsBank;

public class BankTest {

    @Test
    public void testOpenAccount() throws AccountNotExistsException
    {
        GreatSavingsBank gsb = new GreatSavingsBank();
        String acc = gsb.openAccount();
        Assertions.assertEquals(0, gsb.getBalance(acc));
    }

    @Test
    public void testUniqueAccount()
    {
        GreatSavingsBank gsb = new GreatSavingsBank();
        String acc1 = gsb.openAccount();
        String acc2 = gsb.openAccount();
        Assertions.assertNotEquals(acc1, acc2);
    }

    @Test
    public void testInvalidAccount()
    {
        GreatSavingsBank gsb = new GreatSavingsBank();
        Assertions.assertThrows(AccountNotExistsException.class, () -> gsb.getBalance("12345"));
    }

    @Test
    public void testDeposit() throws Exception
    {
        GreatSavingsBank gsb = new GreatSavingsBank();
        String acc = gsb.openAccount();
        gsb.deposit(acc, 2000);
        Assertions.assertEquals(2000, gsb.getBalance(acc));
    }

    @Test
    public void testConstructorWithAccountNumber() {
        String accountNumber = "123456789";
        BankException exception = new BankException(accountNumber) {};

        Assertions.assertEquals(accountNumber, exception.getAccountNumber());
    }

    @Test
    public void testConstructorWithAccountNumberAndMessage() {
        String accountNumber = "123456789";
        String message = "Test error message";
        BankException exception = new BankException(accountNumber, message) {};

        Assertions.assertEquals(accountNumber, exception.getAccountNumber());
        Assertions.assertEquals(message, exception.getMessage());
    }

}
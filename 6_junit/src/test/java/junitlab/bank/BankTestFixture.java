package junitlab.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import junitlab.bank.impl.GreatSavingsBank;

public class BankTestFixture {

    GreatSavingsBank gsb;
    String acc1, acc2;

    @BeforeEach
    public void setUp() throws AccountNotExistsException
    {
        gsb = new GreatSavingsBank();
        acc1 = gsb.openAccount();
        acc2 = gsb.openAccount();

        gsb.deposit(acc1, 1500);
        gsb.deposit(acc2, 12000);
    }

    @Test
    public void testTransfer() throws Exception
    {
        gsb.transfer(acc2, acc1, 3456);
        Assertions.assertEquals(4956, gsb.getBalance(acc1));
        Assertions.assertEquals(8544, gsb.getBalance(acc2));
    }

    @Test
    public void testTransferWithoutEnoguhFunds()
    {
        Assertions.assertThrows(NotEnoughFundsException.class, () -> gsb.transfer(acc1, acc2, 3456));
    }

}

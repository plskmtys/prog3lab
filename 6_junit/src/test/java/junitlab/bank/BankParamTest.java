package junitlab.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import junitlab.bank.impl.GreatSavingsBank;

import java.util.stream.Stream;

public class BankParamTest {

    @ParameterizedTest
    @MethodSource("roundingDataProvider")
    public void testWithdrawRounding(int amount, int rounded) {
        GreatSavingsBank bank = new GreatSavingsBank();
        String testacc = bank.openAccount();
        Assertions.assertDoesNotThrow(() -> bank.deposit(testacc, 10000));

        Assertions.assertDoesNotThrow(() -> bank.withdraw(testacc, amount));

        int expectedBalance = 10000 - rounded;
        long actualBalance = Assertions.assertDoesNotThrow(() -> bank.getBalance(testacc));

        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> roundingDataProvider() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(1100, 1100),
                org.junit.jupiter.params.provider.Arguments.of(1101, 1100),
                org.junit.jupiter.params.provider.Arguments.of(1149, 1100),
                org.junit.jupiter.params.provider.Arguments.of(1150, 1200),
                org.junit.jupiter.params.provider.Arguments.of(1151, 1200),
                org.junit.jupiter.params.provider.Arguments.of(1199, 1200),
                org.junit.jupiter.params.provider.Arguments.of(1200, 1200)
        );
    }
}

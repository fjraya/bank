package com.iobuilders.bank.account.domain;

import com.iobuilders.bank.account.application.ports.input.WalletException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WalletTest {
    Wallet wallet = new Wallet("an user id", 0.0);

    @Test
    public void whenCalledToDepositCorrectTransactionCreation() throws WalletException {
        wallet.deposit(new Money(300.0));
        wallet.deposit(new Money(200.0));
        Money actual = wallet.getBalance();
        double expected = 500.0;
        Assertions.assertEquals(expected, actual.value());
    }

    @Test
    public void whenCalledToDepositWithNegativeAmountThrow() {
        Assertions.assertThrows(WalletException.class, () -> wallet.deposit(new Money(-70.0)));
    }

    @Test
    public void whenCalledToWithdrawWithEnoughtFoundsCorrectWithDrawing() throws WalletException {
        wallet.deposit(new Money(300.0));
        wallet.withdraw(new Money(200.0));
        Money actual = wallet.getBalance();
        double expected = 100.0;
        Assertions.assertEquals(expected, actual.value());
    }

    @Test
    public void whenCalledtoWithdrawWithInsuficientFundsThrow() {
        Assertions.assertThrows(WalletException.class, () -> wallet.withdraw(new Money(200.0)));
    }

    @Test
    public void whenCalledToDepositCorrectTransactionItemCreation() throws WalletException {
        wallet.deposit(new Money(300.0));
        wallet.deposit(new Money(300.0));
        int actual = wallet.getTransactions().size();
        int expected = 2;
        Assertions.assertEquals(expected, actual);
    }
}

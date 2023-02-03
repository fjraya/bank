package com.iobuilders.bank.account.services;

import com.iobuilders.bank.account.application.ports.input.WalletException;
import com.iobuilders.bank.account.application.ports.output.WalletPort;
import com.iobuilders.bank.account.application.service.WalletService;
import com.iobuilders.bank.account.domain.Money;
import com.iobuilders.bank.account.domain.Wallet;
import com.iobuilders.bank.account.infrastructure.controller.CreateWalletCommand;
import com.iobuilders.bank.account.infrastructure.controller.DepositWalletCommand;
import com.iobuilders.bank.account.infrastructure.controller.TransferCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class WalletServiceTest {
    WalletPort walletPort = mock(WalletPort.class);
    WalletService walletService = new WalletService(walletPort);

    @Test
    public void whenCalledToCreateWalletCorrectCallToOutputPort() {
        walletService.createWallet("an user Id", new CreateWalletCommand(0.0));
        verify(walletPort, times(1)).saveWallet(any());
    }

    @Test
    public void whenCalledToDepositWithNotExistentWalletThrow() {
        when(walletPort.getWalletByUserIdAndWalletId(any(), any())).thenReturn(Optional.empty());
        Assertions.assertThrows(WalletException.class, () -> walletService.deposit("an user Id", "an id", new DepositWalletCommand(78.0)));
    }

    @Test
    public void whenCalledToDepositWithExistentWalletCorrectCallToSave() throws WalletException {
        when(walletPort.getWalletByUserIdAndWalletId(any(), any())).thenReturn(Optional.of(new Wallet("an user id", 45.0)));
        walletService.deposit("an user Id", "wallet id", new DepositWalletCommand(45.0));
        verify(walletPort, times(1)).saveWallet(any());
    }

    @Test
    public void whenCalledToTransferWithExistentAccountsCorrectCallToSave() throws WalletException {
        when(walletPort.getWalletByUserIdAndWalletId("an user id", "1")).thenReturn(Optional.of(new Wallet("an user id", 45.0)));
        when(walletPort.getWalletByUserIdAndWalletId("an user id", "2")).thenReturn(Optional.of(new Wallet("an user id", 48.0)));
        walletService.transfer("an user id", new TransferCommand("1", "2", 43.2));
        verify(walletPort, times(2)).saveWallet(any());
    }

    @Test
    public void whenCalledToTransferWithUnexistentAccountThrow() {
        when(walletPort.getWalletByUserIdAndWalletId(any(), any())).thenReturn(Optional.empty());
        when(walletPort.getWalletByUserIdAndWalletId("an user id", "2")).thenReturn(Optional.of(new Wallet("an user id", 48.0)));
        Assertions.assertThrows(WalletException.class, () -> walletService.transfer("an user id", new TransferCommand("1", "2", 43.2)));
    }

    @Test
    public void whenCalledToGetWalletReturnCorrect() throws WalletException {
        Wallet wallet = new Wallet("2", new HashSet<>(), 0.0, "an user id");
        wallet.deposit(new Money(78.0));
        wallet.deposit(new Money(78.0));
        wallet.deposit(new Money(78.0));
        when(walletPort.getWalletByUserIdAndWalletId("an user id", "2")).thenReturn(Optional.of(wallet));
        Wallet actual = walletService.getWallet("an user id", "2");
        Assertions.assertEquals("2", actual.getId());
        Assertions.assertEquals(234.0, actual.getBalance().value());
    }



}

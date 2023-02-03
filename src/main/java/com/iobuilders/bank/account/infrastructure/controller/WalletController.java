package com.iobuilders.bank.account.infrastructure.controller;


import com.iobuilders.bank.account.application.ports.input.WalletException;
import com.iobuilders.bank.account.application.service.WalletService;
import com.iobuilders.bank.account.domain.Wallet;
import com.iobuilders.bank.user.application.ports.input.InvalidTokenException;
import com.iobuilders.bank.user.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalletController {
    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/wallet")
    public ResponseEntity<String> createWallet(@RequestHeader("Authorization") String authorization, @RequestBody CreateWalletCommand createWalletCommand) throws InvalidTokenException {
        String userId = userService.checkUser(authorization);
        Wallet wallet = walletService.createWallet(userId, createWalletCommand);
        return ResponseEntity.ok(wallet.getId());
    }

    @PutMapping("/api/wallet/{id}")
    public void deposit(@RequestHeader("Authorization") String authorization, @PathVariable String id, @RequestBody DepositWalletCommand depositWalletCommand) throws WalletException, InvalidTokenException {
        String userId = userService.checkUser(authorization);
        walletService.deposit(userId, id, depositWalletCommand);
    }

    @PutMapping("/api/wallet/transfer")
    public void transfer(@RequestHeader("Authorization") String authorization, @RequestBody TransferCommand transferCommand) throws InvalidTokenException, WalletException {
        String userId = userService.checkUser(authorization);
        walletService.transfer(userId, transferCommand);
    }

    @GetMapping("/api/wallet/{id}")
    public ResponseEntity<WalletDTO> getWallet(@RequestHeader("Authorization") String authorization, @PathVariable String id) throws InvalidTokenException, WalletException {
        String userId = userService.checkUser(authorization);
        Wallet wallet = walletService.getWallet(userId, id);
        return ResponseEntity.ok(WalletDTO.fromDomain(wallet));
    }


}

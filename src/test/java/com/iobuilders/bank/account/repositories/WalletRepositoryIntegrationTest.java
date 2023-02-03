package com.iobuilders.bank.account.repositories;


import com.iobuilders.bank.BankApplication;
import com.iobuilders.bank.account.infrastructure.out.TransactionEntity;
import com.iobuilders.bank.account.infrastructure.out.WalletEntity;
import com.iobuilders.bank.account.infrastructure.out.WalletRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest(classes = BankApplication.class)
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:test.properties")
public class WalletRepositoryIntegrationTest {
    @Autowired
    private WalletRepository walletRepository;

    @BeforeEach
    public void setUp() {
        tearDown();
    }

    private WalletEntity setupInitialData() {
        TransactionEntity transactionEntity = new TransactionEntity("1", 23.4, LocalDateTime.now(), null);
        HashSet<TransactionEntity> transactions = new HashSet<>();
        transactions.add(transactionEntity);
        return walletRepository.save(new WalletEntity("2", transactions, 0.0, "an user id"));
    }

    @AfterEach
    public void tearDown() {
        walletRepository.deleteAll();
    }

    @Test
    public void whenCalledSaveCorrectInsertion() {
        long guard = walletRepository.count();
        Assertions.assertEquals(0, guard, "table wallets must be empty");
        setupInitialData();
        Optional<WalletEntity> wallet = walletRepository.findById("2");
        Assertions.assertEquals(true, wallet.isPresent());
    }

    @ParameterizedTest
    @MethodSource("combinationInput")
    public void whenCalledToFindByUserIdAndIdWithExistentUserIdAndIdCorrectResult(String id, boolean expected) {
        setupInitialData();
        Optional<WalletEntity> wallet = walletRepository.findByUserIdAndId("an user id", id);
        Assertions.assertEquals(expected, wallet.isPresent());
    }

    private static Stream<Arguments> combinationInput() {
        return Stream.of(
                Arguments.arguments("2", true),
                Arguments.arguments("3", false)
        );
    }
}

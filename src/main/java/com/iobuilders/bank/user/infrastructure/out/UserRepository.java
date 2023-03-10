package com.iobuilders.bank.user.infrastructure.out;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    public Optional<UserEntity> findByToken(String token);
}

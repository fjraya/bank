package com.iobuilders.bank.user.application.ports.output;

import com.iobuilders.bank.user.application.ports.input.RegisterUserException;
import com.iobuilders.bank.user.domain.User;

import java.util.Optional;

public interface UserPort {
    User createUser(User user) throws RegisterUserException;
    Optional<User> getUser(String id);

    Optional<User> getUserByToken(String token);
}

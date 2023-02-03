package com.iobuilders.bank.user.application.service;

import com.iobuilders.bank.user.application.ports.input.*;
import com.iobuilders.bank.user.application.ports.output.UserPort;
import com.iobuilders.bank.user.domain.InvalidValueException;
import com.iobuilders.bank.user.domain.User;
import com.iobuilders.bank.user.infrastructure.controller.CreateUserCommand;

import java.util.Optional;

public class UserService implements RegisterUser, GetUser, CheckUser {
    private UserPort userPort;
    public UserService(UserPort createUserPort) {
        this.userPort = createUserPort;
    }
    @Override
    public User createUser(CreateUserCommand user) throws RegisterUserException, InvalidValueException {
        return userPort.createUser(new User(user.getUsername(), user.getPassword()));
    }

    @Override
    public User getUser(String id) throws UserException {
        Optional<User> user = userPort.getUser(id);
        if (user.isEmpty()) throw new UserException("User with id " + id + " doesn't exist");
        return user.get();
    }

    public String checkUser(String authorization) throws InvalidTokenException {
        Optional<User> user = userPort.getUserByToken(authorization);
        if (user.isEmpty()) throw new InvalidTokenException("Invalid token: " + authorization);
        return user.get().getUsername().value();
    }
}

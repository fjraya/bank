package com.iobuilders.bank.user.application.ports.input;

import com.iobuilders.bank.user.domain.InvalidValueException;
import com.iobuilders.bank.user.domain.User;
import com.iobuilders.bank.user.infrastructure.controller.CreateUserCommand;

public interface RegisterUser {
    User createUser(CreateUserCommand user) throws RegisterUserException, InvalidValueException;
}

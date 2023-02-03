package com.iobuilders.bank.user.application.ports.input;

import com.iobuilders.bank.user.domain.User;

public interface GetUser {
    User getUser(String id) throws UserException;
}

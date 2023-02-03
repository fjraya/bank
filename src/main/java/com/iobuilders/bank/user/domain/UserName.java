package com.iobuilders.bank.user.domain;

public class UserName implements ValueObject<String> {
    private String username;

    public UserName(String username) throws InvalidValueException {
        if (username == null) throw new InvalidValueException("Username cannot be null");
        if (username.isBlank()) throw new InvalidValueException("Username cannot be empty");
        this.username = username;
    }

    @Override
    public String value() {
        return username;
    }
}

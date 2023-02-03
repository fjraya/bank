package com.iobuilders.bank.user.domain;

public class Password implements ValueObject<String> {
    private String password;
    public Password(String password) throws InvalidValueException {
        if (password == null) throw new InvalidValueException("Password cannot be null");
        if (password.isBlank()) throw new InvalidValueException("Password cannot be empty");
        if (password.length() < 8) throw new InvalidValueException("Password is too short. 8 or more chars");
        PasswordEncoder passwordEncoder = new DummyPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    @Override
    public String value() {
        return password;
    }
}

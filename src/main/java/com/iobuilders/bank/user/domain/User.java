package com.iobuilders.bank.user.domain;

import java.util.UUID;

public class User {
    private String id;
    private UserName username;
    private Password password;
    private Token token;

    public User(String name, String pwd) throws InvalidValueException {
        this.id = UUID.randomUUID().toString();
        username = new UserName(name);
        password = new Password(pwd);
        token = Token.newToken();
    }

    public User(String id, String name, String pwd, String tok) throws InvalidValueException {
        this.id = id;
        username = new UserName(name);
        password = new Password(pwd);
        token = new Token(tok);
    }

    public UserName getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    public Token getToken() {
        return token;
    }

    public String getId() {
        return this.id;
    }
}

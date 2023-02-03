package com.iobuilders.bank.user.infrastructure.out;

import com.iobuilders.bank.user.domain.InvalidValueException;
import com.iobuilders.bank.user.domain.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {
    @Id
    private String id;

    private String username;

    private String password;

    private String token;

    public UserEntity() {

    }

    public UserEntity(String id, String username, String password, String token) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public static UserEntity fromUser(User user) {
        return new UserEntity(user.getId(), user.getUsername().value(), user.getPassword().value(), user.getToken().value());
    }

    public User toUser() throws InvalidValueException {
        return new User(this.id, this.username, this.password, this.token);
    }
}

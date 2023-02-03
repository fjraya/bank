package com.iobuilders.bank.user.infrastructure.out;

import com.iobuilders.bank.user.application.ports.input.RegisterUserException;
import com.iobuilders.bank.user.application.ports.output.UserPort;
import com.iobuilders.bank.user.domain.InvalidValueException;
import com.iobuilders.bank.user.domain.User;

import java.util.Optional;

public class UserAdapter implements UserPort {
    private UserRepository userRepository;

    public UserAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) throws RegisterUserException {
        UserEntity userEntity = userRepository.save(UserEntity.fromUser(user));
        try {
            return userEntity.toUser();
        } catch (InvalidValueException e) {
            throw new RegisterUserException("Invalid values in user");
        }
    }

    @Override
    public Optional<User> getUser(String id)  {
         Optional<UserEntity> userEntity = userRepository.findById(id);
        return extractUser(userEntity);
    }

    @Override
    public Optional<User> getUserByToken(String token) {
        Optional<UserEntity> user = userRepository.findByToken(token);
        return extractUser(user);
    }

    private Optional<User> extractUser(Optional<UserEntity> userEntity) {
        if (userEntity.isPresent()) {
            try {
                return Optional.of(userEntity.get().toUser());
            } catch (InvalidValueException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}

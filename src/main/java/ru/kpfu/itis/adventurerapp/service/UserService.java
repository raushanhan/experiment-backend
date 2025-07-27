package ru.kpfu.itis.adventurerapp.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.adventurerapp.entity.User;

import java.util.Optional;


public interface UserService {
    Optional<User> findByEmail(String email);
    User save(User user);
}

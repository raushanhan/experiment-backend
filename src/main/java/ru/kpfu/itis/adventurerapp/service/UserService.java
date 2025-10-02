package ru.kpfu.itis.adventurerapp.service;

import ru.kpfu.itis.adventurerapp.entity.User;
import java.util.Optional;


public interface UserService {
    Optional<User> findByEmail(String email);
    User save(User user);
    Optional<User> findById(Long id);
}

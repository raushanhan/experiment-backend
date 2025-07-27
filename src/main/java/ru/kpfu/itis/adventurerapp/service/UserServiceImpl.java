package ru.kpfu.itis.adventurerapp.service;


import org.springframework.stereotype.Service;
import ru.kpfu.itis.adventurerapp.entity.User;
import ru.kpfu.itis.adventurerapp.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    public final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}

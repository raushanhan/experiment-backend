package ru.kpfu.itis.adventurerapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.adventurerapp.dto.UserDto;
import ru.kpfu.itis.adventurerapp.entity.User;
import ru.kpfu.itis.adventurerapp.repository.UserRepository;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(@AuthenticationPrincipal User user) {
        if (user != null) {
            log.info("LOG - Getting current user");
            UserDto dto = getUserDtoFromUser(user);
            log.info(dto.toString());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.map(user -> ResponseEntity.ok(getUserDtoFromUser(user))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/isPresent/{email}")
    public ResponseEntity<Boolean> checkIfEmailExists(@PathVariable String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        Boolean res = userOptional.isPresent();
        return ResponseEntity.ok(res);
    }

    private UserDto getUserDtoFromUser(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}

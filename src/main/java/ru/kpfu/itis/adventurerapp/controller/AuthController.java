package ru.kpfu.itis.adventurerapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.adventurerapp.dto.UserLoginRequestDTO;
import ru.kpfu.itis.adventurerapp.dto.UserLoginResponseDTO;
import ru.kpfu.itis.adventurerapp.dto.UserRegisterRequestDTO;
import ru.kpfu.itis.adventurerapp.entity.User;
import ru.kpfu.itis.adventurerapp.security.JwtTokenProvider;
import ru.kpfu.itis.adventurerapp.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequestDTO dto) {
        if (userService.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email уже зарегистрирован");
        }

        String hashedPassword = passwordEncoder.encode(dto.getPassword());

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(hashedPassword);
        user.setCreatedAt(LocalDateTime.now());

        userService.save(user);

        return ResponseEntity.ok("Пользователь зарегистрирован");
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginRequestDTO dto) {

        log.info("LOG - entered login");
        Optional<User> userOpt = userService.findByEmail(dto.getEmail());

        if (userOpt.isEmpty() || !passwordEncoder.matches(dto.getPassword(), userOpt.get().getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserLoginResponseDTO("Не удалось залогиниться"));
        }

        String token = jwtTokenProvider.generateToken(dto.getEmail());

        log.info("LOG - finished login");
        return ResponseEntity.ok(new UserLoginResponseDTO(token));
    }
}


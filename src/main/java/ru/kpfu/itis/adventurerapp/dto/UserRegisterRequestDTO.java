package ru.kpfu.itis.adventurerapp.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegisterRequestDTO {
    private String username;
    private String email;
    private String password;
}

package ru.kpfu.itis.adventurerapp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("hashed_password_1"));
        System.out.println(new BCryptPasswordEncoder().encode("hashed_password_2"));
        System.out.println(new BCryptPasswordEncoder().encode("hashed_password_3"));
    }
}

package ru.kpfu.itis.adventurerapp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes;

    @Override
    public String toString() {
        return "User{" +
                "createdAt=" + createdAt +
                ", passwordHash='" + passwordHash + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
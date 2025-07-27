package ru.kpfu.itis.adventurerapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Double latitude;
    private Double longitude;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", imageUrl='" + imageUrl + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", user=" + user +
                '}';
    }
}
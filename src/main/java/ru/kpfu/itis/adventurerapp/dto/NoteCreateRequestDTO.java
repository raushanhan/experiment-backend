package ru.kpfu.itis.adventurerapp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoteCreateRequestDTO {
    private String title;
    private String content;
    private Double latitude;
    private Double longitude;
    private String imageUrl;
    private LocalDateTime createdAt;
}

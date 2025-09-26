package ru.kpfu.itis.adventurerapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.adventurerapp.dto.NoteCreateRequestDTO;
import ru.kpfu.itis.adventurerapp.dto.NoteResponseDTO;
import ru.kpfu.itis.adventurerapp.entity.Note;
import ru.kpfu.itis.adventurerapp.entity.User;
import ru.kpfu.itis.adventurerapp.mapper.NoteMapperImplementation;
import ru.kpfu.itis.adventurerapp.service.NoteService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notes")
public class NotesController {

    private final NoteService noteService;
    private final NoteMapperImplementation noteMapper;

    @PostMapping
    public ResponseEntity<NoteResponseDTO> createNote(@RequestBody NoteCreateRequestDTO dto,
                                                      @AuthenticationPrincipal User user) {
        log.info("LOG - Creating note {}", dto.toString());
        Note note = noteMapper.toEntity(dto);
        note.setUser(user);
        note.setImageUrl("uploads/" + user.getId() + "/" + dto.getImageUrl());

        Note saved = noteService.save(note);
        log.info("LOG - added note {}", saved.toString());
        return ResponseEntity.ok(noteMapper.toDto(saved));
    }

    @GetMapping
    public ResponseEntity<List<NoteResponseDTO>> getUserNotes(@AuthenticationPrincipal User user) {
        List<Note> notes = noteService.findAllByUser(user);
        log.info("LOG - gave notes");
        return ResponseEntity.ok(noteMapper.toDtoList(notes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseDTO> getUserNote(@PathVariable Long id, @AuthenticationPrincipal User user) {
        log.info("LOG - entered getUserNote");
        Optional<Note> note = noteService.findByIdAndUser(id, user);
        if (note.isPresent()) {
            log.info("LOG - gave note for user {}", note.toString());
            return ResponseEntity.ok(noteMapper.toDto(note.get()));
        }
        else return ResponseEntity.notFound().build();
    }


    @DeleteMapping
    public boolean deleteUserNote(@RequestParam long id,
                                  @AuthenticationPrincipal User user) {
        log.info("LOG - deleting note (id={})", id);
        return noteService.deleteByIdAndUser(id, user);
    }
}

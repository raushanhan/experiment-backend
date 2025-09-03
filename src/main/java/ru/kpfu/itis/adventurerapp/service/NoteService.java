package ru.kpfu.itis.adventurerapp.service;

import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.adventurerapp.entity.Note;
import ru.kpfu.itis.adventurerapp.entity.User;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    Note save(Note note);
    List<Note> findAllByUser(User user);
    Optional<Note> findByIdAndUser(Long id, User user);
    boolean deleteByIdAndUser(Long id, User user);
}

package ru.kpfu.itis.adventurerapp.service;

import ru.kpfu.itis.adventurerapp.entity.Note;
import ru.kpfu.itis.adventurerapp.entity.User;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    Note save(Note note);
    List<Note> findAllByUser(User user);
    Optional<Note> findByIdAndUser(Long id, User user);
    void deleteByIdAndUser(Long id, User user);
}

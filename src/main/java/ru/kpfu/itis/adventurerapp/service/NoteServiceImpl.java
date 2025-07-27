package ru.kpfu.itis.adventurerapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.adventurerapp.entity.Note;
import ru.kpfu.itis.adventurerapp.entity.User;
import ru.kpfu.itis.adventurerapp.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public List<Note> findAllByUser(User user) {
        return noteRepository.findAllByUser(user);
    }

    @Override
    public Optional<Note> findByIdAndUser(Long id, User user) {
        return noteRepository.findByIdAndUser(id, user);
    }

    @Override
    public void deleteByIdAndUser(Long id, User user) {
        noteRepository.deleteByIdAndUser(id, user);
    }
}

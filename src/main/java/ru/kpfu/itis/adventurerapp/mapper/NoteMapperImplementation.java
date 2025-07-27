package ru.kpfu.itis.adventurerapp.mapper;

import org.springframework.stereotype.Component;
import ru.kpfu.itis.adventurerapp.dto.NoteCreateRequestDTO;
import ru.kpfu.itis.adventurerapp.dto.NoteResponseDTO;
import ru.kpfu.itis.adventurerapp.entity.Note;

import java.util.ArrayList;
import java.util.List;

@Component
public class NoteMapperImplementation implements NoteMapper {
    @Override
    public Note toEntity(NoteCreateRequestDTO dto) {
        Note res = new Note();
        res.setTitle(dto.getTitle());
        res.setContent(dto.getContent());
        res.setLongitude(dto.getLongitude());
        res.setLatitude(dto.getLatitude());
        res.setImageUrl(dto.getImageUrl());
        return res;
    }

    @Override
    public NoteResponseDTO toDto(Note note) {
        NoteResponseDTO res = new NoteResponseDTO();
        res.setTitle(note.getTitle());
        res.setContent(note.getContent());
        res.setLongitude(note.getLongitude());
        res.setLatitude(note.getLatitude());
        res.setImageUrl(note.getImageUrl());
        res.setId(note.getId());
        res.setCreatedAt(note.getCreatedAt());
        res.setUpdatedAt(note.getUpdatedAt());
        return res;
    }

    @Override
    public List<NoteResponseDTO> toDtoList(List<Note> notes) {
        List<NoteResponseDTO> res = new ArrayList<>();
        for (Note note : notes) {
            res.add(toDto(note));
        }
        return res;
    }

    @Override
    public void updateNoteFromDto(NoteCreateRequestDTO dto, Note note) {

    }
}

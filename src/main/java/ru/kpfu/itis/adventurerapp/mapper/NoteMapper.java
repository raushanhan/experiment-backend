package ru.kpfu.itis.adventurerapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.adventurerapp.dto.NoteCreateRequestDTO;
import ru.kpfu.itis.adventurerapp.dto.NoteResponseDTO;
import ru.kpfu.itis.adventurerapp.entity.Note;

import java.util.List;


public interface NoteMapper {

    Note toEntity(NoteCreateRequestDTO dto);

    NoteResponseDTO toDto(Note note);

    List<NoteResponseDTO> toDtoList(List<Note> notes);

    void updateNoteFromDto(NoteCreateRequestDTO dto, Note note);
}

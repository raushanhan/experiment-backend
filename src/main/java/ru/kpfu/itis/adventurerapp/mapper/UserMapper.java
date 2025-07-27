package ru.kpfu.itis.adventurerapp.mapper;

import org.mapstruct.Mapper;
import ru.kpfu.itis.adventurerapp.dto.UserResponseDTO;
import ru.kpfu.itis.adventurerapp.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toDto(User user);
}
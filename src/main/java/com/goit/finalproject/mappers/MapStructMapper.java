package com.goit.finalproject.mappers;

import com.goit.finalproject.dto.NoteDto;
import com.goit.finalproject.dto.UserDto;
import com.goit.finalproject.entity.Note;
import com.goit.finalproject.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface MapStructMapper {
    List<NoteDto> mapEntityToDto(List<Note> note);
    NoteDto mapEntityToDto(Note note);
    Note mapDtoToEntity(NoteDto noteDto);
    UserDto userToUserDto(User person);
}

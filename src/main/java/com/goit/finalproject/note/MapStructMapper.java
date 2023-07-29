package com.goit.finalproject.note;

import com.goit.finalproject.user.UserDto;
import com.goit.finalproject.user.User;
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

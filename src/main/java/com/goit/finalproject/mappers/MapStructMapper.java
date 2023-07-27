package com.goit.finalproject.mappers;

import com.goit.finalproject.dto.NoteDTO;
import com.goit.finalproject.dto.PersonDTO;
import com.goit.finalproject.entity.Note;
import com.goit.finalproject.entity.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    NoteDTO noteToNoteDto(Note note);
    PersonDTO personToPersonDto(Person person);
}

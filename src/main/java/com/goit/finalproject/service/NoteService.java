package com.goit.finalproject.service;

import com.goit.finalproject.dto.NoteDto;
import com.goit.finalproject.entity.Note;
import com.goit.finalproject.repository.NoteRepository;
import com.goit.finalproject.service.plug.NoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final UserService userService;

    public List<NoteDto> listAllUsersNotes() {
        List<Note> result = noteRepository.findAll();
        return noteMapper.mapEntityToDto(result);
    }

    public List<NoteDto> listAll() {
        List<Note> allNotes = noteRepository.findAll();
        allNotes.removeIf(note -> Objects.equals(note.getUser().getId(), userService.getUserId()));
        return noteMapper.mapEntityToDto(allNotes);
    }

    public void add(NoteDto noteDto, Long userId) { //TODO нам не потрібно вертати NoteDto
        noteDto.setUser_id(userId);
        Note note = noteMapper.mapDtoToEntity(noteDto);
//        note.setUser(noteRepository.findById(userId).orElseThrow().getUser());

        noteRepository.save(note);
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    public void update(Note note) { //TODO зробити перевірку на існування Note і кидати помилку
        noteRepository.save(note);
    }

    public Note findNoteById(Long id) { //TODO треба повертати помилку а не null
        return noteRepository.findById(id).orElseThrow();
    }

    public NoteDto getById(Long id) { //TODO треба повертати помилку
        return noteMapper.mapEntityToDto(noteRepository.findById(id)
                .orElse(new Note()));
    }

}

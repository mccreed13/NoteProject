package com.goit.finalproject.service;

import com.goit.finalproject.dto.NoteDto;
import com.goit.finalproject.entity.Note;
import com.goit.finalproject.mappers.MapStructMapper;
import com.goit.finalproject.repository.NoteRepository;
import com.goit.finalproject.service.plug.NoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public List<NoteDto> listAll() {
        List<Note> result = noteRepository.findAll();
        return noteMapper.mapEntityToDto(result);
    }

    public NoteDto add(NoteDto noteDto) { //TODO нам не потрібно вертати NoteDto
        Note note = noteMapper.mapDtoToEntity(noteDto);
        noteRepository.save(note);
        return noteDto;
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    public void update(Note note) { //TODO зробити перевірку на існування Note і кидати помилку
        noteRepository.save(note);
    }

    public Note findNoteById(Long id) { //TODO треба повертати помилку а не null
        return noteRepository.findById(id).orElse(null);
    }

    public NoteDto getById(Long id) { //TODO треба повертати помилку
        return noteMapper.mapEntityToDto(noteRepository.findById(id)
                .orElse(new Note()));
    }

    public void updateFromDto(NoteDto noteDto) { //TODO треба прибрати у нас є метод add
        Note note = noteMapper.mapDtoToEntity(noteDto);
        noteRepository.save(note);
    }

}

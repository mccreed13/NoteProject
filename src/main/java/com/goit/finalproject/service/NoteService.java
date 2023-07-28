package com.goit.finalproject.service;

import com.goit.finalproject.dto.NoteDto;
import com.goit.finalproject.entity.Note;
import com.goit.finalproject.mappers.MapStructMapper;
import com.goit.finalproject.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final MapStructMapper noteMapper;

    public List<NoteDto> listAll() {
        List<Note> result = noteRepository.findAll();
        return noteMapper.mapEntityToDto(result);
    }

    public NoteDto add(NoteDto noteDto) {
        Note note = noteMapper.mapDtoToEntity(noteDto);
        noteRepository.save(note);
        return noteDto;
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    public void update(Note note) {
        noteRepository.save(note);
    }
    public Note findNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }
    public NoteDto getById(Long id) {
        return noteMapper.mapEntityToDto(noteRepository.findById(id)
                .orElse(new Note()));
    }
    public void updateFromDto(NoteDto noteDto) {
        Note note = noteMapper.mapDtoToEntity(noteDto);
        noteRepository.save(note);
    }

}

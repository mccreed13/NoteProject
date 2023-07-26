package com.goit.finalproject.note;

import com.goit.finalproject.note.plug.Note;
import com.goit.finalproject.note.plug.NoteDto;
import com.goit.finalproject.note.plug.NoteMapper;
import com.goit.finalproject.note.plug.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public NoteService(NoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    public List<com.goit.finalproject.note.plug.NoteDto> listAll() {
        List<Note> result = noteRepository.findAll();
        return noteMapper.mapEntityToDto(result);
    }

    public NoteDto add(Note note) {
        noteRepository.save(note);
        return noteMapper.mapEntityToDto(note);
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    public void update(Note note) {
        noteRepository.save(note);
    }

    public NoteDto getById(Long id) {

        return noteMapper.mapEntityToDto(noteRepository.findById(id)
                .orElse(new Note()));
    }
}

package com.goit.finalproject.note;

import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public NoteService(NoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    public List<NoteDto> listAll() {
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

package com.goit.finalproject.controller;


import com.goit.finalproject.models.NoteDto;
import com.goit.finalproject.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/note")
public class NoteController {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<NoteDto> addNote(@RequestBody NoteDto note) {
        NoteDto savedNote = noteRepository.save(note);
        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
    }

    @GetMapping("/share/{id}")
    public ResponseEntity<NoteDto> shareNote(@PathVariable Long id) {
        Optional<NoteDto> note = noteRepository.findById(id);
        return note.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
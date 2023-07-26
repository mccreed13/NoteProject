package com.goit.finalproject.controller;

import com.goit.finalproject.models.Note;
import com.goit.finalproject.models.NoteDto;
import com.goit.finalproject.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
public class NoteController {
    //  сервіс замість репозиторію?
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/add")
    public ModelAndView addNote(NoteDto noteDto) {
        noteService.save(noteDto);
        return new ModelAndView("redirect:/path_to_note_listing_page");
    }

    @GetMapping("/share/{id}")
    public ModelAndView shareNote(@PathVariable Long id) {
        return new ModelAndView("redirect:/path_to_note_listing_page");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteNote(@PathVariable Long id) {
        try {
            noteService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

        }
        return new ModelAndView("redirect:/path_to_note_listing_page");
    }
}

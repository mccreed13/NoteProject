package com.goit.finalproject.controller;

import com.goit.finalproject.entity.Note;
import com.goit.finalproject.dto.NoteDto;
import com.goit.finalproject.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    public ModelAndView addNote(@ModelAttribute NoteDto noteDto) {
        noteService.add(noteDto);
        return new ModelAndView("redirect:/note/list");
    }
    @GetMapping("/list")
    public ModelAndView listNotes() {
        List<NoteDto> notes = noteService.listAll(); // Використовуйте listAll() замість getAllNotes()
        ModelAndView modelAndView = new ModelAndView("notesList");
        modelAndView.addObject("notes", notes);
        return modelAndView;
    }
    @GetMapping("/edit")
    public ModelAndView editNote(@RequestParam("noteId") Long noteId) {
        Note note = noteService.findNoteById(noteId);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("note", note);
        return modelAndView;
    }
    @PostMapping("/update")
    public ModelAndView updateNote(@ModelAttribute NoteDto noteDto) {
        noteService.updateFromDto(noteDto);
        return new ModelAndView("redirect:/note/list");
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


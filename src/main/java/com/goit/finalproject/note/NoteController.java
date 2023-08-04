package com.goit.finalproject.note;

import com.goit.finalproject.access.Access;
import com.goit.finalproject.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;
    private static final String REDIRECT = "redirect:/note/list";

    @GetMapping(value = "/")
    public String getNotes() {
        return REDIRECT;
    }

    @GetMapping(value = "/list")
    public ModelAndView getListNotes() {
        ModelAndView result = new ModelAndView("note/notesList");
        List<NoteDto> notes = noteService.listAll();
        result.addObject("notes", notes);
        return result;
    }

    @GetMapping(value = "/create")
    public ModelAndView getEditPage() {
        return new ModelAndView("note/noteCreate");
    }

    @PostMapping(value = "/create")
    public String createNewNote(@ModelAttribute NoteDto noteDto) {
        Long userId = userService.getUserId();
        noteService.add(noteDto, userId);
        return REDIRECT;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView getEditPage(@PathVariable Long id) {
        ModelAndView result = new ModelAndView("note/noteEdit");
        NoteDto noteDto = noteService.getById(id);
        result.addObject("note", noteDto);
        result.addObject("noteAccess", noteDto.getAccess().equals(Access.PUBLIC));
        return result;
    }

    @PostMapping(value = "/edit/{id}")
    public String editNote(@PathVariable Long id, @ModelAttribute NoteDto noteDto) {
        noteService.updateNoteById(id, noteDto);
        return REDIRECT;
    }

    @GetMapping(value = "/share/{id}")
    public ModelAndView getSharePage(@PathVariable Long id) {
        try{
            NoteDto noteDto = noteService.getById(id);
            if (noteDto.getAccess() == Access.PUBLIC) {
                ModelAndView result = new ModelAndView("note/publicNote");
                result.addObject("note", noteDto);
                return result;
            } else {
                return new ModelAndView("note/publicErrorNote");
            }
        }catch (Exception e){
            return new ModelAndView("note/publicErrorNote");
        }

    }

    @PostMapping(value = "/delete/{id}")
    public String deleteNoteById(@PathVariable Long id) {
        noteService.deleteById(id);
        return REDIRECT;
    }
}


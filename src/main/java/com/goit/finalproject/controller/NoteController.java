package com.goit.finalproject.controller;

import com.goit.finalproject.entity.Access;
import com.goit.finalproject.entity.Note;
import com.goit.finalproject.dto.NoteDto;
import com.goit.finalproject.service.NoteService;
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

    @GetMapping(value = "/list")
    public ModelAndView getListNotes() {
        ModelAndView result = new ModelAndView("noteslist");
        List<NoteDto> notes = noteService.listAll();
        result.addObject("notes", notes);
        return result;
    }

    @GetMapping(value = "/create")
    public ModelAndView getEditPage(){
        return new ModelAndView("noteCreate");
    }

    @PostMapping(value = "/create")
    public String createNewNote(HttpServletRequest request){
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Access access = Access.valueOf(request.getParameter("access"));
        Long userId = Long.valueOf(request.getParameter("userId"));
//        NoteDto noteDto = new NoteDto(title, content, access, userId);
//        noteService.add(noteDto);
        return "redirect:/note/list";
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView getEditPage(@PathVariable Long id){
        ModelAndView result = new ModelAndView("noteEdit");
        NoteDto noteDto = noteService.getById(id);
        result.addObject("note", noteDto);
        return result;
    }

    @PostMapping(value = "/edit/{id}")
    public String editNote(@PathVariable Long id, HttpServletRequest request){
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Access access = Access.valueOf(request.getParameter("access"));
        noteService.update(Note.builder().id(id).title(title).content(content).access(access).build());
        return "redirect:/note/list";
    }

    @GetMapping(value = "/share/{id}")
    public ModelAndView getSharePage(@PathVariable Long id) {
        NoteDto noteDto = noteService.getById(id);
        if (noteDto.getAccess() == Access.PUBLIC) { //TODO changed on setAccess (not equals?)
            ModelAndView result = new ModelAndView("publicNote");
            result.addObject("note", noteDto);
            return result;
        } else {
            return new ModelAndView("publicErrorNote");
        }
    }
}


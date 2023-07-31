package com.goit.finalproject.controller;

import com.goit.finalproject.entity.Access;
import com.goit.finalproject.entity.Note;
import com.goit.finalproject.dto.NoteDto;
import com.goit.finalproject.entity.User;
import com.goit.finalproject.repository.UserRepository;
import com.goit.finalproject.service.NoteService;
import com.goit.finalproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;

    @GetMapping(value = "/list")
    public ModelAndView getListNotes() {
        ModelAndView result = new ModelAndView("noteslist");
        List<NoteDto> notes = noteService.listAll();
        result.addObject("notes", notes);
        return result;
    }

    @GetMapping(value = "/create")
    //TODO changed ModelAndView on String; added parameter NoteDto; return
    public String getEditPage(){
        return "noteCreate";
    }

    @PostMapping(value = "/create")
    //TODO added Note note, BindingResult result as parameters
    public String createNewNote(NoteDto noteDto, HttpServletRequest request){
        Long user_id = userService.getUserId();
        noteService.add(noteDto, user_id);
        return "redirect:/note/list";
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView getEditPage(@PathVariable Long id){
        ModelAndView result = new ModelAndView("edit"); //TODO change on edit
        NoteDto noteDto = noteService.getById(id);
        result.addObject("note", noteDto);
        return result;
    }

    @PostMapping(value = "/edit/{id}")
    public String editNote(@PathVariable Long id, NoteDto noteDto, HttpServletRequest request){
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Long user_id = Long.parseLong(request.getParameter("user_id"));
        User user = userService.getUserById(user_id);
        Access access = Access.valueOf(request.getParameter("access"));
        noteService.update(Note.builder().id(id).title(title)
                .content(content).access(access).user(user).build());
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
//TODO added delete method
    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable("id") long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }
}


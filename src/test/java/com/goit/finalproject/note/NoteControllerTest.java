package com.goit.finalproject.note;

import com.goit.finalproject.access.Access;
import com.goit.finalproject.user.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(NoteController.class)
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private NoteService noteService;

    @Test
    void getNotes() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/note/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/note/list"));
    }

    @Test
    void getListNotes() throws Exception {
        Mockito.when(noteService.listAll()).thenReturn(createTestNotesList());
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/note/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("note/notesList"))
                .andExpect(model().attribute("notes", Matchers.equalTo(createTestNotesList())));
    }

    @Test
    void createNewNote() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/note/create"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/note/list"));
    }

    @Test
    void getCreatePage() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/note/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("note/noteCreate"));
    }

    @Test
    void getEditPage() throws Exception {
        NoteDto testNoteDto = createTestNotesList().get(6);
        Mockito.when(noteService.getById(5L)).thenReturn(testNoteDto);
        Mockito.when(userService.getUserId()).thenReturn(testNoteDto.getUserId());
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/note/edit/" + 5L))
                .andExpect(status().isOk())
                .andExpect(view().name("note/noteEdit"))
                .andExpect(model().attribute("note", Matchers.equalTo(testNoteDto)))
                .andExpect(model().attribute("noteAccess", Matchers.equalTo(testNoteDto.getAccess().equals(Access.PUBLIC))));
    }
    @Test
    void editNote() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/note/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/note/list"));
    }

    @Test
    void getSharePage() throws Exception {
        NoteDto testNoteDto = createTestNotesList().get(6);
        Mockito.when(noteService.getById(5L)).thenReturn(testNoteDto);
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/note/share/" + 5))
                .andExpect(status().isOk())
                .andExpect(view().name("note/publicNote"))
                .andExpect(model().attribute("note", Matchers.equalTo(testNoteDto)));
    }

    @Test
    void getShareErrorPage() throws Exception {
        NoteDto testNoteDto = createTestNotesList().get(6);
        testNoteDto.setAccess(Access.PRIVATE);
        Mockito.when(noteService.getById(5L)).thenReturn(testNoteDto);
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/note/share/" + 5))
                .andExpect(status().isOk())
                .andExpect(view().name("note/publicErrorNote"));
    }

    @Test
    void deleteNoteById() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/note/delete/" + 5))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/note/list"));
    }

    private List<NoteDto> createTestNotesList(){
        List<NoteDto> noteDtos = new ArrayList<>();
        for (long i=0; i < 10; i++) {
            NoteDto noteDto = new NoteDto(i, "Title " + i, "Content " + i, Access.PUBLIC, 1L);
            noteDtos.add(noteDto);
        }
        return noteDtos;
    }
}
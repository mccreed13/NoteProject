package com.goit.finalproject.note;

import com.goit.finalproject.security.PasswordEncoderProvider;
import com.goit.finalproject.security.SecurityConfiguration;
import com.goit.finalproject.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.security.Principal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {NoteController.class, PasswordEncoderProvider.class})
@Import(SecurityConfiguration.class)
class NoteControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;
    @MockBean
    private NoteService noteService;


    @Test
    void getGetListNotesUnauthorizedShouldReturn401() throws Exception {
        mvc.perform(get("/note/list"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser()
    void getGetListNotesAuthorizedShouldReturnOk() throws Exception {
        mvc.perform(get("/note/list"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetList() {
    }
}
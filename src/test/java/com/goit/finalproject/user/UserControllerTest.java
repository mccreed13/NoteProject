package com.goit.finalproject.user;

import com.goit.finalproject.role.Role;
import com.goit.finalproject.role.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class UserControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }

    @Test
    void testExistingUserLoginSuccessful() throws Exception {
        Role userRole = new Role(1L, "USER", new ArrayList<>());
        roleService.save(userRole);

        User user = new User();
        user.setUsername("user1");
        user.setPassword("password");
        user.setRoles(Set.of(userRole));
        userService.createUser(user);

        mvc.perform(formLogin("/login")
                        .user(user.getUsername())
                        .password("password"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("/note/list"));
                .andExpect(header().string("Location","/note/list"));
    }

    @Test
    void testWrongCredentialLoginFailed() throws Exception {
        Role userRole = new Role(1L, "USER", new ArrayList<>());
        roleService.save(userRole);

        User user = new User();
        user.setUsername("user1");
        user.setPassword("password");
        user.setRoles(Set.of(userRole));
        userService.createUser(user);

        mvc.perform(formLogin("/login")
                        .user(user.getUsername())
                        .password("wrong_password"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/login?error"));
    }

    @Test
    @WithMockUser(value = "USER")
    public void whenAdminAccessAdminSecuredEndpoint_thenIsOk() throws Exception {
        mvc.perform(get("/note/list"))
                .andExpect(status().isOk());
    }

    @Test
    void testNotExistingUserLoginSuccessful() throws Exception {
        User user = new User();
        user.setUsername("user1");
        user.setPassword("password");
        Role userRole = new Role(1L, "USER", new ArrayList<>());
        roleService.save(userRole);

        user.setRoles(new HashSet<Role>(Collections.singletonList(userRole)));
        userService.createUser(user);


        mvc.perform(formLogin("/login")
                        .user(user.getUsername())
                        .password(user.getPassword()))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

}
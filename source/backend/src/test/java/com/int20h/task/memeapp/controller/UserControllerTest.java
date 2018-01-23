package com.int20h.task.memeapp.controller;

import com.int20h.task.memeapp.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by valych on 4/28/17.
 */

public class UserControllerTest extends ControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getLoggedUser() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("admin")))
                .andExpect(jsonPath("$.firstname", is("Name")))
                .andExpect(jsonPath("$.lastname", is("Surname")))
                .andExpect(jsonPath("$.email", is("admin@example.com")));
    }
}

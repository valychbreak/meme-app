package com.int20h.task.memeapp.controller.meme;

import com.int20h.task.memeapp.controller.ControllerTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemeControllerTest extends ControllerTest {
    @Test
    public void getAllMemes() throws Exception {
        mockMvc.perform(get("/api/meme/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.page", is(1)))
                .andExpect(jsonPath("$.totalItems", is(15)))
                .andExpect(jsonPath("$.totalPages", is(3)))
                .andExpect(jsonPath("$.items[0].rating", notNullValue()));
    }

    @Test
    public void getAllMemesPage2() throws Exception {
        mockMvc.perform(get("/api/meme/all").param("p", "2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.page", is(2)))
                .andExpect(jsonPath("$.totalItems", is(15)))
                .andExpect(jsonPath("$.totalPages", is(3)))
                .andExpect(jsonPath("$.items[0].rating", notNullValue()));
    }

}
package com.int20h.task.memeapp.controller.meme;

import com.int20h.task.memeapp.controller.ControllerTest;
import com.int20h.task.memeapp.repository.MemeRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MemeControllerTest extends ControllerTest {

    @Autowired
    private MemeRepository memeRepository;
    private int totalItems;

    @Before
    public void setUp() throws Exception {
        totalItems = new Long(memeRepository.count()).intValue();
    }

    @Test
    public void getAllMemes() throws Exception {
        mockMvc.perform(get("/api/meme/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.page", is(1)))
                .andExpect(jsonPath("$.totalItems", is(totalItems)))
                .andExpect(jsonPath("$.totalPages", is(3)))
                .andExpect(jsonPath("$.items[0].rating", notNullValue()));
    }

    @Test
    public void getAllMemesPage2() throws Exception {
        mockMvc.perform(get("/api/meme/all").param("p", "2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.page", is(2)))
                .andExpect(jsonPath("$.totalItems", is(totalItems)))
                .andExpect(jsonPath("$.totalPages", is(3)))
                .andExpect(jsonPath("$.items[0].rating", notNullValue()));
    }

}
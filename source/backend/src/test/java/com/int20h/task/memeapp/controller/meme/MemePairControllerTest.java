package com.int20h.task.memeapp.controller.meme;

import com.int20h.task.memeapp.controller.ControllerTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MemePairControllerTest extends ControllerTest {
    @Test
    public void getMemePair() throws Exception {
        mockMvc.perform(get("/api/meme/pair"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.memePair[0].id", notNullValue()))
                .andExpect(jsonPath("$.memePair[0].image", notNullValue()))
                .andExpect(jsonPath("$.memePair[1].id", notNullValue()))
                .andExpect(jsonPath("$.memePair[1].image", notNullValue()));
    }

}
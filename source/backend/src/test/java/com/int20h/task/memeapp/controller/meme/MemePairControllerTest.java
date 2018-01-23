package com.int20h.task.memeapp.controller.meme;

import com.int20h.task.memeapp.controller.ControllerTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemePairControllerTest extends ControllerTest {
    @Test
    public void getMemePair() throws Exception {
        mockMvc.perform(get("/api/meme/pair"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.memePair[0].id", is(1)))
                .andExpect(jsonPath("$.memePair[0].image", is("https://img-9gag-fun.9cache.com/photo/aDx30WN_460s.jpg")))
                .andExpect(jsonPath("$.memePair[1].id", is(2)))
                .andExpect(jsonPath("$.memePair[1].image", is("https://img-9gag-fun.9cache.com/photo/a9pM3Aj_460s.jpg")));
    }

}
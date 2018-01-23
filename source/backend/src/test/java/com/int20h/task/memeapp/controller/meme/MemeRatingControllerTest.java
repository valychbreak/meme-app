package com.int20h.task.memeapp.controller.meme;

import com.int20h.task.memeapp.controller.ControllerTest;
import com.int20h.task.memeapp.dto.MemeDTO;
import com.int20h.task.memeapp.dto.MemeRatingPairDTO;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;

import static com.int20h.task.memeapp.dto.MemeDTOBuilder.aMemeDTO;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemeRatingControllerTest extends ControllerTest {
    @Test
    public void ratePair() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        MemeDTO memeOne = aMemeDTO()
                .setId(1L)
                .setImage("https://img-9gag-fun.9cache.com/photo/aDx30WN_460s.jpg")
                .build();

        MemeDTO memeTwo = aMemeDTO()
                .setId(2L)
                .setImage("https://img-9gag-fun.9cache.com/photo/a9pM3Aj_460s.jpg")
                .build();

        MemeRatingPairDTO memeRatingPair = new MemeRatingPairDTO(memeTwo, memeOne);
        mockMvc.perform(post("/api/meme/rate").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(memeRatingPair)))
                .andExpect(status().isOk());
    }

}
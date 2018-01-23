package com.int20h.task.memeapp.controller.meme;

import com.int20h.task.memeapp.controller.ApiController;
import com.int20h.task.memeapp.dto.MemeDTO;
import com.int20h.task.memeapp.dto.MemeDTOBuilder;
import com.int20h.task.memeapp.dto.MemePairDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.int20h.task.memeapp.dto.MemeDTOBuilder.aMemeDTO;

@RestController
public class MemePairController extends ApiController {

    @RequestMapping(value = "/meme/pair")
    public ResponseEntity<MemePairDTO> getMemePair() {
        MemeDTO memeOne = aMemeDTO()
                .setId(1L)
                .setImage("https://img-9gag-fun.9cache.com/photo/aDx30WN_460s.jpg")
                .build();

        MemeDTO memeTwo = aMemeDTO()
                .setId(2L)
                .setImage("https://img-9gag-fun.9cache.com/photo/a9pM3Aj_460s.jpg")
                .build();

        return new ResponseEntity<>(new MemePairDTO(memeOne, memeTwo), HttpStatus.OK);
    }
}

package com.int20h.task.memeapp.controller.meme;

import com.int20h.task.memeapp.controller.ApiController;
import com.int20h.task.memeapp.domain.Meme;
import com.int20h.task.memeapp.dto.MemeDTO;
import com.int20h.task.memeapp.dto.MemePairDTO;
import com.int20h.task.memeapp.provider.MemePairProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.int20h.task.memeapp.dto.MemeDTOBuilder.aMemeDTO;

@RestController
public class MemePairController extends ApiController {

    private MemePairProvider memePairProvider;

    @Autowired
    public MemePairController(MemePairProvider memePairProvider) {
        this.memePairProvider = memePairProvider;
    }

    @RequestMapping(value = "/meme/pair")
    public ResponseEntity<MemePairDTO> getMemePair() {
        Pair<Meme, Meme> memePair = memePairProvider.provide();
        MemeDTO memeOne = aMemeDTO().setMeme(memePair.getFirst()).build();
        MemeDTO memeTwo = aMemeDTO().setMeme(memePair.getSecond()).build();
        return new ResponseEntity<>(new MemePairDTO(memeOne, memeTwo), HttpStatus.OK);
    }
}

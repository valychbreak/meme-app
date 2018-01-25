package com.int20h.task.memeapp.controller.meme;

import com.int20h.task.memeapp.controller.ApiController;
import com.int20h.task.memeapp.domain.Meme;
import com.int20h.task.memeapp.dto.MemeDTO;
import com.int20h.task.memeapp.dto.MemeRatingPairDTO;
import com.int20h.task.memeapp.repository.MemeRepository;
import com.int20h.task.memeapp.service.MemeRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemeRatingController extends ApiController {

    private MemeRepository memeRepository;
    private MemeRatingService memeRatingService;

    @Autowired
    public MemeRatingController(MemeRepository memeRepository, MemeRatingService memeRatingService) {
        this.memeRepository = memeRepository;
        this.memeRatingService = memeRatingService;
    }

    @RequestMapping(value = "/meme/rate", method = RequestMethod.POST)
    public ResponseEntity ratePair(@RequestBody MemeRatingPairDTO memeRatingPair) {
        MemeDTO selectedMemeDTO = memeRatingPair.getSelectedMeme();
        MemeDTO ignoredMemeDTO = memeRatingPair.getIgnoredMeme();

        Meme selectedMeme = memeRepository.findOne(selectedMemeDTO.getId());
        Meme discardedMeme = memeRepository.findOne(ignoredMemeDTO.getId());

        Assert.notNull(selectedMeme);
        Assert.notNull(discardedMeme);

        memeRatingService.rateMemes(selectedMeme, discardedMeme);
        return new ResponseEntity(HttpStatus.OK);
    }
}

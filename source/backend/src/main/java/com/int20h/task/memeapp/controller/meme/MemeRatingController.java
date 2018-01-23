package com.int20h.task.memeapp.controller.meme;

import com.int20h.task.memeapp.controller.ApiController;
import com.int20h.task.memeapp.dto.MemeRatingPairDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemeRatingController extends ApiController {

    @RequestMapping(value = "/meme/rate", method = RequestMethod.POST)
    public ResponseEntity ratePair(@RequestBody MemeRatingPairDTO memeRatingPair) {
        // TODO: implement rating algorithm and work with real objects
        Assert.notNull(memeRatingPair.getSelectedMeme());
        Assert.notNull(memeRatingPair.getIgnoredMeme());
        return new ResponseEntity(HttpStatus.OK);
    }
}

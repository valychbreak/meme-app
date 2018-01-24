package com.int20h.task.memeapp.service.imgflip;

import com.int20h.task.memeapp.service.imgflip.exception.ImgFlipExchangeException;
import com.int20h.task.memeapp.service.imgflip.model.ImgFlipMem;
import com.int20h.task.memeapp.service.imgflip.model.ImgFlipResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Component
public class ImgFlipClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //TODO move this variable to properties
    private final String imgFlipGetImagesUrl = "https://api.imgflip.com/get_memes";

    @Autowired
    private RestTemplate restTemplate;

    public List<ImgFlipMem> loadMemes() {
        logger.debug("Send request to imgflip service '{}'", imgFlipGetImagesUrl);
        try {
            ResponseEntity<ImgFlipResponse> response = this.restTemplate.getForEntity(imgFlipGetImagesUrl,
                    ImgFlipResponse.class);
            return response.getBody().getData().getMemes();
        } catch (HttpClientErrorException ex) {
            logger.warn("ImgFlip service returned error: [{}]", ex.getMessage());
            throw new ImgFlipExchangeException("ImgFlip service returns error");
        }
    }

}

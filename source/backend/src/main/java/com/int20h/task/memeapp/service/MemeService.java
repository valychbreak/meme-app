package com.int20h.task.memeapp.service;

import com.int20h.task.memeapp.domain.Meme;
import com.int20h.task.memeapp.repository.MemeRepository;
import com.int20h.task.memeapp.service.imgflip.ImgFlipClient;
import com.int20h.task.memeapp.service.imgflip.model.ImgFlipMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Service
public class MemeService {
    private final MemeRepository memeRepository;
    private final ImgFlipClient imgFlipClient;

    @Autowired
    public MemeService(MemeRepository memeRepository, ImgFlipClient imgFlipClient) {

        this.memeRepository = memeRepository;
        this.imgFlipClient = imgFlipClient;
    }

    public void loadMemesFromImgFlip() {
        List<ImgFlipMem> memes = imgFlipClient.loadMemes();
        memes.stream()
                .map(el -> new Meme(el.getName(), el.getUrl()))
                .forEach(memeRepository::save);
    }
}

package com.int20h.task.memeapp.service;

import com.int20h.task.memeapp.domain.Meme;
import com.int20h.task.memeapp.repository.MemeRepository;
import com.int20h.task.memeapp.service.imgflip.ImgFlipClient;
import com.int20h.task.memeapp.service.imgflip.model.ImgFlipMem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public class MemeServiceTest {

    @Mock
    ImgFlipClient imgFlipClient;

    @Mock
    MemeRepository memeRepository;

    MemeService memeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        memeService = new MemeService(memeRepository, imgFlipClient);
    }

    @Test
    public void getMemesFromImgFlip() {

        ImgFlipMem imgFlipMem1 = new ImgFlipMem(
                "One Does Not Simply",
                "http://i.imgflip.com/1bij.jpg"
        );
        ImgFlipMem imgFlipMem2 = new ImgFlipMem(
                "Ancient Aliens",
                "http://i.imgflip.com/26am.jpg"
        );
        List<ImgFlipMem> memes = Arrays.asList(imgFlipMem1, imgFlipMem2);

        Meme expectedMeme1 = new Meme(imgFlipMem1.getName(), imgFlipMem1.getUrl());
        Meme expectedMeme2 = new Meme(imgFlipMem2.getName(), imgFlipMem2.getUrl());

        when(imgFlipClient.loadMemes()).thenReturn(memes);
        ArgumentCaptor<Meme> argumentCaptor = ArgumentCaptor.forClass(Meme.class);

        memeService.loadMemesFromImgFlip();

        verify(imgFlipClient).loadMemes();
        verify(memeRepository, times(2)).save(argumentCaptor.capture());

        List<Meme> savedMemes = argumentCaptor.getAllValues();
        assertThat(savedMemes).hasSize(2);
        assertThat(savedMemes).  containsExactlyInAnyOrder(expectedMeme1, expectedMeme2);
    }
}

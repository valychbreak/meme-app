package com.int20h.task.memeapp.service;

import com.int20h.task.memeapp.domain.Meme;
import com.int20h.task.memeapp.repository.MemeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MemeRatingServiceTest {

    @Mock
    private MemeRepository memeRepository;

    private MemeRatingService service;

    private Meme selectedMeme;
    private Meme discardedMeme;

    @Before
    public void setUp() throws Exception {
        selectedMeme = new Meme("http://image-link.com/123.jpg");
        discardedMeme = new Meme("http://image-link.com/123.jpg");

        service = new MemeRatingService(memeRepository);
    }

    @Test
    public void ratesOnlySelectedMeme() throws Exception {
        selectedMeme.setRating(100D);
        discardedMeme.setRating(200D);

        service.rateMemes(selectedMeme, discardedMeme);

        assertThat(selectedMeme.getRating()).isEqualTo(120D);
        assertThat(discardedMeme.getRating()).isEqualTo(200D);

        verify(memeRepository, times(1)).save(eq(selectedMeme));
        verifyNoMoreInteractions(memeRepository);
    }

    @Test
    public void ratesMemeWithGreaterRating() throws Exception {
        selectedMeme.setRating(200D);
        discardedMeme.setRating(100D);

        service.rateMemes(selectedMeme, discardedMeme);

        assertThat(selectedMeme.getRating()).isEqualTo(212D);
        assertThat(discardedMeme.getRating()).isEqualTo(100D);

        verify(memeRepository, times(1)).save(eq(selectedMeme));
        verifyNoMoreInteractions(memeRepository);
    }

}
package com.int20h.task.memeapp.provider;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.int20h.task.memeapp.domain.Meme;
import com.int20h.task.memeapp.repository.MemeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.util.Pair;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MemePairProviderTest {

    @Mock
    private MemeRepository memeRepository;

    private MemePairProvider memePairProvider;

    @Before
    public void setUp() throws Exception {
        memePairProvider = new MemePairProvider(memeRepository);
    }

    @Test
    public void provides2NotEqualsImages() throws Exception {
        Meme memeOne = createMeme("http://image-link.com/123.jpg", 35L, 20.4);
        Meme memeTwo = createMeme("http://image-link.com/1244_aa_g.jpg", 103L, 4.4);

        when(memeRepository.findAll()).thenReturn(
                Sets.newHashSet(memeOne, memeTwo)
        );

        when(memeRepository.count()).thenReturn(2L);

        Pair<Meme, Meme> memePair = memePairProvider.provide();
        assertThat(ImmutableSet.of(memePair.getFirst(), memePair.getSecond())).containsOnly(memeOne, memeTwo);
    }

    @Test
    public void providesMemePairFromFiveMemes() throws Exception {
        when(memeRepository.findAll()).thenReturn(
                Sets.newHashSet(
                        createMeme("http://image-link.com/123.jpg", 35L, 20.4),
                        createMeme("http://image-link.com/1244_aa_g.jpg", 103L, 4.4),
                        createMeme("http://image-link.com/1334_aa_g.jpg", 12L, 43.4),
                        createMeme("http://image-link.com/1114_aa_g.jpg", 66L, 22.4),
                        createMeme("http://image-link.com/2224_aa_g.jpg", 1L, 80.4)
                )
        );

        when(memeRepository.count()).thenReturn(2L);

        Pair<Meme, Meme> memePair = memePairProvider.provide();
        assertThat(memePair.getFirst()).isNotNull();
        assertThat(memePair.getSecond()).isNotNull();
        assertThat(memePair.getFirst()).isNotEqualTo(memePair.getSecond());
    }

    private Meme createMeme(String imageLink, long id, double rating) {
        Meme meme = new Meme(imageLink);
        meme.setId(id);
        meme.setRating(rating);
        return meme;
    }

}
package com.int20h.task.memeapp.controller.meme;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.int20h.task.memeapp.controller.ControllerTest;
import com.int20h.task.memeapp.domain.Meme;
import com.int20h.task.memeapp.dto.MemeDTO;
import com.int20h.task.memeapp.dto.MemeRatingPairDTO;
import com.int20h.task.memeapp.repository.MemeRepository;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static com.int20h.task.memeapp.dto.MemeDTOBuilder.aMemeDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class MemeRatingControllerTest extends ControllerTest {

    @Autowired
    private MemeRepository memeRepository;
    private Meme meme;
    private Meme meme2;

    @Before
    public void setUp() throws Exception {
        // TODO: remove this code and use dbunit (for now, db unit xml file doesn't work for some reason)
        meme = new Meme("http://test.com/jss.jpg");
        meme.setRating(100D);
        memeRepository.save(meme);

        meme2 = new Meme("http://test.com/jss.jpg");
        meme2.setRating(200D);
        memeRepository.save(meme2);
    }

    @Test
    @DatabaseSetup(value = "/data/controller/MemeRatingControllerTest.xml")
    public void ratePair() throws Exception {

        Meme selectedMeme = memeRepository.findOne(meme.getId());
        Meme discardedMeme = memeRepository.findOne(meme2.getId());

        ObjectMapper objectMapper = new ObjectMapper();

        MemeDTO memeOne = aMemeDTO().setMeme(discardedMeme).build();
        MemeDTO memeTwo = aMemeDTO().setMeme(selectedMeme).build();

        MemeRatingPairDTO memeRatingPair = new MemeRatingPairDTO(memeTwo, memeOne);
        mockMvc.perform(post("/api/meme/rate").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(memeRatingPair)))
                .andExpect(status().isOk());

        Meme reloadedSelectedMeme = memeRepository.findOne(meme.getId());
        Meme reloadedDiscardedMeme = memeRepository.findOne(meme2.getId());

        assertThat(reloadedDiscardedMeme.getRating()).isEqualTo(discardedMeme.getRating());
        assertThat(reloadedSelectedMeme.getRating()).isGreaterThan(selectedMeme.getRating());
    }

}
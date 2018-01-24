package com.int20h.task.memeapp.service.imgflip;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.int20h.task.memeapp.service.imgflip.exception.ImgFlipExchangeException;
import com.int20h.task.memeapp.service.imgflip.model.ImgFlipData;
import com.int20h.task.memeapp.service.imgflip.model.ImgFlipMem;
import com.int20h.task.memeapp.service.imgflip.model.ImgFlipResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImgFlipClientTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ImgFlipClient imgFlipClient;

    private MockRestServiceServer mockServer;

    // TODO move this variable and variable from implementation to properties
    private final String imgFlipGetImagesUrl = "https://api.imgflip.com/get_memes";

    @Before
    public void setup() {
        mockServer = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    public void getMemes() throws IOException {

        ImgFlipMem expectedMem1 = new ImgFlipMem(
                "One Does Not Simply",
                "http://i.imgflip.com/1bij.jpg"
        );
        ImgFlipMem expectedMem2 = new ImgFlipMem(
                "Ancient Aliens",
                "http://i.imgflip.com/26am.jpg"
        );
        ImgFlipData data = new ImgFlipData(Arrays.asList(expectedMem1, expectedMem2));

        ImgFlipResponse imgFlipResponse = new ImgFlipResponse(true, data);


        mockServer.expect(requestTo(imgFlipGetImagesUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(
                        convertObjectToJsonBytes(imgFlipResponse),
                        MediaType.APPLICATION_JSON)
                );

        List<ImgFlipMem> memes = imgFlipClient.loadMemes();

        assertThat(memes).isNotNull();
        assertThat(memes).containsExactlyInAnyOrder(expectedMem1,expectedMem2);
    }

    @Test
    public void getDirectionsKeepersServiceReturnErrorThrowsException() {
        mockServer.expect(requestTo(imgFlipGetImagesUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withBadRequest().body("bad request"));

        expectedException.expect(ImgFlipExchangeException.class);
        expectedException.expectMessage(containsString("ImgFlip service returns error"));

        imgFlipClient.loadMemes();
    }

    public byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}

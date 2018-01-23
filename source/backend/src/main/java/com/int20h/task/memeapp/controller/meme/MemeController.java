package com.int20h.task.memeapp.controller.meme;

import com.int20h.task.memeapp.controller.ApiController;
import com.int20h.task.memeapp.dto.MemeDTO;
import com.int20h.task.memeapp.dto.PageOrientedData;
import com.int20h.task.memeapp.dto.PageOrientedDataBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static com.int20h.task.memeapp.dto.MemeDTOBuilder.aMemeDTO;

@RestController
public class MemeController extends ApiController {

    @RequestMapping(value = "/meme/all")
    public ResponseEntity<PageOrientedData<MemeDTO>> getAllMemes(
            @RequestParam(value = "p", required = false) Integer page,
            @RequestParam(value = "items", required = false) Integer itemsPerPage
    ) {

        if (page == null) {
            page = 1;
        }

        if (itemsPerPage == null) {
            itemsPerPage = 5;
        }

        MemeDTO memeOne = aMemeDTO()
                .setId(1L)
                .setImage("https://img-9gag-fun.9cache.com/photo/aDx30WN_460s.jpg")
                .setRating(9.6)
                .build();

        MemeDTO memeTwo = aMemeDTO()
                .setId(2L)
                .setImage("https://img-9gag-fun.9cache.com/photo/a9pM3Aj_460s.jpg")
                .setRating(9.1)
                .build();

        MemeDTO memeThree = aMemeDTO()
                .setId(3L)
                .setImage("https://img-9gag-fun.9cache.com/photo/a9pM3Aj_460s.jpg")
                .setRating(8.9)
                .build();

        MemeDTO memeFour = aMemeDTO()
                .setId(4L)
                .setImage("https://img-9gag-fun.9cache.com/photo/a9pM3Aj_460s.jpg")
                .setRating(8.8)
                .build();

        MemeDTO memeFive = aMemeDTO()
                .setId(5L)
                .setImage("https://img-9gag-fun.9cache.com/photo/a9pM3Aj_460s.jpg")
                .setRating(3D)
                .build();

        PageOrientedData<MemeDTO> memesResult = new PageOrientedDataBuilder<>()
                .setPage(page)
                .setTotalItems(15)
                .setTotalPages(3)
                .setItems(Arrays.asList(memeOne, memeTwo, memeThree, memeFour, memeFive))
                .build();
        return new ResponseEntity<>(memesResult, HttpStatus.OK);
    }
}

package com.int20h.task.memeapp.controller.meme;

import com.int20h.task.memeapp.controller.ApiController;
import com.int20h.task.memeapp.dto.MemeDTO;
import com.int20h.task.memeapp.dto.PageOrientedData;
import com.int20h.task.memeapp.dto.PageOrientedDataBuilder;
import com.int20h.task.memeapp.repository.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.int20h.task.memeapp.dto.MemeDTOBuilder.aMemeDTO;

@RestController
public class MemeController extends ApiController {

    private MemeRepository memeRepository;

    @Autowired
    public MemeController(MemeRepository memeRepository) {
        this.memeRepository = memeRepository;
    }

    @RequestMapping(value = "/meme/all")
    public ResponseEntity<PageOrientedData<MemeDTO>> getAllMemes(
            @RequestParam(value = "p", required = false) Integer page,
            @RequestParam(value = "items", required = false) Integer itemsPerPage
    ) {
        // TODO: return limited amount of items (depending on itemsPerPage)
        if (page == null) {
            page = 1;
        }

        if (itemsPerPage == null) {
            itemsPerPage = 5;
        }

        List<MemeDTO> memeDTOList = new ArrayList<>();

        memeRepository.findAllByOrderByRatingDesc().forEach(meme -> {
            memeDTOList.add(aMemeDTO().setMeme(meme).build());
        });

        long totalItems = memeRepository.count();
        PageOrientedData<MemeDTO> memesResult = new PageOrientedDataBuilder<>()
                .setPage(page)
                .setTotalItems(new Long(totalItems).intValue())
                .setTotalPages(3)
                .setItems(memeDTOList)
                .build();
        return new ResponseEntity<>(memesResult, HttpStatus.OK);
    }
}

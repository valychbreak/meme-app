package com.int20h.task.memeapp.repository;

import com.int20h.task.memeapp.domain.Meme;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemeRepository extends CrudRepository<Meme, Long> {
    List<Meme> findAllByOrderByRatingDesc();
}

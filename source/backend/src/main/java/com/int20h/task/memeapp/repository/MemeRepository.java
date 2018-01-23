package com.int20h.task.memeapp.repository;

import com.int20h.task.memeapp.domain.Meme;
import org.springframework.data.repository.CrudRepository;

public interface MemeRepository extends CrudRepository<Meme, Long> {

}

package com.int20h.task.memeapp.service;

import com.int20h.task.memeapp.domain.Meme;
import com.int20h.task.memeapp.repository.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemeRatingService {

    private MemeRepository memeRepository;

    @Autowired
    public MemeRatingService(MemeRepository memeRepository) {
        this.memeRepository = memeRepository;
    }

    public void rateMemes(Meme selectedMeme, Meme discardedMeme) {
        double rating = calculateNewRating(selectedMeme.getRating(), discardedMeme.getRating());
        selectedMeme.setRating(rating);

        memeRepository.save(selectedMeme);
    }

    private double calculateNewRating(double winnerRating, double looserRating) {

        double actualScore = 1.0;

        // calculate expected outcome
        double exponent = (looserRating - winnerRating) / 400;
        double expectedOutcome = (1 / (1 + (Math.pow(10, exponent))));

        // K-factor
        int K = 32;

        // calculate new rating
        return Math.round(winnerRating + K * (actualScore - expectedOutcome));
    }
}

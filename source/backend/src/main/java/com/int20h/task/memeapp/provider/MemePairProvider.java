package com.int20h.task.memeapp.provider;

import com.int20h.task.memeapp.domain.Meme;
import com.int20h.task.memeapp.repository.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MemePairProvider {

    private MemeRepository memeRepository;

    @Autowired
    public MemePairProvider(MemeRepository memeRepository) {
        this.memeRepository = memeRepository;
    }

    public Pair<Meme, Meme> provide() {
        Random random = new Random();
        int memeLastIndex = new Long(memeRepository.count()).intValue() - 1;

        int indexOne = random.nextInt(memeLastIndex);
        int indexTwo = generateSecondIndex(random, memeLastIndex, indexOne);
        return getMemePairBy(indexOne, indexTwo);
    }

    private int generateSecondIndex(Random random, int memeLastIndex, int indexOne) {
        // default index in case number of tries won't provide any result
        int defaultSecondIndex = indexOne == 0 ? memeLastIndex : 0;
        Integer result = null;

        int tries = 10;

        while (tries -- > 0 && result == null) {
            int tempIndex = random.nextInt(memeLastIndex);

            if (tempIndex != indexOne) {
                result = tempIndex;
            }
        }

        if (result == null) {
            result = defaultSecondIndex;
        }
        return result;
    }

    private Pair<Meme, Meme> getMemePairBy(int indexOne, int indexTwo) {
        Iterable<Meme> allMemes = memeRepository.findAll();

        Meme memeOne = null;
        Meme memeTwo = null;
        int currentIndex = 0;
        for (Meme meme : allMemes) {
            if (memeOne == null || memeTwo == null) {
                if (currentIndex == indexOne) {
                    memeOne = meme;
                } else if (currentIndex == indexTwo) {
                    memeTwo = meme;
                }
            }
            currentIndex++;
        }
        return Pair.of(memeOne, memeTwo);
    }
}

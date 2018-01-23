package com.int20h.task.memeapp.dto;

import java.util.ArrayList;
import java.util.List;

public class MemePairDTO {
    private List<MemeDTO> memePair = new ArrayList<>(2);

    public MemePairDTO(MemeDTO memeOne, MemeDTO memeTwo) {
        memePair.add(memeOne);
        memePair.add(memeTwo);
    }

    public List<MemeDTO> getMemePair() {
        return memePair;
    }

    public void setMemePair(List<MemeDTO> memePair) {
        this.memePair = memePair;
    }
}

package com.int20h.task.memeapp.dto;

public class MemeRatingPairDTO {
    private MemeDTO selectedMeme;
    private MemeDTO ignoredMeme;

    protected MemeRatingPairDTO() {
    }

    public MemeRatingPairDTO(MemeDTO selectedMeme, MemeDTO ignoredMeme) {
        this.selectedMeme = selectedMeme;
        this.ignoredMeme = ignoredMeme;
    }

    public MemeDTO getSelectedMeme() {
        return selectedMeme;
    }

    public void setSelectedMeme(MemeDTO selectedMeme) {
        this.selectedMeme = selectedMeme;
    }

    public MemeDTO getIgnoredMeme() {
        return ignoredMeme;
    }

    public void setIgnoredMeme(MemeDTO ignoredMeme) {
        this.ignoredMeme = ignoredMeme;
    }
}

package com.int20h.task.memeapp.dto;

import com.int20h.task.memeapp.domain.Meme;

public class MemeDTOBuilder {
    private Long id;
    private String image;
    private Double rating;

    public static MemeDTOBuilder aMemeDTO() {
        return new MemeDTOBuilder();
    }

    public MemeDTOBuilder setMeme(Meme meme) {
        setId(meme.getId());
        setImage(meme.getImageLink());
        setRating(meme.getRating());
        return this;
    }

    public MemeDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public MemeDTOBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public MemeDTOBuilder setRating(Double rating) {
        this.rating = rating;
        return this;
    }

    public MemeDTO build() {
        return new MemeDTO(id, image, rating);
    }
}
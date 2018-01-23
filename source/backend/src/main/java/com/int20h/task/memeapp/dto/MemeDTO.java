package com.int20h.task.memeapp.dto;

public class MemeDTO {
    private Long id;
    private String image;
    private Double rating;

    public MemeDTO(Long id, String image, Double rating) {
        this.id = id;
        this.image = image;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}

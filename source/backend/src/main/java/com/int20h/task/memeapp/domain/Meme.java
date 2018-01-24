package com.int20h.task.memeapp.domain;

import javax.persistence.*;

@Entity
@Table(name = "memes")
public class Meme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "image_link", nullable = false)
    private String imageLink;

    @Column(name = "rating", nullable = false)
    private double rating;

    protected Meme() {
    }

    public Meme(String imageLink) {
        this.imageLink = imageLink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

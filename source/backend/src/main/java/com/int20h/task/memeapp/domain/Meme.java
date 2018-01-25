package com.int20h.task.memeapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "memes")
public class Meme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image_link", nullable = false)
    private String imageLink;

    @Column(name = "rating", nullable = false)
    private double rating;

    protected Meme() {
    }

    public Meme(String imageLink) {
        this.imageLink = imageLink;
    }

    public Meme(String name, String imageLink) {
        this.name = name;
        this.imageLink = imageLink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meme meme = (Meme) o;

        if (!name.equals(meme.name)) return false;
        return imageLink.equals(meme.imageLink);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + imageLink.hashCode();
        return result;
    }
}

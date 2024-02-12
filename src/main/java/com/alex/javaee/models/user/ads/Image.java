package com.alex.javaee.models.user.ads;

import jakarta.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageName;


    // Konstruktorer, getters och setters

    public Image() {
        // Tom konstruktor för JPA
    }

    public Image(String imageName, byte[] imageData) {
        this.imageName = imageName;
    }

    // getters och setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

}

package com.alex.javaee.models.user.ads;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ads")
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min = 1, max = 20, message = "Title cannot be empty or more than 20 characters!")
    private String Title;
    @Size(min = 1, max = 250, message = "Description cannot be empty or more than 250 characters!")
    private String Description;
    @Enumerated(EnumType.STRING)
    private Categories categories;

    public AdEntity(long id, String title, String description, Categories categories) {
        this.id = id;
        Title = title;
        Description = description;
        this.categories = categories;
    }

    public AdEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }
}
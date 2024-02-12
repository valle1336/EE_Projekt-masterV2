package com.alex.javaee.models.user.ads;

import com.alex.javaee.models.user.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ads")
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min = 1, max = 20, message = "Title cannot be empty or more than 20 characters!")
    private String title;
    @Size(min = 1, max = 250, message = "Description cannot be empty or more than 250 characters!")
    private String description;
    @Enumerated(EnumType.STRING)
    private Categories categories;

    private String email;

    private int price;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;


    public AdEntity(long id, String title, String description, Categories categories, UserEntity user, String email, int price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.categories = categories;
        this.user = user;
        this.email = email;
        this.price = price;
    }

    public AdEntity() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }
}

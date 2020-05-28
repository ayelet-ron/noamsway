package com.example.noamsway.model;

import androidx.room.Entity;

@Entity
public class Post {
    public String userId;
    public String postId;
    public int image;
    public String title;
    public String description;
    public String authorName;
    public Category category;

    public Post(int image, String title, String description, String authorName,Category category) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.authorName = authorName;
        this.category=category;
    }
}

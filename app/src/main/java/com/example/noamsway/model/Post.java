package com.example.noamsway.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Post implements Serializable {
    @PrimaryKey
    public String postId;
    public String userId;
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
    public Post() {
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostId() {
        return postId;
    }

    public String getUserId() {
        return userId;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Category getCategory() {
        return category;
    }
}

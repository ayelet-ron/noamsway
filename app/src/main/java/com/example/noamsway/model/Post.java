package com.example.noamsway.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.firestore.FieldValue;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Post implements Serializable {
    @PrimaryKey
    public String postId;
    public int image;
    public String title;
    public String description;
    public User user;
    public Category category;
//    public double lastUpdate;
//    public boolean isDeleted;

    public Post(int image, String title, String description, User user,Category category) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.user = user;
        this.category=category;
    }
    public Post(int image, String title, String description, User user,Category category,String postId) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.user = user;
        this.category=category;
        this.postId = postId;
    }
    public Post(int image, String title, String description, User user) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.user = user;
    }
    public Post() {
    }
    public Map<String,Object> toMap(){
        HashMap<String,Object> result = new HashMap<>();
        result.put("postId",postId);
        result.put("title",title);
        result.put("image",image);
        result.put("description",description);
        result.put("user",user);
        result.put("category",category);
       // result.put("lastUpdate", FieldValue.serverTimestamp());
        //result.put("isDeleted",isDeleted);
        return result;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
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


    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }
}

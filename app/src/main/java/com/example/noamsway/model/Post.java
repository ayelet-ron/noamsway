package com.example.noamsway.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Post implements Serializable {
    @PrimaryKey
    public String postId;
    public String image;
    public String title;
    public String description;
    public User user;
    public Category category;
    public long lastUpdate;
    public boolean isDeleted;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Post(String image, String title, String description, User user, Category category) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.user = user;
        this.category = category;
        this.isDeleted = false;
    }
    public Post(String title, String description, User user, Category category) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.category = category;
        this.isDeleted = false;
    }

    public Post(String image, String title, String description, User user, Category category, String postId) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.user = user;
        this.category = category;
        this.postId = postId;
        this.isDeleted = false;
    }

    public Post(String image, String title, String description, User user) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.user = user;
        this.isDeleted = false;
    }

    public Post() {
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("postId", postId);
        result.put("title", title);
        result.put("image", image);
        result.put("description", description);
        result.put("user", user);
        result.put("category", category);
        result.put("lastUpdate", FieldValue.serverTimestamp());
        result.put("isDeleted", isDeleted);
        return result;
    }
    public static Post factory(Map<String, Object> json){
        Post post = new Post();
        post.setTitle((String)json.get("title"));
        post.setPostId((String)json.get("postId"));
        post.setImage((String)json.get("image"));
        post.setDescription((String)json.get("description"));
        post.setUser(User.factory((Map<String, Object>) json.get("user")));
        post.setCategory(Category.factory((Map<String, Object>) json.get("category")));
        Timestamp ts = (Timestamp)json.get("lastUpdated");
        if (ts != null) post.lastUpdate = ts.getSeconds();
        post.setDeleted((Boolean)json.get("isDeleted"));
        return post;
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

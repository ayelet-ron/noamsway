package com.example.noamsway.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.noamsway.utils.Listener;

import java.util.ArrayList;

public class PostModel {
    public static final PostModel instance = new PostModel();
    public void addPost(Post post, Listener<Boolean> listener){
        PostFirebase.addPost(post,listener);
    }
    public void updatePost(Post post, Listener<Boolean> listener){
        PostFirebase.updatePost(post,listener);
    }
    public void deletePost(String postId, Listener<Boolean> listener){
        PostFirebase.deletePost(postId,listener);
    }
    public LiveData<ArrayList<Post>> getAllPosts(){
        MutableLiveData<ArrayList<Post>> liveData = new MutableLiveData<>();
        liveData.setValue(new ArrayList<>());
        PostFirebase.getAllPosts(new Listener<ArrayList<Post>>() {
            @Override
            public void onComplete(ArrayList<Post> data) {
                liveData.setValue(data);
            }
        });
        return liveData;
    }
    public LiveData<ArrayList<Post>> getAllPostsOfSpecificCategory(String categoryName){
        MutableLiveData<ArrayList<Post>> liveData = new MutableLiveData<>();
        liveData.setValue(new ArrayList<>());
        PostFirebase.getAllPostsOfSpecificCategory(categoryName,new Listener<ArrayList<Post>>() {
            @Override
            public void onComplete(ArrayList<Post> data) {
                liveData.setValue(data);
            }
        });
        return liveData;
    }
    public LiveData<ArrayList<Post>> getAllPostsOfSpecificUser(String userEmail){
        MutableLiveData<ArrayList<Post>> liveData = new MutableLiveData<>();
        liveData.setValue(new ArrayList<>());
        PostFirebase.getAllPostsOfSpecificUser(userEmail,new Listener<ArrayList<Post>>() {
            @Override
            public void onComplete(ArrayList<Post> data) {
                liveData.setValue(data);
            }
        });
        return liveData;
    }
}

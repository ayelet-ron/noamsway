package com.example.noamsway.model;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.noamsway.MyApplication;
import com.example.noamsway.utils.Listener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class PostModel {
    public static final PostModel instance = new PostModel();
    public void addPost(Post post, Listener<Boolean> listener){
        PostFirebase.addPost(post,listener);
    }
    public void updatePost(Post post, Listener<Boolean> listener){
        PostFirebase.updatePost(post,listener);
    }
    public void deletePost(String postId, Listener<Boolean> listener){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                AppLocalDb.db.postDao().delete(postId);
            }
        });
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
    public LiveData<ArrayList<Post>> getAllPostsOfSpecificCategoryOld(String categoryName){
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
    public LiveData<List<Post>> getAllPostsOfSpecificCategory(String categoryName){
        LiveData<List<Post>> liveData = AppLocalDb.db.postDao().getAllPostsFromCategory(categoryName);
        refreshPostsCategoryList(categoryName,null);
        return liveData;
    }

    public LiveData<List<Post>> getAllPostsOfSpecificUser(String userEmail){
        LiveData<List<Post>> liveData = AppLocalDb.db.postDao().getAllPostsOfSpecificUser(userEmail);
        refreshMyPostsList(userEmail,null);
        return liveData;
    }

    public void refreshPostsCategoryList(String category,final Listener<Boolean> listener){
        long lastUpdated = MyApplication.context.getSharedPreferences("TAG",MODE_PRIVATE).getLong("PostsLastUpdateDate",0);
        PostFirebase.getAllPostsSince(lastUpdated,new Listener<List<Post>>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onComplete(final List<Post> data) {
                new AsyncTask<String,String,String>(){
                    @Override
                    protected String doInBackground(String... strings) {
                        long lastUpdated = 0;
                        for(Post p : data){
                            AppLocalDb.db.postDao().insertAll(p);
                            if (p.lastUpdate > lastUpdated) lastUpdated = p.lastUpdate;
                        }
                        SharedPreferences.Editor edit = MyApplication.context.getSharedPreferences("TAG", MODE_PRIVATE).edit();
                        edit.putLong("PostsLastUpdateDate",lastUpdated);
                        edit.commit();
                        return null;
                    }
                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        if (listener!=null)  listener.onComplete(true);
                    }
                }.execute("");
            }
        });
    }
    public void refreshMyPostsList(String userEmail,final Listener<Boolean> listener){
        long lastUpdated = MyApplication.context.getSharedPreferences("TAG",MODE_PRIVATE).getLong("PostsLastUpdateDate",0);
        PostFirebase.getAllPostsOfSpecificUserSince(lastUpdated,userEmail,new Listener<List<Post>>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onComplete(final List<Post> data) {
                new AsyncTask<String,String,String>(){
                    @Override
                    protected String doInBackground(String... strings) {
                        long lastUpdated = 0;
                        for(Post p : data){
                            AppLocalDb.db.postDao().insertAll(p);
                            if (p.lastUpdate > lastUpdated) lastUpdated = p.lastUpdate;
                        }
                        SharedPreferences.Editor edit = MyApplication.context.getSharedPreferences("TAG", MODE_PRIVATE).edit();
                        edit.putLong("PostsLastUpdateDate",lastUpdated);
                        edit.commit();
                        return null;
                    }
                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        if (listener!=null)  listener.onComplete(true);
                    }
                }.execute("");
            }
        });
    }
}

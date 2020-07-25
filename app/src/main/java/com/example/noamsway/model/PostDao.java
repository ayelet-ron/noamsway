package com.example.noamsway.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PostDao {
    @Query("SELECT * FROM Post WHERE category LIKE '%' || :category || '%' and isDeleted=0")
    LiveData<List<Post>> getAllPostsFromCategory(String category);

    @Query("select * from Post WHERE isDeleted=0")
    LiveData<List<Post>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Post... posts);

    @Query("delete from Post WHERE postId=:postId")
    void delete(String postId);

    @Query("SELECT * FROM Post WHERE user LIKE '%' || :email || '%' and isDeleted=0")
    LiveData<List<Post>> getAllPostsOfSpecificUser(String email);

    @Update
    void update(Post post);


}

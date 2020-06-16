package com.example.noamsway.ui.postList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.noamsway.model.Model;
import com.example.noamsway.model.Post;
import com.example.noamsway.model.PostModel;
import com.example.noamsway.ui.categories.CategoriesFragmentArgs;

import java.util.ArrayList;
import java.util.List;

public class PostListViewModel extends ViewModel {
    private LiveData<List<Post>> postsListData;
    private LiveData<List<Post>> userPostsListData;

    public PostListViewModel() {
    }
    public LiveData<List<Post>> getData(String categoryName){
        if(postsListData==null){
            postsListData = PostModel.instance.getAllPostsOfSpecificCategory(categoryName);
        }
        return postsListData;
    }
    public LiveData<List<Post>> getPostsByUser(String userEmail){
        if(userPostsListData==null){
            userPostsListData = PostModel.instance.getAllPostsOfSpecificUser(userEmail);
        }
        return userPostsListData;
    }
    public void refreshMyPost(){
        //need to call refresh that only update the local DB reference eliave
    }
    public void refreshCategoryPosts(){
        //need to call refresh that only update the local DB reference eliave

    }
}

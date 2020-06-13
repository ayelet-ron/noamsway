package com.example.noamsway.ui.newPost;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.noamsway.model.Category;
import com.example.noamsway.model.CategoryModel;
import com.example.noamsway.model.StoreModel;
import com.example.noamsway.utils.DataCallback;

import java.util.ArrayList;

public class NewPostViewModel extends ViewModel {
    private LiveData<ArrayList<String>> categoriesListData;
    public void uploadImage(Uri imagePath, DataCallback callback){
        StoreModel.uploadImage(imagePath,callback);
    }
//    public NewPostViewModel() {
//    }
//    public LiveData<ArrayList<String>> getData(){
//        if(categoriesListData==null){
//            categoriesListData = CategoryModel.getAllCategoriesName();
//        }
//        return categoriesListData;
//    }
}

package com.example.noamsway.ui.newPost;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.noamsway.model.Category;
import com.example.noamsway.model.CategoryModel;

import java.util.ArrayList;

public class NewPostViewModel extends ViewModel {
    private LiveData<ArrayList<String>> categoriesListData;

//    public NewPostViewModel() {
//    }
//    public LiveData<ArrayList<String>> getData(){
//        if(categoriesListData==null){
//            categoriesListData = CategoryModel.getAllCategoriesName();
//        }
//        return categoriesListData;
//    }
}

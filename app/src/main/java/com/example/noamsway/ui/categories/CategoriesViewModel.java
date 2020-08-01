package com.example.noamsway.ui.categories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.noamsway.model.Category;
import com.example.noamsway.model.CategoryModel;

import java.util.ArrayList;

public class CategoriesViewModel extends ViewModel {
    private LiveData<ArrayList<Category>> categoriesListData;

    public CategoriesViewModel() {
    }
}
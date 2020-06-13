package com.example.noamsway.model;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.noamsway.R;
import com.example.noamsway.utils.Listener;

import java.util.ArrayList;

public class CategoryModel {
    public static CategoryModel instance;
    public ArrayList<Category> categories;
    private CategoryModel(){
        categories = new ArrayList<>();
        this.categories.add(new Category("Patriotism", R.drawable.picture1, R.drawable.israel_map_icon, Categories.PATRIOTISM));
        this.categories.add(new Category("Trips", R.drawable.trips, R.drawable.trip_icon, Categories.TRIPS));
        this.categories.add(new Category("Army", R.drawable.capture, R.drawable.army_icon, Categories.ARMY));
        this.categories.add(new Category("News", R.drawable.news, R.drawable.news_icon, Categories.NEWS));
    }
    public static CategoryModel getInstance(){
        if (instance !=null){
            return instance;
        }
        instance = new CategoryModel();
        return instance;
    }
    public ArrayList<Category> getCategories(){
        return categories;
    }
    public ArrayList<String> getCategoriesNames(){
        ArrayList<String> names= new ArrayList<>();
        for (Category category : categories){
            names.add(category.name);
        }
        return names;
    }
    public Category getCategoryByName(String categoryName){
        for (Category category : categories){
            if (category.name.equals(categoryName)){
                return category;
            }
        }
        return null;
    }
    public static void addCategory(Category category) {
        CategoryFirebase.addCategory(category);
    }

//    public static LiveData<ArrayList<Category>> getAllCategories() {
//        MutableLiveData<ArrayList<Category>> liveData = new MutableLiveData<>();
//        liveData.setValue(new ArrayList<>());
//        CategoryFirebase.getAllCategories(new Listener<ArrayList<Category>>() {
//            @Override
//            public void onComplete(ArrayList<Category> data) {
//                liveData.setValue(data);
//            }
//        });
//        return liveData;
//    }
//
//    public static LiveData<ArrayList<String>> getAllCategoriesName() {
//        MutableLiveData<ArrayList<String>> liveData = new MutableLiveData<>();
//        liveData.setValue(new ArrayList<>());
//        CategoryFirebase.getAllCategoriesName(new Listener<ArrayList<String>>() {
//            @Override
//            public void onComplete(ArrayList<String> data) {
//                liveData.setValue(data);
//            }
//        });
//        return liveData;
//    }
//
//    public static LiveData<ArrayList<Category>> getCategoryByName(String categoryName) {
//        MutableLiveData<ArrayList<Category>> liveData = new MutableLiveData<>();
//        liveData.setValue(new ArrayList<>());
//        CategoryFirebase.getCategoryByName(categoryName, new Listener<ArrayList<Category>>() {
//            @Override
//            public void onComplete(ArrayList<Category> data) {
//                liveData.setValue(data);
//            }
//        });
//        return liveData;
//    }

    public static void initData() {
        addCategory(new Category("Patriotism", R.drawable.picture1, R.drawable.israel_map_icon, Categories.PATRIOTISM));
        addCategory(new Category("Trips", R.drawable.trips, R.drawable.trip_icon, Categories.TRIPS));
        addCategory(new Category("Army", R.drawable.capture, R.drawable.army_icon, Categories.ARMY));
        addCategory(new Category("News", R.drawable.news, R.drawable.news_icon, Categories.NEWS));
    }
}

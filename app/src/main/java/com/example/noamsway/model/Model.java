package com.example.noamsway.model;

import com.example.noamsway.R;

import java.util.LinkedList;
import java.util.List;

public class Model {
    public static final Model instance = new Model();
    List<Category> data = new LinkedList<>();
    private Model(){
        data.add(new Category("Patriotism", R.drawable.flag,Categories.PATRIOTISM));
        data.add(new Category("Trips", R.drawable.view,Categories.TRIPS));
        data.add(new Category("Flowers", R.drawable.flowers,Categories.FAMILY));
    }
    public List<Category> getAllCategories(){
        return data;
    }
}

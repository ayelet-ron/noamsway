package com.example.noamsway.model;

import android.graphics.drawable.Drawable;

enum Categories { ARMY, TRIPS, FAMILY, NOAMRON,PATRIOTISM}

public class Category {
    public int img;
    public String name;
    public Categories type;

    public Category(String name, int img, Categories type){
        this.img = img;
        this.name = name;
        this.type = type;
    }
    public Category(String name, Categories type){
        this.name = name;
        this.type = type;
    }
}

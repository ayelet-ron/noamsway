package com.example.noamsway.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

enum Categories { ARMY, TRIPS, NEWS, NOAMRON,PATRIOTISM}

public class Category implements Serializable {
    public int img;
    public int icon;
    public String name;
    public Categories type;

    public Category(String name, int img,int icon, Categories type){
        this.img = img;
        this.name = name;
        this.type = type;
        this.icon=icon;
    }
    public Category(String name, Categories type){
        this.name = name;
        this.type = type;
    }
}

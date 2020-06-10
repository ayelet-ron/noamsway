package com.example.noamsway.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categories getType() {
        return type;
    }

    public void setType(Categories type) {
        this.type = type;
    }

    public Category(String name, Categories type){
        this.name = name;
        this.type = type;
    }
    public Category(){
    }
}

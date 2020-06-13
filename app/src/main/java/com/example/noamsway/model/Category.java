package com.example.noamsway.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Category implements Serializable {
    public long img;
    public long icon;
    public String name;
    public Categories type;

    public Category(String name, long img,long icon, Categories type){
        this.img = img;
        this.name = name;
        this.type = type;
        this.icon=icon;
    }

    public long getImg() {
        return img;
    }

    public void setImg(long img) {
        this.img = img;
    }

    public long getIcon() {
        return icon;
    }

    public void setIcon(long icon) {
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
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("img", img);
        result.put("icon", icon);
        result.put("type", type);
        result.put("name", name);
        return result;
    }
    public static Category factory(Map<String, Object> json){
        Category category = new Category();
        category.setImg((long)json.get("img"));
        category.setIcon((long)json.get("icon"));
        category.setType(Categories.valueOf((String) json.get("type")));
        category.setName((String)json.get("name"));
        return category;
    }
}

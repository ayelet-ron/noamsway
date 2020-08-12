package com.example.noamsway.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Category implements Serializable {
    public String img;
    public String icon;
    public String name;
    public Categories type;

    public Category(String name, String img, String icon, Categories type) {
        this.img = img;
        this.name = name;
        this.type = type;
        this.icon = icon;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
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

    public Category(String name, Categories type) {
        this.name = name;
        this.type = type;
    }

    public Category() {
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("img", img);
        result.put("icon", icon);
        result.put("type", type);
        result.put("name", name);
        return result;
    }

    public static Category factory(Map<String, Object> json) {
        Category category = new Category();
        category.setImg((String) json.get("img"));
        category.setIcon((String) json.get("icon"));
        category.setType(Categories.valueOf((String) json.get("type")));
        category.setName((String) json.get("name"));
        return category;
    }
}

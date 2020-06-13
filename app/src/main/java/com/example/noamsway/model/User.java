package com.example.noamsway.model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {
    public String email;
    public String fullName;
    public String password;

    public User(String email,String fullName,String password){
        this.email=email;
        this.password=password;
        this.fullName=fullName;
    }
    public User(String email,String password){
        this.email=email;
        this.password=password;
    }
    public User(){
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("fullName", fullName);
        result.put("password", password);
        return result;
    }
    public static User factory(Map<String, Object> json){
        User user = new User();
        user.setEmail((String)json.get("email"));
        user.setFullName((String)json.get("fullName"));
        return user;
    }
}

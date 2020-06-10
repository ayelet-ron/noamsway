package com.example.noamsway.model;

import java.io.Serializable;

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
}

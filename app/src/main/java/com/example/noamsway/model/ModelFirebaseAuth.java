package com.example.noamsway.model;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ModelFirebaseAuth {
    private FirebaseAuth mAuth;
    public ModelFirebaseAuth(){
        mAuth = FirebaseAuth.getInstance();
    }
    public boolean areUserLoggedIn(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser==null){
            return false;
        }
        return true;
    }

    public void login(String email,String password,String fullName){

    }


}

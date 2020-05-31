package com.example.noamsway.model;

import android.util.Log;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;
import java.util.concurrent.Executor;

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

    public void login(String email,String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Log.d("TAG", "full name:"+user.getDisplayName());
                }else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                    //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void signUp(String email, String password, final String fullName){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(fullName).build();
                    user.updateProfile(profileUpdates);
                }else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                    //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void longout(){
        mAuth.signOut();
    }
    public String getUserEmail(){
        return Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
    }
    public String getUserFullName(){
        return Objects.requireNonNull(mAuth.getCurrentUser()).getDisplayName();
    }
}

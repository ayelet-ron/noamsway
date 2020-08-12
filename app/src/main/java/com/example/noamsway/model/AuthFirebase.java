package com.example.noamsway.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.noamsway.utils.Listener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

public class AuthFirebase {
    FirebaseAuth mAuth;

    public AuthFirebase() {
        mAuth = FirebaseAuth.getInstance();
    }

    static boolean areUserLoggedIn() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            return false;
        }
        return true;
    }

    public void login(User user, final Listener<Boolean> listener) {
        mAuth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    listener.onComplete(true);
                    Log.d("TAG", "createUserWithEmail:success");
                } else {
                    // If sign in fails, display a message to the user.
                    listener.onComplete(false);
                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                }
            }
        });
    }

    static void signUp(User user, final Listener<Boolean> listener) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(user.email, user.password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    listener.onComplete(true);
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success");
                    FirebaseUser Firebaseuser = mAuth.getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(user.fullName).build();
                    Firebaseuser.updateProfile(profileUpdates);
                } else {
                    listener.onComplete(false);
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                }
            }
        });
    }

    static void longout() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
    }

    static String getUserEmail() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        return Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
    }

    static String getUserFullName() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        return Objects.requireNonNull(mAuth.getCurrentUser()).getDisplayName();
    }

    static User getCurrentUser() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = Objects.requireNonNull(mAuth.getCurrentUser());
        User user = new User();
        user.setEmail(firebaseUser.getEmail());
        user.setFullName(firebaseUser.getDisplayName());
        return user;
    }
}

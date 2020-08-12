package com.example.noamsway.model;

import com.example.noamsway.utils.Listener;

public class ModelAuth {
    private AuthFirebase authFirebase;
    public static final ModelAuth instance = new ModelAuth();

    private ModelAuth() {
        authFirebase = new AuthFirebase();
    }

    public boolean areUserLoggedIn() {
        return AuthFirebase.areUserLoggedIn();

    }

    public void login(User user, Listener<Boolean> listener) {
        authFirebase.login(user, listener);
    }

    public void signUp(User user, Listener<Boolean> listener) {
        AuthFirebase.signUp(user, listener);
    }

    public void longout() {
        AuthFirebase.longout();
    }

    public String getUserEmail() {
        return AuthFirebase.getUserEmail();
    }

    public String getUserFullName() {
        return AuthFirebase.getUserFullName();
    }

    public User getCurrentUser() {
        return AuthFirebase.getCurrentUser();
    }
}

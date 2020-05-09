package com.example.noamsway.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SignInViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SignInViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is auth fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
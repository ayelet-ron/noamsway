package com.example.noamsway.ui.postDetails;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import com.example.noamsway.model.StoreModel;
import com.example.noamsway.utils.DataCallback;

public class PostDetailsViewModel extends ViewModel {
    public void uploadImage(Uri imagePath, DataCallback callback){
        StoreModel.uploadImage(imagePath,callback);
    }
}

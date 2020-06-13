package com.example.noamsway.model;

import android.net.Uri;

import com.example.noamsway.utils.DataCallback;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class StoreModel {
    public static StoreFirebase instance = new StoreFirebase();


    public static void uploadImage(Uri imagePath , DataCallback callback)
    {
       instance.uploadImage(imagePath,callback);
    }
}

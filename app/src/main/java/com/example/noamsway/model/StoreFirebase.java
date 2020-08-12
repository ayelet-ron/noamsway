package com.example.noamsway.model;

import android.net.Uri;

import com.example.noamsway.utils.DataCallback;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class StoreFirebase {
    private final String REFERENCE_URL = "gs://noamsway-e82ae.appspot.com";
    private StorageReference storageRef;
    private FirebaseStorage fbStorage;
    private UploadTask uploadTask;

    public StoreFirebase() {
        fbStorage = FirebaseStorage.getInstance();
        storageRef = fbStorage.getReferenceFromUrl(REFERENCE_URL);
    }


    public void uploadImage(Uri imagePath, DataCallback callback) {
        fbStorage = FirebaseStorage.getInstance();
        storageRef = fbStorage.getReferenceFromUrl(REFERENCE_URL);
        storageRef = fbStorage.getReference();
        StorageReference postImgRef = storageRef.child("images/" + imagePath.getLastPathSegment());
        uploadTask = postImgRef.putFile(imagePath);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                postImgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        callback.onData(uri.toString());
                    }
                });
            }
        });
    }
}


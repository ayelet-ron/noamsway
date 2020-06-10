package com.example.noamsway.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.noamsway.utils.Listener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class PostFirebase {
    final static String POST_COLLECTION = "posts";

    public static void getAllPosts(final Listener<ArrayList<Post>> listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(POST_COLLECTION).get().addOnCompleteListener((task) -> {
            ArrayList<Post> postData = null;
            if (task.isSuccessful()) {
                postData = new ArrayList<Post>();
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Post post = doc.toObject(Post.class);
                    postData.add(post);
                }
            }
            listener.onComplete(postData);
        });
    }
    public static void getAllPostsOfSpecificCategory(String categoryName, final Listener<ArrayList<Post>> listener){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(POST_COLLECTION).whereEqualTo("category.name",categoryName).get().addOnCompleteListener((task)->{
            ArrayList<Post> postData = null;
            if(task.isSuccessful()){
                postData = new ArrayList<Post>();
                for(QueryDocumentSnapshot doc : task.getResult()){
                    Post post = doc.toObject(Post.class);
                    postData.add(post);
                }
            }
            listener.onComplete(postData);
        });
    }
    public static void getAllPostsOfSpecificUser(String userEmail, final Listener<ArrayList<Post>> listener){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(POST_COLLECTION).whereEqualTo("user.email",userEmail).get().addOnCompleteListener((task)->{
            ArrayList<Post> postData = null;
            if(task.isSuccessful()){
                postData = new ArrayList<Post>();
                for(QueryDocumentSnapshot doc : task.getResult()){
                    Post post = doc.toObject(Post.class);
                    postData.add(post);
                }
            }
            listener.onComplete(postData);
        });
    }
    public static void addPost(Post post, final Listener<Boolean> listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(POST_COLLECTION).add(post).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                db.collection(POST_COLLECTION).document(documentReference.getId()).update("postId", documentReference.getId());
                listener.onComplete(true);
                Log.d("TAG", "DocumentSnapshot written with ID: " + documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onComplete(false);
                Log.w("TAG", "Error adding document", e);
            }
        });
    }
    public static void updatePost(Post post, final Listener<Boolean> listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(POST_COLLECTION).document(post.postId).update(post.toMap()).addOnCompleteListener((task)->{
            if(task.isSuccessful()){
                listener.onComplete(true);
            }
            else{
                listener.onComplete(false);
            }
        });
    }
    public static void deletePost(String postId, final Listener<Boolean> listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(POST_COLLECTION).document(postId).delete().addOnCompleteListener((task)->{
            if(task.isSuccessful()){
                listener.onComplete(true);
            }
            else{
                listener.onComplete(false);
            }

        });
    }
//    public PostFirebase(){
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        FirebaseFirestoreSettings settings = new
//                FirebaseFirestoreSettings.Builder()
//                .setPersistenceEnabled(true)
//                .build();
//        db.setFirestoreSettings(settings);
//    }
}

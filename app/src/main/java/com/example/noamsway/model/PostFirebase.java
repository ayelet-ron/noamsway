package com.example.noamsway.model;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.noamsway.utils.Listener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PostFirebase {
    final static String POST_COLLECTION = "posts";

    public static void getAllPosts(final Listener<ArrayList<Post>> listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(POST_COLLECTION).get().addOnCompleteListener((task) -> {
            ArrayList<Post> postData = null;
            if (task.isSuccessful()) {
                postData = new ArrayList<Post>();
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Map<String,Object> json = doc.getData();
                    Post post = Post.factory(json);
                    postData.add(post);
                }
            }
            listener.onComplete(postData);
        });
    }
    public static void getAllPostsSince(long since, final Listener<List<Post>> listener){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Timestamp ts = new Timestamp(since,0);
        db.collection(POST_COLLECTION).whereGreaterThanOrEqualTo("lastUpdate", ts).get().addOnCompleteListener((task)->{
            List<Post> postData = null;
            if(task.isSuccessful()){
                postData = new LinkedList<>();
                for(QueryDocumentSnapshot doc : task.getResult()){
                    Map<String,Object> json = doc.getData();
                    Post post = Post.factory(json);
                    postData.add(post);
                }
            }
            listener.onComplete(postData);
        });
    }
    public static void getAllPostsOfSpecificCategorySince(long since,String categoryName, final Listener<List<Post>> listener){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Timestamp ts = new Timestamp(since,0);
        db.collection(POST_COLLECTION).whereEqualTo("category.name",categoryName).whereGreaterThanOrEqualTo("lastUpdate", ts).get().addOnCompleteListener((task)->{
            List<Post> postData = null;
            if(task.isSuccessful()){
                postData = new LinkedList<>();
                for(QueryDocumentSnapshot doc : task.getResult()){
                    Map<String,Object> json = doc.getData();
                    Post post = Post.factory(json);
                    postData.add(post);
                }
            }
            listener.onComplete(postData);
        });
    }
    public static void getAllPostsOfSpecificCategory(String categoryName, final Listener<ArrayList<Post>> listener){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(POST_COLLECTION).whereEqualTo("category.name",categoryName).whereEqualTo("isDeleted",false).get().addOnCompleteListener((task)->{
            ArrayList<Post> postData = null;
            if(task.isSuccessful()){
                postData = new ArrayList<Post>();
                for(QueryDocumentSnapshot doc : task.getResult()){
                    Map<String,Object> json = doc.getData();
                    Post post = Post.factory(json);
                    postData.add(post);
                }
            }
            listener.onComplete(postData);
        });
    }
    public static void getAllPostsOfSpecificUserSince(long since,String userEmail, final Listener<List<Post>> listener){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Timestamp ts = new Timestamp(since,0);
        db.collection(POST_COLLECTION).whereEqualTo("user.email",userEmail).whereGreaterThanOrEqualTo("lastUpdate", ts).get().addOnCompleteListener((task)->{
            List<Post> postData = null;
            if(task.isSuccessful()){
                postData = new LinkedList<>();
                for(QueryDocumentSnapshot doc : task.getResult()){
                    Map<String,Object> json = doc.getData();
                    Post post = Post.factory(json);
                    postData.add(post);
                }
            }
            listener.onComplete(postData);
        });
    }
    public static void addPost(Post post, final Listener<Boolean> listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(POST_COLLECTION).add(post.toMap()).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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
    public static void deletePost_old(String postId, final Listener<Boolean> listener) {
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
    public static void deletePost(String postId, final Listener<Boolean> listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(POST_COLLECTION).document(postId).update("isDeleted",true,"lastUpdate",FieldValue.serverTimestamp()).addOnCompleteListener((task)->{
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

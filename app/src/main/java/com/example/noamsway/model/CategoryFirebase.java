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

public class CategoryFirebase {
    private final static String CATEGORY_COLLECTION = "categories";

    public static void getAllCategories(final Listener<ArrayList<Category>> listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(CATEGORY_COLLECTION).get().addOnCompleteListener((task) -> {
            ArrayList<Category> categoryData = null;
            if (task.isSuccessful()) {
                categoryData = new ArrayList<Category>();
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Category category = doc.toObject(Category.class);
                    categoryData.add(category);
                }
            }
            listener.onComplete(categoryData);
        });
    }
    public static void getAllCategoriesName(final Listener<ArrayList<String>> listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(CATEGORY_COLLECTION).get().addOnCompleteListener((task) -> {
            ArrayList<String> categoryData = null;
            if (task.isSuccessful()) {
                categoryData = new ArrayList<String>();
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    String categoryName = doc.getString("name");
                    categoryData.add(categoryName);
                }
            }
            listener.onComplete(categoryData);
        });
    }
    public static void getCategoryByName(String categoryName,final Listener<ArrayList<Category>> listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(CATEGORY_COLLECTION).whereEqualTo("name",categoryName).get().addOnCompleteListener((task) -> {
            ArrayList<Category> categoryData = null;
            if (task.isSuccessful()) {
                categoryData = new ArrayList<Category>();
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Category category = doc.toObject(Category.class);
                    categoryData.add(category);
                }
            }
            listener.onComplete(categoryData);
        });
    }
//    public static void getAllPostsOfSpecificCategory(String categoryName, final Listener<ArrayList<Post>> listener){
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection(POST_COLLECTION).whereEqualTo("").get().addOnCompleteListener((task)->{
//            ArrayList<Post> postData = null;
//            if(task.isSuccessful()){
//                postData = new ArrayList<Post>();
//                for(QueryDocumentSnapshot doc : task.getResult()){
//                    Post post = doc.toObject(Post.class);
//                    postData.add(post);
//                }
//            }
//            listener.onComplete(postData);
//        });
//    }

    public static void addCategory(Category category) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(CATEGORY_COLLECTION).document(category.getType().name()).set(category).addOnCompleteListener((task)->{
            if(task.isSuccessful()){
                Log.d("TAG","category was added" );
            }
        });
    }
}

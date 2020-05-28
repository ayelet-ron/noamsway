package com.example.noamsway.model;

import android.os.AsyncTask;

import com.example.noamsway.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Model {
    public static final Model instance = new Model();
    ModelFirebase modelFirebase;
    ModelFirebaseAuth modelFirebaseAuth;

    ArrayList<Category> categoriesList = new ArrayList<>();
    ArrayList<Post> postsList = new ArrayList<>();
    ArrayList<Post> categoryPostsList = new ArrayList<>();
    private Model(){
        this.modelFirebase = new ModelFirebase();
        this.modelFirebaseAuth = new ModelFirebaseAuth();

        Category patriotism = new Category("Patriotism", R.drawable.picture1,Categories.PATRIOTISM);
        Category trips = new Category("Trips", R.drawable.trips,Categories.TRIPS);
        Category army = new Category("Army", R.drawable.capture,Categories.ARMY);
        categoriesList.add(patriotism);
        categoriesList.add(trips);
        categoriesList.add(army);
        categoriesList.add(new Category("News", R.drawable.news,Categories.NOAMRON));
        Post pat = new Post(R.drawable.flowers,"red flowers","my red flowers story with my father","Ayelet Ron",patriotism);
        Post trip = new Post(R.drawable.back,"just look at the sun","my red flowers story with my father","Chen Amiell",trips);
        postsList.add(pat);
        postsList.add(trip);
        postsList.add(new Post(R.drawable.trip,"just look at the view","my red flowers story with my father","Noam Ron",army));
        categoryPostsList.add(pat);
        categoryPostsList.add(trip);

    }
    public boolean areUserLoggedIn(){
        return this.modelFirebaseAuth.areUserLoggedIn();
    }
    public ArrayList<Category> getAllCategories(){
        return categoriesList;
    }
    public ArrayList<Post> getAllPosts(){
        return postsList;
    }
//    public ArrayList<String> getAllCategoriesNames(){
//        return categoriesList.forEach(s->s.name);
//    }
    public ArrayList<Post> getAllPostOfCategory(String categoryName){
        if (categoryName.equals("Patriotism")){
            return categoryPostsList;
        }
        return postsList;
    }
    public interface getAllExampleListener {
        void onComplete(List<String> data);
    }


    class MyAsyncTask extends AsyncTask<String, String, String> {
        List<String> data;

        @Override
        protected String doInBackground(String... strings) {
            //data = AppLocalDb.db.getAll()
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //listener.onComplete(data);
        }
    }
}



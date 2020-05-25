package com.example.noamsway.model;

import android.os.AsyncTask;

import com.example.noamsway.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Model {
    public static final Model instance = new Model();
    ArrayList<Category> categoriesList = new ArrayList<>();
    ArrayList<Post> postsList = new ArrayList<>();
    private Model(){
        categoriesList.add(new Category("Patriotism", R.drawable.picture1,Categories.PATRIOTISM));
        categoriesList.add(new Category("Trips", R.drawable.trips,Categories.TRIPS));
        categoriesList.add(new Category("Army", R.drawable.capture,Categories.ARMY));
        categoriesList.add(new Category("News", R.drawable.news,Categories.NOAMRON));
        postsList.add(new Post(R.drawable.flowers,"red flowers","my red flowers story with my father","Ayelet Ron"));
        postsList.add(new Post(R.drawable.back,"just look at the sun","my red flowers story with my father","Chen Amiell"));
        postsList.add(new Post(R.drawable.trip,"just look at the view","my red flowers story with my father","Noam Ron"));
    }
    public ArrayList<Category> getAllCategories(){
        return categoriesList;
    }
    public ArrayList<Post> getAllPosts(){
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



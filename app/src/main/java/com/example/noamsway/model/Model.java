package com.example.noamsway.model;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.noamsway.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Model {
    public static final Model instance = new Model();

    private Model(){

//        Category patriotism = new Category("Patriotism", R.drawable.picture1,R.drawable.patriotism_icon,Categories.PATRIOTISM);
//        Category trips = new Category("Trips", R.drawable.trips,R.drawable.trip_icon,Categories.TRIPS);
//        Category army = new Category("Army", R.drawable.capture,R.drawable.army_icon,Categories.ARMY);
//        Category newsCategory = new Category("News", R.drawable.news,R.drawable.news_icon,Categories.NEWS);
//        categoriesList.add(patriotism);
//        categoriesList.add(trips);
//        categoriesList.add(army);
//        categoriesList.add(newsCategory);
//        Post pat = new Post(R.drawable.flowers,"red flowers","my red flowers story with my father","Ayelet Ron",patriotism);
//        Post trip = new Post(R.drawable.back,"just look at the sun","my red flowers story with my father","Chen Amiel",trips);
//        Post news = new Post(R.drawable.view,"beautiful day","just go out and look at the view","Chen Amiel",newsCategory);
//        postsList.add(pat);
//        postsList.add(trip);
//        postsList.add(news);
//        postsList.add(new Post(R.drawable.trip,"just look at the view","my red flowers story with my father","Noam Ron",army));
//        categoryPostsList.add(pat);
//        categoryPostsList.add(trip);
//        categoryPostsList.add(news);

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



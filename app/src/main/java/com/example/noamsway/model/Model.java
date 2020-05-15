package com.example.noamsway.model;

import android.os.AsyncTask;

import com.example.noamsway.R;

import java.util.LinkedList;
import java.util.List;

public class Model {
    public static final Model instance = new Model();
    List<Category> data = new LinkedList<>();
    private Model(){
        data.add(new Category("Patriotism", R.drawable.picture1,Categories.PATRIOTISM));

        data.add(new Category("Trips", R.drawable.trip4,Categories.TRIPS));
        data.add(new Category("Army", R.drawable.capture,Categories.ARMY));
        //data.add(new Category("Patriotism", R.drawable.patriotism_fin,Categories.PATRIOTISM));
        data.add(new Category("Patriotism", R.drawable.flag_final,Categories.PATRIOTISM));
        //data.add(new Category("Trips", R.drawable.trips3,Categories.TRIPS));
        //data.add(new Category("Trips", R.drawable.trips,Categories.TRIPS));
        //data.add(new Category("Army", R.drawable.army_fin,Categories.ARMY));

        //data.add(new Category("Army", R.drawable.army,Categories.ARMY));

        //data.add(new Category("Flowers", R.drawable.flowers,Categories.FAMILY));
    }
    public List<Category> getAllCategories(){
        return data;
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



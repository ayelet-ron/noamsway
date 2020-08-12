package com.example.noamsway.model;

import android.os.AsyncTask;

import java.util.List;

public class Model {
    public static final Model instance = new Model();

    private Model() {
    }

    public interface getAllExampleListener {
        void onComplete(List<String> data);
    }


    class MyAsyncTask extends AsyncTask<String, String, String> {
        List<String> data;

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}



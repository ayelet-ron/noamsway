package com.example.noamsway.model;

import java.util.ArrayList;

public class CategoryModel {
    public static CategoryModel instance;
    public ArrayList<Category> categories;

    private CategoryModel() {
        categories = new ArrayList<>();
        this.categories.add(new Category("Patriotism", "https://firebasestorage.googleapis.com/v0/b/noamsway-e82ae.appspot.com/o/category%2FPatriotismCategory.png?alt=media&token=03629bbe-b110-4bc4-b20d-8304d4b2b3cf", "https://firebasestorage.googleapis.com/v0/b/noamsway-e82ae.appspot.com/o/category%2FPatriotismIcon.png?alt=media&token=3760cceb-6b91-4381-94f3-5efd04270d63", Categories.PATRIOTISM));
        this.categories.add(new Category("Trips", "https://firebasestorage.googleapis.com/v0/b/noamsway-e82ae.appspot.com/o/category%2FTripsCategory.png?alt=media&token=417a4b55-dd98-4bb4-9f57-342c32daeed9", "https://firebasestorage.googleapis.com/v0/b/noamsway-e82ae.appspot.com/o/category%2FTripsIcon.png?alt=media&token=6706a7bd-55c3-4029-bdab-66509347019f", Categories.TRIPS));
        this.categories.add(new Category("Army", "https://firebasestorage.googleapis.com/v0/b/noamsway-e82ae.appspot.com/o/category%2FArmyCategory.PNG?alt=media&token=f4efeded-e8fc-4591-b725-c0f38279b0c9", "https://firebasestorage.googleapis.com/v0/b/noamsway-e82ae.appspot.com/o/category%2FArmyIcon.png?alt=media&token=03b2cb8f-4c54-45cf-be3b-b6fd68f18b09", Categories.ARMY));
        this.categories.add(new Category("News", "https://firebasestorage.googleapis.com/v0/b/noamsway-e82ae.appspot.com/o/category%2FNewsCategory.png?alt=media&token=dc84782f-43d4-4401-92e8-9261be0cdcba", "https://firebasestorage.googleapis.com/v0/b/noamsway-e82ae.appspot.com/o/category%2FNewsIcon.png?alt=media&token=de6108d2-b8bc-412b-8b38-073f9cece30a", Categories.NEWS));
    }

    public static CategoryModel getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new CategoryModel();
        return instance;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public ArrayList<String> getCategoriesNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Category category : categories) {
            names.add(category.name);
        }
        return names;
    }

    public Category getCategoryByName(String categoryName) {
        for (Category category : categories) {
            if (category.name.equals(categoryName)) {
                return category;
            }
        }
        return null;
    }

    public static void addCategory(Category category) {
        CategoryFirebase.addCategory(category);
    }

//    public static LiveData<ArrayList<Category>> getAllCategories() {
//        MutableLiveData<ArrayList<Category>> liveData = new MutableLiveData<>();
//        liveData.setValue(new ArrayList<>());
//        CategoryFirebase.getAllCategories(new Listener<ArrayList<Category>>() {
//            @Override
//            public void onComplete(ArrayList<Category> data) {
//                liveData.setValue(data);
//            }
//        });
//        return liveData;
//    }
//
//    public static LiveData<ArrayList<String>> getAllCategoriesName() {
//        MutableLiveData<ArrayList<String>> liveData = new MutableLiveData<>();
//        liveData.setValue(new ArrayList<>());
//        CategoryFirebase.getAllCategoriesName(new Listener<ArrayList<String>>() {
//            @Override
//            public void onComplete(ArrayList<String> data) {
//                liveData.setValue(data);
//            }
//        });
//        return liveData;
//    }
//
//    public static LiveData<ArrayList<Category>> getCategoryByName(String categoryName) {
//        MutableLiveData<ArrayList<Category>> liveData = new MutableLiveData<>();
//        liveData.setValue(new ArrayList<>());
//        CategoryFirebase.getCategoryByName(categoryName, new Listener<ArrayList<Category>>() {
//            @Override
//            public void onComplete(ArrayList<Category> data) {
//                liveData.setValue(data);
//            }
//        });
//        return liveData;
//    }

    public static void initData() {
//        addCategory(new Category("Patriotism", R.drawable.picture1, R.drawable.israel_map_icon, Categories.PATRIOTISM));
//        addCategory(new Category("Trips", R.drawable.trips, R.drawable.trip_icon, Categories.TRIPS));
//        addCategory(new Category("Army", R.drawable.capture, R.drawable.army_icon, Categories.ARMY));
//        addCategory(new Category("News", R.drawable.news, R.drawable.news_icon, Categories.NEWS));
    }
}

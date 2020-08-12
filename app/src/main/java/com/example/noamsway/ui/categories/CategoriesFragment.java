package com.example.noamsway.ui.categories;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noamsway.R;
import com.example.noamsway.model.Category;
import com.example.noamsway.model.CategoryModel;
import com.example.noamsway.utils.RecyclerViewClickListener;

import java.util.ArrayList;

public class CategoriesFragment extends Fragment implements RecyclerViewClickListener {
    private ArrayList<Category> categoriesList;
    private LiveData<ArrayList<Category>> liveDataCategories;
    private RecyclerView recyclerView;
    private CategoriesAdapter adapter;
    private CategoriesViewModel categoriesViewModel;

    public CategoriesFragment() {
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoriesViewModel =
                ViewModelProviders.of(this).get(CategoriesViewModel.class);
        categoriesList = CategoryModel.getInstance().getCategories();
        View root = inflater.inflate(R.layout.fragment_categories, container, false);
        recyclerView = root.findViewById(R.id.categories_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        this.adapter = new CategoriesAdapter(categoriesList, this);
        recyclerView.setAdapter(this.adapter);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onItemClick(int position) {
        Log.d("TAG", "row was clicked" + position);
        NavController nav = NavHostFragment.findNavController(this);
        CategoriesFragmentDirections.ActionNavCategoryToPostListFragment action = CategoriesFragmentDirections.actionNavCategoryToPostListFragment(categoriesList.get(position).name);
        nav.navigate(action);
    }
}

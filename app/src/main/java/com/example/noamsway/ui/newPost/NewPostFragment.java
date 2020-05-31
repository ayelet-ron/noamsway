package com.example.noamsway.ui.newPost;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.noamsway.R;
import com.example.noamsway.model.Category;
import com.example.noamsway.ui.postList.postListFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NewPostFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    private Category category;
    private String categoryName;
    private String description;
    private String postName;
    private String image;
    private NewPostViewModel mViewModel;
    private List<String> categoriesNames = new ArrayList<>();
    private ProgressBar progressBar;
    private String categoryDefaultName;

    public static NewPostFragment newInstance() {
        return new NewPostFragment();
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.new_post_fragment, container, false);
        AppCompatSpinner dropdown = root.findViewById(R.id.enter_category);
        this.categoriesNames.add("Trips");
        this.categoriesNames.add("Army");
        this.categoriesNames.add("News");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, categoriesNames);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
        progressBar = root.findViewById(R.id.add_post_progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        categoryDefaultName = NewPostFragmentArgs.fromBundle(getArguments()).getCategory();
        int index = categoriesNames.indexOf(categoryDefaultName);
        dropdown.setSelection(index);
        Button save = root.findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewPostViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.categoryName = categoriesNames.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

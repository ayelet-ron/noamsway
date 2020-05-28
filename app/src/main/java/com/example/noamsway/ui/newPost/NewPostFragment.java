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
import android.widget.Spinner;

import com.example.noamsway.R;
import com.example.noamsway.model.Category;
import com.example.noamsway.model.Post;

import java.util.ArrayList;
import java.util.List;

public class NewPostFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    private Category category;
    private NewPostViewModel mViewModel;

    public static NewPostFragment newInstance() {
        return new NewPostFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.new_post_fragment, container, false);
        Spinner dropdown = root.findViewById(R.id.spinner);
        List<String> categories = new ArrayList<>();
        categories.add("trips");
        categories.add("army");
        categories.add("news");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, categories);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
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

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

package com.example.noamsway.ui.postList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noamsway.R;
import com.example.noamsway.model.Model;
import com.example.noamsway.model.Post;
import com.example.noamsway.utils.RecyclerViewClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class postListFragment extends Fragment implements RecyclerViewClickListener {
    NavController nav;
    FloatingActionButton fab;
    ArrayList<Post> postsList;
    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private PostListViewModel postListViewModel;
    View root;


    public postListFragment() {
        postsList = Model.instance.getAllPosts();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        postListViewModel = ViewModelProviders.of(this).get(PostListViewModel.class);
        root = inflater.inflate(R.layout.post_list_fragment, container, false);
        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        this.adapter = new PostAdapter(postsList, this);
        recyclerView.setAdapter(this.adapter);
        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController nav = NavHostFragment.findNavController(postListFragment.this);
                nav.navigate(R.id.action_postListFragment_to_newPostFragment);
            }
        });
        return this.root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    @Override
    public void onItemClick(int position) {
        Log.d("TAG","row was clicked" + position);
//        NavController nav = NavHostFragment.findNavController(this);
//        nav.navigate(R.id.action_nav_category_to_postListFragment);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        //mViewModel = ViewModelProviders.of(this).get(PostListViewModel.class);
//        // TODO: Use the ViewModel
//    }
}

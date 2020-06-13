package com.example.noamsway.ui.postList;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.noamsway.MainActivity;
import com.example.noamsway.R;
import com.example.noamsway.model.ModelAuth;
import com.example.noamsway.model.Post;
import com.example.noamsway.ui.categories.CategoriesFragmentArgs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class postListFragment extends PostLists {


    public postListFragment() {
        super();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        postListViewModel = ViewModelProviders.of(this).get(PostListViewModel.class);
        categoryName = CategoriesFragmentArgs.fromBundle(getArguments()).getCategoryName();
        liveDataPosts = postListViewModel.getData(categoryName);
        liveDataPosts.observe(this, new Observer<ArrayList<Post>>() {
            @Override
            public void onChanged(ArrayList<Post> posts) {
                postsList = posts;
                adapter.setPosts(postsList);
            }
        });

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = super.onCreateView(inflater, container, savedInstanceState);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(categoryName);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                postListViewModel.refreshCategoryPosts();
                swipeRefresh.setRefreshing(false);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController nav = NavHostFragment.findNavController(postListFragment.this);
                if(ModelAuth.instance.areUserLoggedIn()){
                    postListFragmentDirections.ActionPostListFragmentToNewPostFragment action = postListFragmentDirections.actionPostListFragmentToNewPostFragment(categoryName);
                    nav.navigate(action);
                }
                else{
                    Toast.makeText(getActivity(),"You Must Login Before Posting New Post",Toast.LENGTH_SHORT).show();
                    nav.navigate(R.id.action_postListFragment_to_nav_login);
                }
            }
        });
        return root;
    }
    @Override
    public void onItemClick(int position) {
        Log.d("TAG","row was clicked" + position);
        NavController nav = NavHostFragment.findNavController(this);
        postListFragmentDirections.ActionPostListFragmentToPostDetailsFragment action = postListFragmentDirections.actionPostListFragmentToPostDetailsFragment(postsList.get(position),false);
        nav.navigate(action);
    }
}

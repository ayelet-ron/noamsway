package com.example.noamsway.ui.postList;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.noamsway.MainActivity;
import com.example.noamsway.R;
import com.example.noamsway.model.ModelAuth;
import com.example.noamsway.model.Post;
import com.example.noamsway.ui.categories.CategoriesFragmentArgs;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class nav_my_posts extends PostLists {

    public nav_my_posts() {
        super();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        postListViewModel = ViewModelProviders.of(this).get(PostListViewModel.class);
        String email = ModelAuth.instance.getUserEmail();
        liveDataPosts = postListViewModel.getPostsByUser(email);
        liveDataPosts.observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                postsList = posts;
                adapter.setPosts(postsList);
            }
        });

    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = super.onCreateView(inflater,container,savedInstanceState);
        fab.setVisibility(View.INVISIBLE);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                postListViewModel.refreshMyPost();
                swipeRefresh.setRefreshing(false);
            }
        });
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("My Posts");
        return root;
    }
    @Override
    public void onItemClick(int position) {
        Log.d("TAG","row was clicked" + position);
        NavController nav = NavHostFragment.findNavController(this);
        nav_my_postsDirections.ActionNavMyPostsToPostDetailsFragment action = nav_my_postsDirections.actionNavMyPostsToPostDetailsFragment(postsList.get(position),true);
        nav.navigate(action);
    }
}

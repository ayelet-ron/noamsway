package com.example.noamsway.ui.postList;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.noamsway.MainActivity;
import com.example.noamsway.R;
import com.example.noamsway.model.ModelAuth;
import com.example.noamsway.model.Post;
import com.example.noamsway.utils.Listener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class nav_my_posts extends PostLists {
    String currentUserEmail;
    Boolean isRefreshing = false;

    public nav_my_posts() {
        super();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        postListViewModel = ViewModelProviders.of(this).get(PostListViewModel.class);
        currentUserEmail = ModelAuth.instance.getUserEmail();


    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = super.onCreateView(inflater,container,savedInstanceState);
        swipeRefresh.setRefreshing(true);
        liveDataPosts = postListViewModel.getPostsByUser(currentUserEmail);
        liveDataPosts.observe(getViewLifecycleOwner(), new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                postsList = posts;
                adapter.setPosts(postsList);
                swipeRefresh.setRefreshing(false);
            }
        });
        fab.setVisibility(View.INVISIBLE);
        swipeRefresh.setOnRefreshListener(()-> {
            postListViewModel.refreshMyPost(currentUserEmail, new Listener<Boolean>() {
                @Override
                public void onComplete(Boolean data) {
                    if(data){
                        swipeRefresh.setRefreshing(false);
                    }
                }
            });

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

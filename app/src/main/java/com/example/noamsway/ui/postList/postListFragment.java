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

import com.example.noamsway.MainActivity;
import com.example.noamsway.R;
import com.example.noamsway.model.ModelAuth;
import com.example.noamsway.model.Post;
import com.example.noamsway.ui.categories.CategoriesFragmentArgs;
import com.example.noamsway.utils.Listener;

import java.util.List;


public class postListFragment extends PostLists {
    Boolean isRefreshing = false;

    public postListFragment() {
        super();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        postListViewModel = ViewModelProviders.of(this).get(PostListViewModel.class);
        categoryName = CategoriesFragmentArgs.fromBundle(getArguments()).getCategoryName();



    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = super.onCreateView(inflater, container, savedInstanceState);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(categoryName);
        swipeRefresh.setRefreshing(true);
        liveDataPosts = postListViewModel.getData(categoryName);
        liveDataPosts.observe(getViewLifecycleOwner(), new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                postsList = posts;
                adapter.setPosts(postsList);
                swipeRefresh.setRefreshing(false);
            }
        });
        swipeRefresh.setOnRefreshListener(() -> {
            postListViewModel.refreshCategoryPosts(categoryName, new Listener<Boolean>() {
                @Override
                public void onComplete(Boolean data) {
                    if(data){
                        swipeRefresh.setRefreshing(false);
                    }
                }
            });
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController nav = NavHostFragment.findNavController(postListFragment.this);
                if (ModelAuth.instance.areUserLoggedIn()) {
                    postListFragmentDirections.ActionPostListFragmentToNewPostFragment action = postListFragmentDirections.actionPostListFragmentToNewPostFragment(categoryName);
                    nav.navigate(action);
                } else {
                    Toast.makeText(getActivity(), "You Must Login Before Posting New Post", Toast.LENGTH_SHORT).show();
                    nav.navigate(R.id.action_postListFragment_to_nav_login);
                }
            }
        });
        return root;
    }

    @Override
    public void onItemClick(int position) {
        Log.d("TAG", "row was clicked" + position);
        NavController nav = NavHostFragment.findNavController(this);
        postListFragmentDirections.ActionPostListFragmentToPostDetailsFragment action = postListFragmentDirections.actionPostListFragmentToPostDetailsFragment(postsList.get(position), false);
        nav.navigate(action);
    }
}

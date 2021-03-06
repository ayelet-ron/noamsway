package com.example.noamsway.ui.postList;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.noamsway.R;
import com.example.noamsway.model.Post;
import com.example.noamsway.utils.RecyclerViewClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PostLists extends Fragment implements RecyclerViewClickListener {
    NavController nav;
    List<Post> postsList;
    LiveData<List<Post>> liveDataPosts;
    RecyclerView recyclerView;
    PostAdapter adapter;
    FloatingActionButton fab;
    PostListViewModel postListViewModel;
    String categoryName;
    View root;
    SwipeRefreshLayout swipeRefresh;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.post_list_fragment, container, false);
        swipeRefresh = root.findViewById(R.id.posts_list_swipe_refresh);
        fab = root.findViewById(R.id.fab);
        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        this.adapter = new PostAdapter(postsList, this);
        recyclerView.setAdapter(this.adapter);

        return this.root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onItemClick(int position) {

    }
}

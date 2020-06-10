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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noamsway.MainActivity;
import com.example.noamsway.R;
import com.example.noamsway.model.ModelAuth;
import com.example.noamsway.model.Post;
import com.example.noamsway.utils.RecyclerViewClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PostLists extends Fragment implements RecyclerViewClickListener {
    NavController nav;
    ArrayList<Post> postsList;
    LiveData<ArrayList<Post>> liveDataPosts;
    RecyclerView recyclerView;
    PostAdapter adapter;
    FloatingActionButton fab;
    PostListViewModel postListViewModel;
    String categoryName;
    View root;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.post_list_fragment, container, false);
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

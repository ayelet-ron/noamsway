package com.example.noamsway.ui.postList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.noamsway.R;


public class postListFragment extends Fragment {

    private PostListViewModel postListViewModel;

    //public static postListFragment newInstance() {
        //return new postListFragment();
    //}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        postListViewModel = ViewModelProviders.of(this).get(PostListViewModel.class);
        return inflater.inflate(R.layout.post_list_fragment, container, false);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        //mViewModel = ViewModelProviders.of(this).get(PostListViewModel.class);
//        // TODO: Use the ViewModel
//    }

}

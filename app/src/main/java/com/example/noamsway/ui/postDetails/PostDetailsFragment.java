package com.example.noamsway.ui.postDetails;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.noamsway.MainActivity;
import com.example.noamsway.R;
import com.example.noamsway.model.Model;
import com.example.noamsway.model.Post;

public class PostDetailsFragment extends Fragment {
    private Post post;
    private TextView postName;
    private ImageView categoryImage;
    private ImageView categoryIcon;
    private TextView postDescription;
    private TextView postCategoryName;
    private TextView authorName;
    private PostDetailsViewModel mViewModel;

    public static PostDetailsFragment newInstance() {
        return new PostDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.post_details_fragment, container, false);
        post = PostDetailsFragmentArgs.fromBundle(getArguments()).getPost();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(post.title);
        postName = root.findViewById(R.id.post_name);
        postDescription = root.findViewById(R.id.post_description);
        postCategoryName = root.findViewById(R.id.category_name);
        categoryImage = root.findViewById(R.id.post_image);
        categoryIcon = root.findViewById(R.id.category_icon);
        authorName = root.findViewById(R.id.author_name);
        authorName.setText(post.authorName);
        postName.setText(post.title);
        postDescription.setText(post.description);
        postCategoryName.setText(post.category.name);
        categoryImage.setImageResource(post.image);
        categoryIcon.setImageResource(post.category.icon);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PostDetailsViewModel.class);
        // TODO: Use the ViewModel
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        if(Model.instance.getUserFullName().equals(post.authorName)){
            menuInflater.inflate(R.menu.edit_delete_menu, menu);
            super.onCreateOptionsMenu(menu, menuInflater);
        }
    }

}

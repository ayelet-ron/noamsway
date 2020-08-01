package com.example.noamsway.ui.postDetails;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.noamsway.MainActivity;
import com.example.noamsway.R;
import com.example.noamsway.model.Category;
import com.example.noamsway.model.CategoryModel;
import com.example.noamsway.model.Model;
import com.example.noamsway.model.ModelAuth;
import com.example.noamsway.model.Post;
import com.example.noamsway.model.PostModel;
import com.example.noamsway.utils.DataCallback;
import com.example.noamsway.utils.Listener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostDetailsFragment extends Fragment {
    private Post post;
    private Category editCategory;
    private TextView authorName,authorNameTitle;
    private ImageView postImage, categoryIcon;
    private Spinner dropdown;
    private final int PICK_IMAGE_REQUEST = 71;
    private EditText postName, postDescription;
    private PostDetailsViewModel mViewModel;
    private Uri filePath;
    boolean isPickedImage = false;
    private Boolean editSelected,fromMyPosts;
    private String menuCategory;
    private ArrayList<String> categoriesNames = new ArrayList<>();
    private View root;

    public void onAttach(Context context) {
        super.onAttach(context);
        mViewModel = ViewModelProviders.of(this).get(PostDetailsViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.post_details_fragment, container, false);
        editSelected=false;
        post = PostDetailsFragmentArgs.fromBundle(getArguments()).getPost();
        menuCategory = post.category.name;
        editCategory = post.category;
        fromMyPosts = PostDetailsFragmentArgs.fromBundle(getArguments()).getMyPosts();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(post.title);
        postName = root.findViewById(R.id.post_name);
        postDescription = root.findViewById(R.id.post_description);
        disableEdit();
        dropdown = root.findViewById(R.id.spinner_category_name);
        categoriesNames = CategoryModel.getInstance().getCategoriesNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, categoriesNames);
        dropdown.setAdapter(adapter);
        dropdown.setEnabled(false);

        int index = categoriesNames.indexOf(post.category.name);
        dropdown.setSelection(index);
        postImage = root.findViewById(R.id.post_image);
        categoryIcon = root.findViewById(R.id.category_icon);
        authorNameTitle = root.findViewById(R.id.author_name_title);
        authorName = root.findViewById(R.id.author_name);
        authorName.setText(post.user.fullName);
        postName.setText(post.title);
        postDescription.setText(post.description);
        Picasso.get().load(post.image).placeholder(R.drawable.place_holder).into(postImage);
        Picasso.get().load(post.category.icon).placeholder(R.drawable.place_holder).into(categoryIcon);
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PostDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        if (ModelAuth.instance.areUserLoggedIn() && ModelAuth.instance.getUserFullName().equals(post.user.fullName)) {
            menuInflater.inflate(R.menu.main, menu);
            menu.getItem(0).setVisible(true);
            menu.getItem(1).setVisible(true);
            super.onCreateOptionsMenu(menu, menuInflater);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_button:
                PostModel.instance.deletePost(post.postId, new Listener<Boolean>() {
                    @Override
                    public void onComplete(Boolean data) {
                        if (data) {
                            Toast.makeText(getActivity(), "Post was Deleted", Toast.LENGTH_SHORT).show();
                            moveBack();
                        } else {
                            Toast.makeText(getActivity(), "Something went wrong please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                return true;
            case R.id.edit_button:
                if(editSelected){
                    post.setCategory(editCategory);
                    post.setDescription(postDescription.getText().toString());
                    post.setTitle(postName.getText().toString());
                    if(isPickedImage){
                        mViewModel.uploadImage(filePath, new DataCallback() {
                            @Override
                            public void onData(String string) {
                                post.setImage(string);
                                PostModel.instance.updatePost(post, new Listener<Boolean>() {
                                    @Override
                                    public void onComplete(Boolean data) {
                                        if(data){
                                            Toast.makeText(getActivity(), "Your Post Was Update Successfully", Toast.LENGTH_SHORT).show();
                                            moveBack();
                                            isPickedImage = false;
                                        }
                                        else{
                                            Toast.makeText(getActivity(), "Something went wrong please try again", Toast.LENGTH_SHORT).show();
                                            moveBack();
                                        }
                                    }
                                });
                            }
                        });
                    }
                    else {
                        PostModel.instance.updatePost(post, new Listener<Boolean>() {
                            @Override
                            public void onComplete(Boolean data) {
                                if(data){
                                    Toast.makeText(getActivity(), "Your Post Was Update Successfully", Toast.LENGTH_SHORT).show();
                                    moveBack();
                                    isPickedImage = false;
                                }
                                else{
                                    Toast.makeText(getActivity(), "Something went wrong please try again", Toast.LENGTH_SHORT).show();
                                    moveBack();
                                }
                            }
                        });
                    }
                }
                else{
                    editSelected=true;
                    Toast.makeText(getActivity(), "Edit Post", Toast.LENGTH_SHORT).show();
                    item.setIcon(R.drawable.save_icon);
                    postImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chooseImage();
                        }
                    });
                    enableEdit();
                    authorNameTitle.setVisibility(View.INVISIBLE);
                    authorName.setVisibility(View.INVISIBLE);
                    dropdown.setEnabled(true);
                    dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            editCategory = CategoryModel.instance.getCategoryByName(categoriesNames.get(position));
                            Picasso.get().load(editCategory.icon).placeholder(R.drawable.place_holder).into(categoryIcon);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            editCategory = post.category;
                        }
                    });
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void disableEdit() {
        postName.setFocusable(false);
        postDescription.setFocusable(false);
        postName.setCursorVisible(false);
        postDescription.setCursorVisible(false);
    }
    private void enableEdit() {
        postName.setFocusableInTouchMode(true);
        postDescription.setFocusableInTouchMode(true);
        postName.setCursorVisible(true);
        postDescription.setCursorVisible(true);
    }

    private void moveBack(){
        NavController nav = NavHostFragment.findNavController(PostDetailsFragment.this);
        if(fromMyPosts){
            PostDetailsFragmentDirections.ActionPostDetailsFragmentToNavMyPosts action =PostDetailsFragmentDirections.actionPostDetailsFragmentToNavMyPosts();
            nav.navigate(action);
        }
        else{
            PostDetailsFragmentDirections.ActionPostDetailsFragmentToPostListFragment action = PostDetailsFragmentDirections.actionPostDetailsFragmentToPostListFragment(menuCategory);
            nav.navigate(action);
        }

    }
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        isPickedImage = true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // TODO: Fix when not picking picture
        if(requestCode == PICK_IMAGE_REQUEST
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            Picasso.get().load(filePath).into(postImage);
            isPickedImage = true;

        }
    }

}

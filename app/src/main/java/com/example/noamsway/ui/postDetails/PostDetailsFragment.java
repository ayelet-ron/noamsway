package com.example.noamsway.ui.postDetails;

import android.content.Context;
import android.graphics.Color;
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
import com.example.noamsway.utils.Listener;

import java.util.ArrayList;

public class PostDetailsFragment extends Fragment {
    private Post post;
    private Category editCategory;
    private TextView authorName,authorNameTitle;
    private ImageView postImage, categoryIcon;
    private Spinner dropdown;
    private EditText postName, postDescription;
    private PostDetailsViewModel mViewModel;
    private Boolean editSelected;
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
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(post.title);
        postName = root.findViewById(R.id.post_name);
        postDescription = root.findViewById(R.id.post_description);
        disableEdit();
        dropdown = root.findViewById(R.id.spinner_category_name);
        categoriesNames = CategoryModel.getInstance().getCategoriesNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, categoriesNames);
        dropdown.setAdapter(adapter);
        dropdown.setEnabled(false);
//        dropdown.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        int index = categoriesNames.indexOf(post.category.name);
        dropdown.setSelection(index);
        postImage = root.findViewById(R.id.post_image);
        categoryIcon = root.findViewById(R.id.category_icon);
        authorNameTitle = root.findViewById(R.id.author_name_title);
        authorName = root.findViewById(R.id.author_name);
        authorName.setText(post.user.fullName);
        postName.setText(post.title);
        postDescription.setText(post.description);
        postImage.setImageResource(post.image);
        categoryIcon.setImageResource(post.category.icon);
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
        if (ModelAuth.instance.getUserFullName().equals(post.user.fullName)) {
            super.onCreateOptionsMenu(menu, menuInflater);
            menuInflater.inflate(R.menu.edit_delete_menu, menu);
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
                            NavController nav = NavHostFragment.findNavController(PostDetailsFragment.this);
                            PostDetailsFragmentDirections.ActionPostDetailsFragmentToPostListFragment action = PostDetailsFragmentDirections.actionPostDetailsFragmentToPostListFragment(post.category.name);
                            nav.navigate(action);
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
                    post.setImage(R.drawable.flowers);
                    PostModel.instance.updatePost(post, new Listener<Boolean>() {
                        @Override
                        public void onComplete(Boolean data) {
                            if(data){
                                Toast.makeText(getActivity(), "Your Post Was Update Successfully", Toast.LENGTH_SHORT).show();
                                //PostDetailsFragmentDirections.ActionPostDetailsFragmentToNavMyPosts=PostDetailsFragmentDirections.actionPostDetailsFragmentToNavMyPosts();

                            }
                            else{
                                Toast.makeText(getActivity(), "Something went wrong please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    editSelected=true;
                    Toast.makeText(getActivity(), "Edit Post", Toast.LENGTH_SHORT).show();
                    item.setIcon(R.drawable.save_icon);
                    enableEdit();
                    authorNameTitle.setVisibility(View.INVISIBLE);
                    authorName.setVisibility(View.INVISIBLE);
                    dropdown.setEnabled(true);
                    dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            editCategory = CategoryModel.instance.getCategoryByName(categoriesNames.get(position));
                            categoryIcon.setImageResource(editCategory.icon);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            editCategory = post.category;
                        }
                    });
                }
                return true;
        }
        return true;
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

//    @Override
//    public void onDestroyView() {
//       super.onDestroyView();
//        ((MainActivity) getActivity()).getSupportActionBar().setTitle(post.category.name);
//    }

}

package com.example.noamsway.ui.newPost;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.noamsway.MainActivity;
import com.example.noamsway.R;
import com.example.noamsway.model.Category;
import com.example.noamsway.model.CategoryModel;
import com.example.noamsway.model.ModelAuth;
import com.example.noamsway.model.Post;
import com.example.noamsway.model.PostModel;
import com.example.noamsway.model.User;
import com.example.noamsway.utils.DataCallback;
import com.example.noamsway.utils.Listener;
import com.squareup.picasso.Picasso;
//import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class NewPostFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private Category category;
    private String categoryName;
    private EditText description,postName;
    private String image;
    private Uri filePath;
    private ImageView uploadImage;
    private NewPostViewModel mViewModel;
    private ArrayList<String> categoriesNames = new ArrayList<>();
    private LiveData<ArrayList<String>> categoriesLiveData;
    private ProgressBar progressBar;
    private final int PICK_IMAGE_REQUEST = 71;
    private String categoryDefaultName;
    private AppCompatSpinner dropdown;
    private View root;
    private Button save;


    public void onAttach(Context context) {
        super.onAttach(context);
        mViewModel = ViewModelProviders.of(this).get(NewPostViewModel.class);
//        categoriesLiveData= mViewModel.getData();
//        categoriesLiveData.observe(this, new Observer<ArrayList<String>>() {
//            @Override
//            public void onChanged(ArrayList<String> categories) {
//                categoriesNames = categories;
//
//            }
//        });

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.new_post_fragment, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Add New Post");
        return root;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        categoriesNames = CategoryModel.getInstance().getCategoriesNames();
        dropdown = root.findViewById(R.id.enter_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, categoriesNames);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
        progressBar = root.findViewById(R.id.add_post_progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        categoryDefaultName = NewPostFragmentArgs.fromBundle(getArguments()).getCategory();
        categoryName = categoryDefaultName;
        int index = categoriesNames.indexOf(categoryDefaultName);
        dropdown.setSelection(index);
        description = root.findViewById(R.id.enter_description);
        postName = root.findViewById(R.id.enter_post_name);
        uploadImage = root.findViewById(R.id.upload_image);
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        save = root.findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkForm()) {
                    progressBar.setVisibility(View.VISIBLE);
                    User user = ModelAuth.instance.getCurrentUser();
                    Category category = CategoryModel.instance.getCategoryByName(categoryName);
                    Post post = new Post(postName.getText().toString(), description.getText().toString(), user, category);
                    Uri path = Uri.parse("android.resource://com.colman.noamsway/" + R.drawable.place_holder);
                    if(filePath == null) {
                        filePath = path;
                    }
                    Date d = new Date();
                    mViewModel.uploadImage(filePath, new DataCallback() {
                        @Override
                        public void onData(String string) {
                            post.setImage(string);
                            PostModel.instance.addPost(post, new Listener<Boolean>() {
                                @Override
                                public void onComplete(Boolean data) {
                                    if (data) {
                                        Toast.makeText(getActivity(), "Your post was added successfully", Toast.LENGTH_SHORT).show();
                                        NavController nav = NavHostFragment.findNavController(NewPostFragment.this);
                                        NewPostFragmentDirections.ActionNewPostFragmentToPostListFragment action = NewPostFragmentDirections.actionNewPostFragmentToPostListFragment(categoryDefaultName);
                                        nav.navigate(action);
                                    } else {
                                        Toast.makeText(getActivity(), "Something went wrong please try again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });

                } else {
                    Toast.makeText(getActivity(), "Not all fields were filled", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.categoryName = categoriesNames.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public boolean checkForm() {
        if (TextUtils.isEmpty(description.getText()) || TextUtils.isEmpty(postName.getText())) {
            return false;
        }
        return true;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // TODO: Fix when not picking picture
        if(requestCode == PICK_IMAGE_REQUEST
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            Picasso.get().load(filePath).into(uploadImage);
        }
    }
}

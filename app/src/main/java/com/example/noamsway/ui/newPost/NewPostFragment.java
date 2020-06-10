package com.example.noamsway.ui.newPost;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.noamsway.R;
import com.example.noamsway.model.Category;
import com.example.noamsway.model.CategoryModel;
import com.example.noamsway.model.ModelAuth;
import com.example.noamsway.model.Post;
import com.example.noamsway.model.PostModel;
import com.example.noamsway.model.User;
import com.example.noamsway.ui.categories.CategoriesViewModel;
import com.example.noamsway.ui.postList.postListFragment;
import com.example.noamsway.utils.Listener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewPostFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    private Category category;
    private String categoryName;
    private EditText description;
    private EditText postName;
    private String image;
    private NewPostViewModel mViewModel;
    private ArrayList<String> categoriesNames = new ArrayList<>();
    private LiveData<ArrayList<String>> categoriesLiveData;
    private ProgressBar progressBar;
    private String categoryDefaultName;
    private AppCompatSpinner dropdown;

//    public static NewPostFragment newInstance() {
//        return new NewPostFragment();
//    }

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
        View root = inflater.inflate(R.layout.new_post_fragment, container, false);
        dropdown = root.findViewById(R.id.enter_category);
        categoriesNames = CategoryModel.getInstance().getCategoriesNames();
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
        postName =root.findViewById(R.id.enter_post_name);
        Button save = root.findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkForm()){
                    progressBar.setVisibility(View.VISIBLE);
                    User user = ModelAuth.instance.getCurrentUser();
                    Category category = CategoryModel.instance.getCategoryByName(categoryName);
                    Post post = new Post(R.drawable.picture1,postName.getText().toString(),description.getText().toString(),user,category);
                    PostModel.instance.addPost(post, new Listener<Boolean>() {
                        @Override
                        public void onComplete(Boolean data) {
                            if(data){
                                Toast.makeText(getActivity(),"Your post was added successfully",Toast.LENGTH_SHORT).show();
                                NavController nav = NavHostFragment.findNavController(NewPostFragment.this);
                                NewPostFragmentDirections.ActionNewPostFragmentToPostListFragment action = NewPostFragmentDirections.actionNewPostFragmentToPostListFragment(categoryDefaultName);
                                nav.navigate(action);
                            }
                            else{
                                Toast.makeText(getActivity(),"Something went wrong please try again",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getActivity(),"Not all fields were filled",Toast.LENGTH_SHORT).show();
                }

            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewPostViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.categoryName = categoriesNames.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public boolean checkForm(){
        if (TextUtils.isEmpty(description.getText()) || TextUtils.isEmpty(postName.getText()))
        {
            return false;
        }
        return true;
    }
}

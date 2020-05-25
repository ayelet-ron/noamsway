package com.example.noamsway.ui.categories;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noamsway.R;
import com.example.noamsway.model.Category;
import com.example.noamsway.model.Model;
import com.example.noamsway.ui.auth.SignUpFragment;
import com.example.noamsway.utils.RecyclerViewClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment implements RecyclerViewClickListener {
    ArrayList<Category> data;
    private RecyclerView recyclerView;
    private CategorieAdapter adapter;
    private CategoriesViewModel categoriesViewModel;

    public CategoriesFragment() {
        data = Model.instance.getAllCategories();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoriesViewModel =
                ViewModelProviders.of(this).get(CategoriesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_categories, container, false);
        recyclerView = root.findViewById(R.id.categories_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        this.adapter = new CategorieAdapter(data,this);
        recyclerView.setAdapter(this.adapter);
//        adapter..(new OnItemClickListener() {
//            @Override
//            public void onClick(int position) {
//                Fragment fragment = new SignUpFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                fragmentTransaction.replace(R.id.loginFragment, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//                Log.d("TAG","row was clicked" + position);
//                Category category = data.get(position);
//                //NavController navCtrl = Navigation.findNavController(list);
//                //navCtrl.navigate(R.id.action_nav_category_to_postListFragment);
//                NavDirections direction = CategoriesFragmentDirections.actionNavCategoryToPostListFragment();
//                Navigation.findNavController(list).navigate(direction);
//                //NavGraphDirections direction = StudentsListFragmentDirections.actionGlobalStudentDetailsFragment(student);
//            }
//        });
        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    @Override
    public void onItemClick(int position) {
        //Toast.makeText(getActivity() , data.get(position).name, Toast.LENGTH_SHORT).show();
//        Fragment fragment = new SignUpFragment();
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//        fragmentTransaction.replace(R.id.loginFragment, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
        Log.d("TAG","row was clicked" + position);
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_nav_category_to_postListFragment);
//        NavDirections direction = CategoriesFragmentDirections.actionNavCategoryToPostListFragment();
//        Navigation.findNavController(recyclerView).navigate(direction);
    }
}

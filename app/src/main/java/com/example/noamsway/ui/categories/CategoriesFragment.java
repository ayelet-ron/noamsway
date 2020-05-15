package com.example.noamsway.ui.categories;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noamsway.R;
import com.example.noamsway.model.Category;
import com.example.noamsway.model.Model;

import java.util.List;

public class CategoriesFragment extends Fragment {
    RecyclerView list;
    List<Category> data;
    private CategoriesViewModel categoriesViewModel;

//    interface Delegate{
//        void onItemSelected(Category category);
//    }
//    Delegate parent;
    public CategoriesFragment() {
    data = Model.instance.getAllCategories();
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoriesViewModel =
                ViewModelProviders.of(this).get(CategoriesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_categories, container, false);
        list = root.findViewById(R.id.categories_list);
        list.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(layoutManager);


        CategoryListAdapter adapter = new CategoryListAdapter();
        list.setAdapter(adapter);

        adapter.setOnIntemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Log.d("TAG","row was clicked" + position);
                Category category = data.get(position);
                //NavGraphDirections direction = StudentsListFragmentDirections.actionGlobalStudentDetailsFragment(student);
            }
        });
        return root;
    }
    static class CategoryRowViewHolder extends RecyclerView.ViewHolder{
        TextView categoryName;
        ImageView categoryImage;
        Category category;

        public CategoryRowViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            this.categoryImage = itemView.findViewById(R.id.category_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onClick(position);
                        }
                    }
                }
            });

        }

        public void bind(Category category) {

            this.categoryImage.setImageResource(category.img);
            this.category = category;
        }
    }

    interface OnItemClickListener{
        void onClick(int position);
    }

    class CategoryListAdapter extends RecyclerView.Adapter<CategoryRowViewHolder>{
        private OnItemClickListener listener;

        void setOnIntemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }


        @NonNull
        @Override
        public CategoryRowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.category_list_row, viewGroup,false );
            CategoryRowViewHolder vh = new CategoryRowViewHolder(v, listener);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull CategoryRowViewHolder categoryRowViewHolder, int i) {
            Category category = data.get(i);
            categoryRowViewHolder.bind(category);

        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}

package com.example.noamsway.ui.categories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noamsway.R;
import com.example.noamsway.model.Category;
import com.example.noamsway.utils.RecyclerViewClickListener;

import java.util.ArrayList;

public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.CategoryRowViewHolder> {

    private ArrayList<Category> dataList;
    private RecyclerViewClickListener recyclerViewClickListener;

    public CategorieAdapter(ArrayList<Category> dataList) {
        this.dataList = dataList;
    }

    public CategorieAdapter(ArrayList<Category> dataList, RecyclerViewClickListener recyclerViewClickListener) {
        this.dataList = dataList;
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @NonNull
    @Override
    public CategoryRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category_list_row, parent, false);
        return new CategoryRowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRowViewHolder holder, int position) {
        holder.bind(this.dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.dataList.size();
    }

    class CategoryRowViewHolder extends RecyclerView.ViewHolder {
        private int mCurrentPosition;
        TextView categoryName;
        ImageView categoryImage;
        Category category;

        public CategoryRowViewHolder(@NonNull View itemView) {
            super(itemView);
            this.categoryImage = itemView.findViewById(R.id.category_image);
//            categoryImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    recyclerViewClickListener.onItemClick(getAdapterPosition());
//                }
//            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClickListener.onItemClick(getAdapterPosition());
                }
            });
        }

        public void bind(Category category) {
            this.categoryImage.setImageResource(category.img);
            this.category = category;
        }
    }
}

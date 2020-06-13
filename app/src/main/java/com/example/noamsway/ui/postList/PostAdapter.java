package com.example.noamsway.ui.postList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noamsway.R;
import com.example.noamsway.model.Post;
import com.example.noamsway.utils.RecyclerViewClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostRowViewHolder> {
    private ArrayList<Post> categoryPosts;
    private RecyclerViewClickListener recyclerViewClickListener;

    public PostAdapter(ArrayList<Post> dataList, RecyclerViewClickListener recyclerViewClickListener) {
        this.categoryPosts = dataList;
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @NonNull
    @Override
    public PostRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.row_post, parent, false);
        return new PostRowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostRowViewHolder holder, int position) {
        holder.bind(this.categoryPosts.get(position));
    }

    @Override
    public int getItemCount() {
        if(categoryPosts!=null){
            return this.categoryPosts.size();
        }
        else{
            return 0;
        }
    }
    public void setPosts(ArrayList<Post> dataList){
        categoryPosts = dataList;
        notifyDataSetChanged();
    }

    class PostRowViewHolder extends RecyclerView.ViewHolder {
        private int mCurrentPosition;
        CardView cardView;
        TextView postTitle;
        TextView postAuthor;
        ImageView postImage;
        Post post;

        public PostRowViewHolder(@NonNull View itemView) {
            super(itemView);
            this.postTitle = itemView.findViewById(R.id.txt_post_description);
            this.postAuthor = itemView.findViewById(R.id.txt_post_title);
            this.cardView = itemView.findViewById(R.id.cardview_item);
            this.postImage = itemView.findViewById(R.id.img_post);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClickListener.onItemClick(getAdapterPosition());
                }
            });

        }

        public void bind(Post post) {
            Picasso.get().load(post.image).placeholder(R.drawable.place_holder).into(postImage);
            this.postTitle.setText(post.title);
            this.postAuthor.setText(post.user.fullName);
            this.post = post;
        }
    }
}

package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.instagram.fragments.ProfileFragment;
import com.parse.ParseFile;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.Date;
import java.util.List;

public class ProfilePostsAdapter extends RecyclerView.Adapter<ProfilePostsAdapter.ViewHolder>{

    private Context context;
    private List<Post> posts;

    public ProfilePostsAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ProfilePostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_post, parent, false);
        return new ProfilePostsAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProfilePostsAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                Post currentPost = posts.get(position);
                Intent i = new Intent(context, DetailsActivity.class);
                i.putExtra(Post.class.getSimpleName(), Parcels.wrap(currentPost));
                context.startActivity(i);
            }
        }

        public void bind(Post post) {
            // Bind the post data to the view elements
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }
        }

    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }


}

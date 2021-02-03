package com.bca.bsi.ui.basenavigation.information.forum.comment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CommentImageAdapter extends RecyclerView.Adapter<CommentImageAdapter.Holder> {

    private List<Forum.Post.ImagePost> imageList;
    private CommentImageAdapter.onImageClick onImageClick;

    public CommentImageAdapter() {
        this.imageList = new ArrayList<>();
    }

    public interface onImageClick {
        void onImageClickWith(String URl);
    }

    public void setOnImageClick(CommentImageAdapter.onImageClick onImageClick) {
        this.onImageClick = onImageClick;
    }

    public void setImageList(List<Forum.Post.ImagePost> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_comment_image, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Forum.Post.ImagePost imagePost = imageList.get(position);
        if (null != imagePost) {
            Picasso.get()
                    .load(Utils.imageURL(imagePost.getImageURL()))
                    .into(holder.img);

            holder.img.setOnClickListener(v -> onImageClick.onImageClickWith(imagePost.getImageURL()));
        }
    }

    @Override
    public int getItemCount() {
        return this.imageList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private ImageView img;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.recycler_comment_image);
        }
    }

}

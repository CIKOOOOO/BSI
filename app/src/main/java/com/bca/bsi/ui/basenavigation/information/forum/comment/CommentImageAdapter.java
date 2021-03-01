package com.bca.bsi.ui.basenavigation.information.forum.comment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * The purpose is to show list image inside of detail post
 * When image is clicked, screen will show dialog with full screen of image
 */
public class CommentImageAdapter extends RecyclerView.Adapter<CommentImageAdapter.Holder> {

    private List<String> imageList;
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

    public void setImageList(List<String> imageList) {
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
        if (!imageList.get(position).isEmpty()) {
            Picasso.get()
                    .load(Utils.imageURL(imageList.get(position)))
                    .into(holder.img);

            holder.img.setOnClickListener(v -> onImageClick.onImageClickWith(imageList.get(position)));
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

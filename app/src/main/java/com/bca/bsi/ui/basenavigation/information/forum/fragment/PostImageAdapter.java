package com.bca.bsi.ui.basenavigation.information.forum.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PostImageAdapter extends RecyclerView.Adapter<PostImageAdapter.Holder> {
    private List<Forum.Post.ImagePost> imageList;
    private onImageClick onImageClick;

    public PostImageAdapter() {
        this.imageList = new ArrayList<>();
    }

    public interface onImageClick {
        void onImageClickWith(String URl);
    }

    public void setOnImageClick(PostImageAdapter.onImageClick onImageClick) {
        this.onImageClick = onImageClick;
    }

    public void setImageList(List<Forum.Post.ImagePost> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout;
        if (viewType == 1) {
            layout = R.layout.recycler_single_image_post;
        } else if (viewType == 2) {
            layout = R.layout.recycler_double_image_post;
        } else if (viewType == 4) {
            layout = R.layout.recycler_fourth_image_post;
        } else {
            layout = R.layout.recycler_triple_image_post;
        }
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Forum.Post.ImagePost imagePost = imageList.get(position);
        if (getItemViewType(position) == 1) {
            Picasso.get()
                    .load(imagePost.getImageURL())
                    .into(holder.img1);
        } else if (getItemViewType(position) == 2) {
            Picasso.get()
                    .load(imagePost.getImageURL())
                    .into(holder.img2);
        } else if (getItemViewType(position) == 4) {
            Picasso.get()
                    .load(imagePost.getImageURL())
                    .into(holder.img4);
        } else {
            Picasso.get()
                    .load(imagePost.getImageURL())
                    .into(holder.img3);
            if (imageList.size() - 1 > position && position == 2) {
                int amount = imageList.size() - 3;
                holder.frameLayout.setVisibility(View.VISIBLE);
                holder.tv.setText("+" + amount);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImageClick.onImageClickWith(imagePost.getImageURL());
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (imageList.size() == 1) {
            return 1;
        } else if (imageList.size() == 2) {
            return 2;
        } else {
            if (position == 0 || position == 1) {
                return 4;
            } else {
                return 3;
            }
        }
    }

    @Override
    public int getItemCount() {
        return Math.min(imageList.size(), 3);
    }

    static class Holder extends RecyclerView.ViewHolder {
        private ImageView img1, img2, img3, img4;
        private FrameLayout frameLayout;
        private TextView tv;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.recycler_img_single_post);
            img2 = itemView.findViewById(R.id.recycler_img_double_post);
            img3 = itemView.findViewById(R.id.recycler_img_triple_post);
            img4 = itemView.findViewById(R.id.recycler_img_fourth_post);
            frameLayout = itemView.findViewById(R.id.recycler_frame_triple_post);
            tv = itemView.findViewById(R.id.recycler_tv_amount_img_triple_post);
        }
    }

}

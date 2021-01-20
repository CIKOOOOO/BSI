package com.bca.bsi.ui.basenavigation.information.forum.post;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PostImageAdapter extends RecyclerView.Adapter<PostImageAdapter.Holder> {

    public static final int CHOOSE_IMAGE = 1;
    public static final int IMAGE_LIST = 2;
    public static final int NEWS = 3;

    private int type;
    private Context mContext;

    private List<Bitmap> bitmapList;
    private onItemClick onItemClick;

    public interface onItemClick {
        void onOpenGallery();

        void onRemoveImageAt(int pos);
    }

    public PostImageAdapter(int type, PostImageAdapter.onItemClick onItemClick) {
        this.type = type;
        this.onItemClick = onItemClick;
        bitmapList = new ArrayList<>();
    }

    public void setBitmapList(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
        if (this.bitmapList.size() > 0) {
            this.type = IMAGE_LIST;
        } else {
            this.type = CHOOSE_IMAGE;
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout;
        if (viewType == CHOOSE_IMAGE)
            layout = R.layout.recycler_add_image;
        else if (viewType == IMAGE_LIST)
            layout = R.layout.recycler_chosen_image;
        else
            layout = R.layout.recycler_add_image;

        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        mContext = parent.getContext();
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if (getItemViewType(position) == CHOOSE_IMAGE) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onOpenGallery();
                }
            });
        } else if (getItemViewType(position) == IMAGE_LIST) {
            if (bitmapList.size() != 5 && bitmapList.size() == position) {
                Log.e("asd","aaa"+position);
                holder.imgClose.setVisibility(View.GONE);
                Glide.with(mContext)
                        .load(R.drawable.ic_baseline_add_welma)
                        .into(holder.imageView);
//                holder.imageView.setImageResource(R.drawable.ic_baseline_add_24);
//                holder.imageView.setBackground(mContext.getDrawable(R.drawable.rectangle_rounded_welma));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClick.onOpenGallery();
                    }
                });
            } else {
                holder.imgClose.setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .load(bitmapList.get(position))
                        .into(holder.imageView);
                holder.imgClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClick.onRemoveImageAt(position);
                    }
                });
            }
        } else {

        }
    }

    @Override
    public int getItemCount() {
        if (type == CHOOSE_IMAGE)
            return 1;
        else if (type == IMAGE_LIST)
            return bitmapList.size() == 5 ? bitmapList.size() : bitmapList.size() + 1;
        else
            return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (type == CHOOSE_IMAGE)
            return CHOOSE_IMAGE;
        else if (type == IMAGE_LIST)
            return IMAGE_LIST;
        else
            return NEWS;
    }

    static class Holder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private ImageButton imgClose;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recycler_img_chosen);
            imgClose = itemView.findViewById(R.id.recycler_img_btn_close_img_chosen);
        }
    }

}

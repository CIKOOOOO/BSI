package com.bca.bsi.ui.basenavigation.information.forum.profile;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ChooseImageAdapter extends RecyclerView.Adapter<ChooseImageAdapter.Holder> {

    public static final int SINGLE_IMAGE = 1;
    public static final int GRID_IMAGE = 2;

    private List<Forum.ProfilePicture> imageList;
    private int type, lastPosition;
    private onImageClick onImageClick;

    public interface onImageClick {
        void onItemClick(Forum.ProfilePicture profilePicture);
    }

    public ChooseImageAdapter(onImageClick onImageClick) {
        this.type = -1;
        this.lastPosition = -1;
        this.onImageClick = onImageClick;
        imageList = new ArrayList<>();
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setImageList(List<Forum.ProfilePicture> imageList) {
        this.lastPosition = -1;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = viewType == SINGLE_IMAGE ? R.layout.recycler_single_image : R.layout.recycler_grid_image;
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ImageView imageView = getItemViewType(position) == SINGLE_IMAGE ? holder.imgSingle : holder.imgGrid;

//        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) holder.constraintLayout.getLayoutParams();
//        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        holder.constraintLayout.setLayoutParams(layoutParams);
//        holder.constraintLayout.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        Picasso.get()
                .load(imageList.get(position).getImgURL())
                .into(imageView);

        int visibility = position == lastPosition ? View.VISIBLE : View.GONE;
        holder.frameLayout.setVisibility(visibility);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastPosition != position) {
                    lastPosition = position;
                    notifyDataSetChanged();
                }

                Log.e("asd", lastPosition + "");

                onImageClick.onItemClick(imageList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return type == SINGLE_IMAGE ? SINGLE_IMAGE : GRID_IMAGE;
    }

    static class Holder extends RecyclerView.ViewHolder {
        private ImageView imgSingle, imgGrid;
        private FrameLayout frameLayout;
        private ConstraintLayout constraintLayout;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imgGrid = itemView.findViewById(R.id.recycler_rounded_img_grid);
            imgSingle = itemView.findViewById(R.id.recycler_rounded_img_single);
            frameLayout = itemView.findViewById(R.id.recycler_frame_blur);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }

}

package com.bca.bsi.ui.basenavigation.information.forum.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;

import java.util.ArrayList;
import java.util.List;

public class ChooseImageAdapter extends RecyclerView.Adapter<ChooseImageAdapter.Holder> {

    public static final int SINGLE_IMAGE = 1;
    public static final int GRID_IMAGE = 2;

    private List<String> imageList;
    private int type;
    private onImageClick onImageClick;

    public interface onImageClick {
        void onItemClick(String img);
    }

    public ChooseImageAdapter(onImageClick onImageClick) {
        this.type = -1;
        this.onImageClick = onImageClick;
        imageList = new ArrayList<>();
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setImageList(List<String> imageList) {
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
//        Picasso.get()
//                .load(imageList.get(position))
//                .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onImageClick.onItemClick(imageList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        return type == SINGLE_IMAGE ? SINGLE_IMAGE : GRID_IMAGE;
    }

    static class Holder extends RecyclerView.ViewHolder {
        private ImageView imgSingle, imgGrid;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imgGrid = itemView.findViewById(R.id.recycler_rounded_img_grid);
            imgSingle = itemView.findViewById(R.id.recycler_rounded_img_single);
        }
    }

}

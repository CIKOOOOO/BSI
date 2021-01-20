package com.bca.bsi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Privacy;
import com.bca.bsi.utils.constant.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PrivacyAdapter extends RecyclerView.Adapter<PrivacyAdapter.Holder> {

    private onPrivacyClick onPrivacyClick;
    private List<Privacy> privacyList;
    private int lastPosition;

    public PrivacyAdapter(onPrivacyClick onPrivacyClick) {
        this.onPrivacyClick = onPrivacyClick;
        this.privacyList = Constant.getListPrivacy();
        lastPosition = -1;
    }

    public interface onPrivacyClick {
        void onItemPrivacyClick(Privacy privacy);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_privacy, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Privacy privacy = privacyList.get(position);
        int drawable = privacy.isClick() ? privacy.getImageClick() : privacy.getImage();
        Picasso.get()
                .load(drawable)
                .into(holder.imageView);
        holder.textView.setText(privacy.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastPosition != -1) {
                    privacyList.get(lastPosition).setClick(false);
                }
                lastPosition = position;
                privacyList.get(lastPosition).setClick(true);
                notifyDataSetChanged();
                onPrivacyClick.onItemPrivacyClick(privacy);
            }
        });
    }

    @Override
    public int getItemCount() {
        return privacyList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recycler_img_privacy);
            textView = itemView.findViewById(R.id.recycler_tv_type_privacy);
        }
    }
}

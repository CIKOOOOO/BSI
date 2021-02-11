package com.bca.bsi.ui.basenavigation.information.forum.profile.connection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.utils.Utils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ConnectionAdapter extends RecyclerView.Adapter<ConnectionAdapter.Holder> {

    private List<Forum.Connection> connectionList;

    private onPeopleClick onPeopleClick;

    public void setConnectionList(List<Forum.Connection> connectionList) {
        this.connectionList = connectionList;
    }

    public ConnectionAdapter(ConnectionAdapter.onPeopleClick onPeopleClick) {
        this.onPeopleClick = onPeopleClick;
        this.connectionList = new ArrayList<>();
    }

    public interface onPeopleClick {
        void onFollow();

        void onUnfollow();

        void onDetailPeople();
    }

    public void clearData() {
        this.connectionList = new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_people, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Forum.Connection connection = connectionList.get(position);
        if (null != connection) {
            holder.tvName.setText(connection.getUsername());
            String type;
            int color;
            int background;
            if (connection.getFollow().equalsIgnoreCase("true")) {
                type = "Unfollow";
                color = R.color.color_base_welma;
                background = R.drawable.rectangle_rounded_stroke_java_5dp;
            } else {
                type = "Follow";
                color = R.color.white_palette;
                background = R.drawable.rectangle_rounded_welma_5dp;
            }
            if (!connection.getImgProfile().isEmpty())
                Picasso.get()
                        .load(Utils.imageURL(connection.getImgProfile()))
                        .into(holder.roundedImageView);
            holder.tvType.setText(type);
            holder.tvType.setTextColor(holder.itemView.getContext().getResources().getColor(color));
            holder.tvType.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), background));
        }
    }

    @Override
    public int getItemCount() {
        return connectionList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private RoundedImageView roundedImageView;
        private TextView tvName, tvType;

        public Holder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.recycler_img_profile_people);
            tvName = itemView.findViewById(R.id.recycler_tv_name_people);
            tvType = itemView.findViewById(R.id.recycler_tv_type_people);
        }
    }
}

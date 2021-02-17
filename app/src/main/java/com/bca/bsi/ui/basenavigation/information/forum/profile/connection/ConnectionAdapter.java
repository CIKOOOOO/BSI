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
import com.bca.bsi.utils.constant.Type;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ConnectionAdapter extends RecyclerView.Adapter<ConnectionAdapter.Holder> {

    private List<Forum.Connection> connectionList;

    private onPeopleClick onPeopleClick;

    private String type, selfProfileID;

    public void setConnectionList(List<Forum.Connection> connectionList) {
        this.connectionList = connectionList;
    }

    public ConnectionAdapter(ConnectionAdapter.onPeopleClick onPeopleClick) {
        this.onPeopleClick = onPeopleClick;
        this.connectionList = new ArrayList<>();
        type = "";
        selfProfileID = "";
    }

    public interface onPeopleClick {
        void onFollow(String profileID);

        void onDetailPeople(String profileID);
    }

    public void changeStatus(Forum.User user) {
        for (int i = 0; i < this.connectionList.size(); i++) {
            Forum.Connection connection = this.connectionList.get(i);
            if (null != type) {
                if (type.equalsIgnoreCase(Type.FOLLOWERS)) {
                    if (null != connection && connection.getProfileID().equalsIgnoreCase(user.getOtherProfileID())) {
                        connection.setFollow(user.getFollowStatus());
                        this.connectionList.set(i, connection);
                        notifyDataSetChanged();
                        break;
                    }
                } else {
                    if (null != connection && connection.getFollowProfileID().equalsIgnoreCase(user.getOtherProfileID())) {
                        connection.setFollow(user.getFollowStatus());
                        this.connectionList.set(i, connection);
                        notifyDataSetChanged();
                        break;
                    }
                }
            }
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSelfProfileID(String selfProfileID) {
        this.selfProfileID = selfProfileID;
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
            String types;
            String profileID = type.equalsIgnoreCase(Type.FOLLOWING) ? connection.getFollowProfileID() : connection.getProfileID();
            int color;
            int background;
            int visibility = profileID.equalsIgnoreCase(selfProfileID) ? View.GONE : View.VISIBLE;
            if (connection.getFollow().equalsIgnoreCase("true")) {
                types = "Unfollow";
                color = R.color.color_base_welma;
                background = R.drawable.rectangle_rounded_stroke_java_5dp;
            } else {
                types = "Follow";
                color = R.color.white_palette;
                background = R.drawable.rectangle_rounded_welma_5dp;
            }
            if (!connection.getImgProfile().isEmpty())
                Picasso.get()
                        .load(Utils.imageURL(connection.getImgProfile()))
                        .into(holder.roundedImageView);
            holder.tvType.setVisibility(visibility);
            holder.tvType.setText(types);
            holder.tvType.setTextColor(holder.itemView.getContext().getResources().getColor(color));
            holder.tvType.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), background));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String profileID = type.equalsIgnoreCase(Type.FOLLOWING) ? connection.getFollowProfileID() : connection.getProfileID();
                    onPeopleClick.onDetailPeople(profileID);
                }
            });
            holder.tvType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String profileID = type.equalsIgnoreCase(Type.FOLLOWING) ? connection.getFollowProfileID() : connection.getProfileID();
                    onPeopleClick.onFollow(profileID);
                }
            });
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

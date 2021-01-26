package com.bca.bsi.ui.basenavigation.information.forum.post.direct;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.User;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DirectShareAdapter extends RecyclerView.Adapter<DirectShareAdapter.Holder> {

    private List<User.ForumUser> forumUserList;
    private onUserClick onUserClick;

    private int type;

    public interface onUserClick {
        void onUserChooseProfile(User.ForumUser forumUser);

        void onDeleteProfile(User.ForumUser forumUser);
    }

    public DirectShareAdapter(DirectShareAdapter.onUserClick onUserClick) {
        this.onUserClick = onUserClick;
        this.forumUserList = new ArrayList<>();
        type = 0;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setForumUserList(List<User.ForumUser> forumUserList) {
        this.forumUserList = forumUserList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = viewType == 0 ? R.layout.recycler_people : R.layout.recycler_chosen_people;
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        User.ForumUser forumUser = forumUserList.get(position);

        holder.tvName.setText(forumUser.getUsername());
        Picasso.get()
                .load(forumUser.getImageProfile())
                .into(holder.rivImage);

        if (getItemViewType(position) == 0) {
            holder.tvFollow.setVisibility(View.GONE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUserClick.onUserChooseProfile(forumUser);
                }
            });
        } else {
            holder.imgRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUserClick.onDeleteProfile(forumUser);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return forumUserList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvName,tvFollow;
        private RoundedImageView rivImage;
        private ImageButton imgRemove;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvFollow = itemView.findViewById(R.id.recycler_tv_type_people);

            tvName = itemView.findViewById(R.id.recycler_tv_name_people);
            rivImage = itemView.findViewById(R.id.recycler_img_profile_people);
            imgRemove = itemView.findViewById(R.id.recycler_img_btn_close_chosen_profile);
        }
    }
}

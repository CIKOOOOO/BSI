package com.bca.bsi.ui.basenavigation.information.forum.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.utils.constant.Type;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ChildMainForumAdapter extends RecyclerView.Adapter<ChildMainForumAdapter.Holder> {

    private static final int TRENDING = 1, STRATEGY = 2, SHARE_TRADE = 3, NEWS = 4, TIMELINE = 5;

    private String type, profileID;
    private List<Forum.Post> forumList;
    private onPostClick onPostClick;
    private Context mContext;

    public interface onPostClick {
        void onDetailPost(String postID);

        void onPostLike(String postID);
    }

    public ChildMainForumAdapter(String type, String profileID) {
        this.profileID = profileID;
        this.type = type.toLowerCase();
        this.forumList = new ArrayList<>();
    }

    public void setForumList(List<Forum.Post> forumList) {
        this.forumList = forumList;
    }

    public void setOnPostClick(ChildMainForumAdapter.onPostClick onPostClick) {
        this.onPostClick = onPostClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_child_main_forum, parent, false);
        mContext = parent.getContext();
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Forum.Post post = forumList.get(position);
        switch (getItemViewType(position)) {
            case ChildMainForumAdapter.TRENDING:
                holder.tvType.setVisibility(View.VISIBLE);
                holder.tvType.setText(post.getType());
                break;
            case ChildMainForumAdapter.STRATEGY:

                break;
            case ChildMainForumAdapter.SHARE_TRADE:
                holder.constraintLayout.setVisibility(View.VISIBLE);
                break;
            case ChildMainForumAdapter.NEWS:

                break;
            case ChildMainForumAdapter.TIMELINE:

                break;
        }

        int drawableLike = post.getStatusLike().equals("true") ? R.drawable.ic_like : R.drawable.ic_no_like;

        Picasso.get()
                .load(post.getImageProfile())
                .into(holder.roundedImageView);

        holder.tvDate.setText(post.getDate());
        holder.tvContent.setText(post.getContent());
        holder.tvLike.setText(post.getLike());
        holder.tvComment.setText(post.getComment());
        holder.tvShare.setText(post.getShare());

        holder.tvLike.setCompoundDrawablesWithIntrinsicBounds(drawableLike, 0, 0, 0);

        if (holder.tvContent.getText().toString().equals(post.getContent())) {
            holder.tvLookMore.setVisibility(View.GONE);
        }

        if (!post.getImageContent().isEmpty()) {
            Picasso.get()
                    .load(post.getImageContent())
                    .into(holder.imgContent);
        }

        String name = profileID.equals(post.getProfileID()) ? "Me" : post.getName();
        holder.tvName.setText(name);

        holder.tvLookMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPostClick.onDetailPost(post.getPostID());
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPostClick.onDetailPost(post.getPostID());
            }
        });

        holder.tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPostClick.onDetailPost(post.getPostID());
            }
        });

        holder.tvLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPostClick.onPostLike(post.getPostID());
            }
        });

        holder.imgBtnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(mContext, holder.imgBtnMore);

                int layout = profileID.equals(post.getProfileID()) ? R.menu.menu_self_post : R.menu.menu_other_post;

                popup.getMenuInflater()
                        .inflate(layout, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });
    }

    @Override
    public int getItemCount() {
        return forumList.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = -1;
        switch (type) {
            case Type.TRENDING:
                viewType = ChildMainForumAdapter.TRENDING;
                break;
            case Type.STRATEGI:
                viewType = ChildMainForumAdapter.STRATEGY;
                break;
            case Type.SHARE_TRADE:
                viewType = ChildMainForumAdapter.SHARE_TRADE;
                break;
            case Type.NEWS:
                viewType = ChildMainForumAdapter.NEWS;
                break;
            case Type.TIMELINE:
                viewType = ChildMainForumAdapter.TIMELINE;
                break;
        }
        return viewType;
    }

    static class Holder extends RecyclerView.ViewHolder {
        private RoundedImageView roundedImageView;
        private TextView tvName, tvDate, tvContent, tvType, tvLike, tvComment, tvShare, tvLookMore;
        private ImageView imgContent;
        private ImageButton imgBtnMore;
        private ConstraintLayout constraintLayout;

        public Holder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.recycler_img_profile_child_main_forum);
            tvDate = itemView.findViewById(R.id.recycler_tv_date_child_main_forum);
            tvName = itemView.findViewById(R.id.recycler_tv_name_child_main_forum);
            tvContent = itemView.findViewById(R.id.recycler_tv_content_child_main_forum);
            tvLookMore = itemView.findViewById(R.id.recycler_tv_view_more_child_main_forum);
            tvLike = itemView.findViewById(R.id.recycler_tv_like_child_main_forum);
            tvComment = itemView.findViewById(R.id.recycler_tv_comment_child_main_forum);
            tvShare = itemView.findViewById(R.id.recycler_tv_share_child_main_forum);
            tvType = itemView.findViewById(R.id.recycler_tv_type_child_main_forum);
            imgContent = itemView.findViewById(R.id.recycler_img_content_child_main_forum);
            constraintLayout = itemView.findViewById(R.id.cl_share_trade);
            imgBtnMore = itemView.findViewById(R.id.recycler_img_btn_more_child_main_forum);
        }
    }
}

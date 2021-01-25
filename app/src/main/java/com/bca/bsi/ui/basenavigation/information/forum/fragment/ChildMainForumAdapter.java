package com.bca.bsi.ui.basenavigation.information.forum.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.viewholder.GeneralHolder;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.viewholder.NewsViewHolder;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.viewholder.ShareTradeViewHolder;
import com.bca.bsi.utils.constant.Type;

import java.util.ArrayList;
import java.util.List;

public class ChildMainForumAdapter extends RecyclerView.Adapter {

    private static final int TRENDING = 1, STRATEGY = 2, SHARE_TRADE = 3, NEWS = 4, TIMELINE = 5;

    private String type, profileID;
    private List<Forum.Post> forumList;
    private OnPostClick onPostClick;

    public interface OnPostClick {
        void onDetailPost(String postID);

        void onPostLike(String postID);

        void onReport(String postID);

        void onSavedPost(String postID);

        void onOtherProfile(String profileID);

        void onMyProfile();

        void onDetailNews(String newsID);
    }

    public ChildMainForumAdapter(String type, String profileID, ChildMainForumAdapter.OnPostClick onPostClick) {
        this.profileID = profileID;
        this.onPostClick = onPostClick;
        this.type = type.toLowerCase();
        this.forumList = new ArrayList<>();
    }

    public void setForumList(List<Forum.Post> forumList) {
        this.forumList = forumList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case ChildMainForumAdapter.STRATEGY:
                layout = R.layout.recycler_child_main_forum;
                break;
            case ChildMainForumAdapter.SHARE_TRADE:
                layout = R.layout.recycler_share_trade_main_forum;
                break;
            case ChildMainForumAdapter.NEWS:
                layout = R.layout.recycler_news_main_forum;
                break;
            case ChildMainForumAdapter.TIMELINE:
                layout = R.layout.recycler_child_main_forum;
                break;
            default:
                layout = R.layout.recycler_child_main_forum;
        }
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        switch (viewType) {
            case ChildMainForumAdapter.STRATEGY:
                viewHolder = new GeneralHolder(v, this.onPostClick);
                break;
            case ChildMainForumAdapter.SHARE_TRADE:
                viewHolder = new ShareTradeViewHolder(v, this.onPostClick);
                break;
            case ChildMainForumAdapter.NEWS:
                viewHolder = new NewsViewHolder(v, this.onPostClick);
                break;
            case ChildMainForumAdapter.TIMELINE:
                viewHolder = new GeneralHolder(v, this.onPostClick);
                break;
            default:
                viewHolder = new GeneralHolder(v, this.onPostClick);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder myHolder, int position) {

        Forum.Post post = forumList.get(position);
        switch (getItemViewType(position)) {
            case ChildMainForumAdapter.STRATEGY:

                break;
            case ChildMainForumAdapter.SHARE_TRADE:
                ShareTradeViewHolder viewHolder = (ShareTradeViewHolder) myHolder;
                viewHolder.loadData(post, profileID);
                break;
            case ChildMainForumAdapter.NEWS:
                NewsViewHolder newsViewHolder = (NewsViewHolder) myHolder;
                newsViewHolder.loadData(post, profileID, type.equals(Type.TRENDING));
                break;
            case ChildMainForumAdapter.TIMELINE:

                break;
        }
    }

    @Override
    public int getItemCount() {
        return forumList.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = -1;

        String currentType = type.equals(Type.TRENDING) ? forumList.get(position).getType() : type;

        switch (currentType) {
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
}

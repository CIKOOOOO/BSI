package com.bca.bsi.ui.basenavigation.information.forum.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Forum;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.viewholder.GeneralHolder;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.viewholder.NewsViewHolder;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.viewholder.NoDataViewHolder;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.viewholder.RepostGeneralHolder;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.viewholder.RepostNewsViewHolder;
import com.bca.bsi.ui.basenavigation.information.forum.fragment.viewholder.ShareTradeViewHolder;
import com.bca.bsi.utils.constant.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose : to process type of view holder, then send the data to chosen view holder
 */
public class ChildMainForumAdapter extends RecyclerView.Adapter {

    private static final int REPOST_NEWS = 1, STRATEGY = 2, SHARE_TRADE = 3, NEWS = 4, REPOST_GENERAL = 5, NO_DATA = 6, LOADING = 7;

    private String type, profileID;
    private boolean isDataExist;
    private List<Forum.Post> forumList;
    private OnPostClick onPostClick;

    public ChildMainForumAdapter(String type, String profileID, OnPostClick onPostClick, boolean isDataExist) {
        this.profileID = profileID;
        this.onPostClick = onPostClick;
        this.type = type.toLowerCase();
        this.isDataExist = isDataExist;
        this.forumList = new ArrayList<>();
    }

    public void setDataExist(boolean dataExist) {
        isDataExist = dataExist;
    }

    public void setForumList(List<Forum.Post> forumList) {
        if (null != forumList)
            this.forumList.addAll(forumList);
    }

    public void setReshareStatus(String reshareStatus, String postID) {
        for (int i = 0; i < this.forumList.size(); i++) {
            Forum.Post post = this.forumList.get(i);
            if (post.getPostID().equalsIgnoreCase(postID)) {
                post.setStatusShare(reshareStatus);
                int share = Integer.parseInt(post.getShare()) + 1;
                post.setShare(String.valueOf(share));
                this.forumList.set(i, post);
                notifyDataSetChanged();
                break;
            }
        }
    }

    public void setSavePost(Forum.SavePost savePost) {
        for (int i = 0; i < this.forumList.size(); i++) {
            Forum.Post post = this.forumList.get(i);
            if (post.getPostID().equalsIgnoreCase(savePost.getPostID())) {
                post.setStatusSave(savePost.getSaveStatus());
                this.forumList.set(i, post);
                notifyDataSetChanged();
                break;
            }
        }
    }

    public void removePost(String postID) {
        for (int i = 0; i < this.forumList.size(); i++) {
            Forum.Post post = this.forumList.get(i);
            if (post.getPostID().equalsIgnoreCase(postID)) {
                this.forumList.remove(i);
                notifyDataSetChanged();
                break;
            }
        }
    }

    public void setLikePost(Forum.LikePost likePost) {
        for (int i = 0; i < this.forumList.size(); i++) {
            Forum.Post post = this.forumList.get(i);
            if (post.getPostID().equalsIgnoreCase(likePost.getPostID())) {
                post.setStatusLike(likePost.getLike());
                int like = likePost.getLike().equalsIgnoreCase("true") ?
                        Integer.parseInt(post.getLike()) + 1
                        : Integer.parseInt(post.getLike()) - 1;
                post.setLike(String.valueOf(like));
                this.forumList.set(i, post);
                notifyDataSetChanged();
                break;
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout;
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case ChildMainForumAdapter.SHARE_TRADE:
                layout = R.layout.recycler_share_trade_main_forum;
                break;
            case ChildMainForumAdapter.NEWS:
                layout = R.layout.recycler_news_main_forum;
                break;
            case ChildMainForumAdapter.REPOST_NEWS:
                layout = R.layout.recycler_repost_news_main_forum;
                break;
            case ChildMainForumAdapter.REPOST_GENERAL:
                layout = R.layout.recycler_repost_child_main_forum;
                break;
            case ChildMainForumAdapter.NO_DATA:
                layout = R.layout.recycler_empty_image;
                break;
            case ChildMainForumAdapter.LOADING:
                layout = R.layout.custom_loading_white;
                break;
            default:
                layout = R.layout.recycler_child_main_forum;
        }

        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        switch (viewType) {
            case ChildMainForumAdapter.SHARE_TRADE:
                viewHolder = new ShareTradeViewHolder(v, this.onPostClick);
                break;
            case ChildMainForumAdapter.NEWS:
                viewHolder = new NewsViewHolder(v, this.onPostClick);
                break;
            case ChildMainForumAdapter.REPOST_NEWS:
                viewHolder = new RepostNewsViewHolder(v, this.onPostClick);
                break;
            case ChildMainForumAdapter.REPOST_GENERAL:
                viewHolder = new RepostGeneralHolder(v, this.onPostClick);
                break;
            case ChildMainForumAdapter.NO_DATA:
            case ChildMainForumAdapter.LOADING:
                viewHolder = new NoDataViewHolder(v, this.onPostClick);
                break;
            default:
                viewHolder = new GeneralHolder(v, this.onPostClick);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder myHolder, int position) {
        if (getItemViewType(position) != ChildMainForumAdapter.NO_DATA && getItemViewType(position) != ChildMainForumAdapter.LOADING) {
            Forum.Post post = forumList.get(position);
            switch (getItemViewType(position)) {
                case ChildMainForumAdapter.SHARE_TRADE:
                    ShareTradeViewHolder viewHolder = (ShareTradeViewHolder) myHolder;
                    viewHolder.loadData(post, profileID, type.equals(Type.TRENDING));
                    break;
                case ChildMainForumAdapter.NEWS:
                    NewsViewHolder newsViewHolder = (NewsViewHolder) myHolder;
                    newsViewHolder.loadData(post, profileID, type.equals(Type.TRENDING));
                    break;
                case ChildMainForumAdapter.REPOST_NEWS:
                    RepostNewsViewHolder repostNewsViewHolder = (RepostNewsViewHolder) myHolder;
                    repostNewsViewHolder.setData(post, profileID);
                    break;
                case ChildMainForumAdapter.REPOST_GENERAL:
                    RepostGeneralHolder repostGeneralHolder = (RepostGeneralHolder) myHolder;
                    repostGeneralHolder.setData(post, profileID);
                    break;
                default:
                    GeneralHolder generalHolder = (GeneralHolder) myHolder;
                    generalHolder.setData(post, profileID, type.equals(Type.TRENDING));
            }
        }
    }

    @Override
    public int getItemCount() {
        return forumList.size() == 0 ? 1 : forumList.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 0;

        String currentType;

        if (isDataExist)
            return LOADING;
        if (this.forumList.size() == 0)
            return NO_DATA;

        if ((type.equals(Type.TRENDING)
                || type.equals(Type.TIMELINE)
                || type.equals(Type.PROFILE)) && null == forumList.get(position).getPost()) {
            currentType = forumList.get(position).getType();
        } else if (null != forumList.get(position).getPost()) {
            if (null != forumList.get(position).getPost().getPromoNews()) {
                currentType = Type.REPOST_NEWS;
            } else {
                currentType = Type.REPOST;
            }
        } else {
            currentType = type;
        }

        if (null != currentType) {
            switch (currentType) {
                case Type.STRATEGY:
                    viewType = ChildMainForumAdapter.STRATEGY;
                    break;
                case Type.SHARE_TRADE:
                    viewType = ChildMainForumAdapter.SHARE_TRADE;
                    break;
                case Type.NEWS:
                    viewType = ChildMainForumAdapter.NEWS;
                    break;
                case Type.REPOST_NEWS:
                    viewType = ChildMainForumAdapter.REPOST_NEWS;
                    break;
                case Type.REPOST:
                    viewType = ChildMainForumAdapter.REPOST_GENERAL;
                    break;
            }
        }
        return viewType;
    }
}

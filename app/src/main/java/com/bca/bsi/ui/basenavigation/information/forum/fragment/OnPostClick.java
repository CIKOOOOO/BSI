package com.bca.bsi.ui.basenavigation.information.forum.fragment;

import com.bca.bsi.model.Forum;

public interface OnPostClick {
    void onDetailPost(String postID);

    void onPostLike(String postID);

    void onReport(String postID, String type);

    void onSavedPost(String postID);

    void onOtherProfile(String profileID);

    void onMyProfile();

    void onDetailNews(String newsID);

    void onResharePost(boolean isReshare, String postID);

    void onDeletePost(String postID);

    void onEditPost(Forum.Post post);

    void onDetailProduct(String reksadanaID);
}

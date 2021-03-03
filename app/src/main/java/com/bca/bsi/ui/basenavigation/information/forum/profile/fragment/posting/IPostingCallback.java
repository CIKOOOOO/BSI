package com.bca.bsi.ui.basenavigation.information.forum.profile.fragment.posting;

import com.bca.bsi.model.Forum;

public interface IPostingCallback {

    void onDeleteSuccess(String postID);

    void onReshareResult(boolean isReshare, String postID);

    void onFailed(String msg);

    void onLikeResult(Forum.LikePost likePost);

    void onSessionExpired();
}

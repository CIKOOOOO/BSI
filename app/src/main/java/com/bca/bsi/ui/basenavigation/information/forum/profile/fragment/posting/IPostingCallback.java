package com.bca.bsi.ui.basenavigation.information.forum.profile.fragment.posting;

public interface IPostingCallback {

    void onDeleteSuccess(String postID);

    void onReshareResult(boolean isReshare, String postID);

    void onFailed(String msg);

}

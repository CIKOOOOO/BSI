package com.bca.bsi.ui.basenavigation.information.forum.profile.fragment.bookmark;

import com.bca.bsi.model.Forum;

import java.util.List;

public interface IBookmarkCallback {

    void onSaveResult(Forum.SavePost savePost);

    void onReshareResult(boolean isReshare, String postID);

    void onLoadReportData(List<Forum.Report> reportList, String type, String postID);

    void onFailed(String msg);

    void onLikeResult(Forum.LikePost likePost);

}

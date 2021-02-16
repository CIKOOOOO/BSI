package com.bca.bsi.ui.basenavigation.information.forum.otherprofile;

import com.bca.bsi.model.Forum;

import java.util.List;

public interface IOtherProfileCallback {

    void onLoadData(Forum.User user, List<Forum.Post> postList);

    void onLoadData(Forum.User user);

    void onSaveResult(Forum.SavePost savePost);

    void onReshareResult(boolean isReshare, String postID);

    void onLoadReportData(List<Forum.Report> reportList, String type, String postID);

    void onReportSuccess();

    void onLikeResult(Forum.LikePost likePost);

    void onFailed(String msg);
}

package com.bca.bsi.ui.basenavigation.information.forum.fragment;

import com.bca.bsi.model.Forum;

import java.util.List;

public interface IChildMainForumCallback {

    void onLoadData(List<Forum.Post> postList);

    void onFailed(String msg);

    void onLoadReportData(List<Forum.Report> reportList, String type, String postID);

    void onOutOfData();

    void onReshareResult(boolean isReshare, String postID);

    void onSaveResult(Forum.SavePost savePost);

    void onLikeResult(Forum.LikePost likePost);

    void onDeleteSuccess(String postID);
}

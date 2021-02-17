package com.bca.bsi.ui.basenavigation.information.forum.comment;

import com.bca.bsi.model.Forum;

import java.util.List;

public interface ICommentCallback {

    void onLoadComment(Forum.Post post, List<Forum.Comment> commentList, int type);

    void onFailed(String msg);

    void onSuccessReport();

    void onLoadReport(List<Forum.Report> reportList, Forum.Comment comment);

    void onLoadReport(List<Forum.Report> reportList, Forum.Post post);

    void onLikeResult(Forum.LikePost likePost);

    void onRepostSuccess();

    void onDeleteSuccess();

    void onDeleteCommentSuccess(String commentID);

    void onSuccessSendComment();
}

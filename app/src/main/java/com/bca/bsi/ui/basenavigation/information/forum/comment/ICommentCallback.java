package com.bca.bsi.ui.basenavigation.information.forum.comment;

import com.bca.bsi.model.Forum;

import java.util.List;

public interface ICommentCallback {

    void onLoadComment(List<Forum.Comment> commentList);

    void onFailed(String msg);

    void onSuccessReport();
}

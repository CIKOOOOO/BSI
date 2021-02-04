package com.bca.bsi.ui.basenavigation.information.forum.post.direct;

import com.bca.bsi.model.User;

import java.util.List;

public interface IDirectShareCallback {
    void onLoadForumUser(List<User.ForumUser> forumUserList);

    void onLoadChosenForumUser(List<User.ForumUser> forumUserList);

    void onFailed(String msg);

    void onSuccessPost();
}

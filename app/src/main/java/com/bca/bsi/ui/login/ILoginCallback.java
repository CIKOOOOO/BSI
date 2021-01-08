package com.bca.bsi.ui.login;

import com.bca.bsi.model.User;

public interface ILoginCallback {
    void onSuccess(User.ForumUser forumUser, User.WelmaUser welmaUser);

    void onFailed(String msg);
}

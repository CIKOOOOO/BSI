package com.bca.bsi.ui.basenavigation.information.forum.profile.connection;

import com.bca.bsi.model.Forum;

import java.util.List;

public interface IConnectionCallback {

    void onLoadData(List<Forum.Connection> connectionList);

    void onLoadData(Forum.User user);

    void onFailed(String msg);

    void onSessionExpired();
}

package com.bca.bsi.ui.basenavigation.information.forum.otherprofile;

import com.bca.bsi.model.Forum;

import java.util.List;

public interface IOtherProfileCallback {

    void onLoadData(Forum.User user, List<Forum.Post> postList);

    void onLoadData(Forum.User user);

    void onFailed(String msg);
}

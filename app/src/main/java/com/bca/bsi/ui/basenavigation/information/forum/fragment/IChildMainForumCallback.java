package com.bca.bsi.ui.basenavigation.information.forum.fragment;

import com.bca.bsi.model.Forum;

import java.util.List;

public interface IChildMainForumCallback {

    void onLoadData(List<Forum.Post> postList);

    void onFailed(String msg);
}

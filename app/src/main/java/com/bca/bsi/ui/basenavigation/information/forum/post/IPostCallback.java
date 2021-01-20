package com.bca.bsi.ui.basenavigation.information.forum.post;

import com.bca.bsi.model.Forum;

import java.util.List;

public interface IPostCallback {
    void onLoadCategoryData(List<Forum.Category> categoryList);

    void onFailed(String msg);
}

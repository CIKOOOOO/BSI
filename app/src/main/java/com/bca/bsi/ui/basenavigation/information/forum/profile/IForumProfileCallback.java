package com.bca.bsi.ui.basenavigation.information.forum.profile;

import com.bca.bsi.model.Forum;

import java.util.List;

public interface IForumProfileCallback {
    void onLoadData(Forum.User user, List<Forum.Post> postList, List<Forum.Post> bookmarkList);

    void onLoadProfilePicture(List<Forum.ProfilePicture> profilePictureList);

    void onFailed(String msg);

    void onDismissBottomNavigation();
}

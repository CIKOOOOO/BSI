package com.bca.bsi.ui.basenavigation.information.forum.profile.inbox;

import com.bca.bsi.model.Forum;

import java.util.List;

public interface IInboxCallback {
    void onLoadInbox(List<Forum.Inbox> inboxes);
}

package com.bca.bsi.ui.basenavigation.information.forum.post.direct;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.User;
import com.bca.bsi.utils.dummydata.DummyData;

import java.util.ArrayList;
import java.util.List;

public class DirectShareViewModel extends AndroidViewModel {

    private IDirectShareCallback callback;
    private ApiInterface apiInterface;
    private List<User.ForumUser> chosenUserList;
    private List<User.ForumUser> forumUserList;
    private List<User.ForumUser> visibleForumList;

    public void setCallback(IDirectShareCallback callback) {
        this.callback = callback;
    }

    public DirectShareViewModel(@NonNull Application application) {
        super(application);
        chosenUserList = new ArrayList<>();
        forumUserList = new ArrayList<>();
        visibleForumList = new ArrayList<>();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public List<User.ForumUser> getChosenUserList() {
        return chosenUserList;
    }

    public void loadUser(String username) {
        this.forumUserList = DummyData.getForumUser();
        this.visibleForumList.addAll(this.forumUserList);
        callback.onLoadForumUser(DummyData.getForumUser());
    }

    public void addChosenUser(User.ForumUser forumUser) {
        this.chosenUserList.add(forumUser);
        for (int i = 0; i < this.visibleForumList.size(); i++) {
            User.ForumUser forumUser1 = this.visibleForumList.get(i);
            if (forumUser1.getProfileID().equalsIgnoreCase(forumUser.getProfileID())) {
                this.visibleForumList.remove(i);
                break;
            }
        }
        callback.onLoadForumUser(this.visibleForumList);
        callback.onLoadChosenForumUser(this.chosenUserList);
    }

    public void removeChosenUser(User.ForumUser forumUser) {
        for (int i = 0; i < this.chosenUserList.size(); i++) {
            User.ForumUser forumUser1 = this.chosenUserList.get(i);
            if (forumUser1.getProfileID().equalsIgnoreCase(forumUser.getProfileID())) {
                this.chosenUserList.remove(i);
                break;
            }
        }
        for (int i = 0; i < this.forumUserList.size(); i++) {
            User.ForumUser forumUser1 = this.forumUserList.get(i);
            if (forumUser1.getProfileID().equalsIgnoreCase(forumUser.getProfileID())) {
                this.visibleForumList.add(forumUser);
                break;
            }
        }
        callback.onLoadForumUser(this.visibleForumList);
        callback.onLoadChosenForumUser(this.chosenUserList);
    }

    private void compareData() {
        this.visibleForumList.addAll(this.forumUserList);
        int removeCounter = 0;
        for (int i = 0; i < this.forumUserList.size(); i++) {
            boolean isChosen = false;
            User.ForumUser forumUser = this.forumUserList.get(i);
            for (int j = 0; j < this.chosenUserList.size(); j++) {
                User.ForumUser forumUser1 = this.chosenUserList.get(j);
                if (forumUser.getProfileID().equalsIgnoreCase(forumUser1.getProfileID())) {
                    isChosen = true;
                    break;
                }
            }

            if (isChosen) {
                removeCounter++;
                this.visibleForumList.remove(i + removeCounter);
            }
        }
        callback.onLoadForumUser(this.visibleForumList);
    }
}

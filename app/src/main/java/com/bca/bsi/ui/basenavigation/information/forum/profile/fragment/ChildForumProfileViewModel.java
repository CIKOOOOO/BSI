package com.bca.bsi.ui.basenavigation.information.forum.profile.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;

public class ChildForumProfileViewModel extends AndroidViewModel {

    private IChildForumProfileCallback callback;
    private ApiInterface apiInterface;

    public void setCallback(IChildForumProfileCallback callback) {
        this.callback = callback;
    }

    public ChildForumProfileViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadData(String type){

    }

}

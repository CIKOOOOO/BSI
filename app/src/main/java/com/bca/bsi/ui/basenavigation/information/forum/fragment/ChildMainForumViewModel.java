package com.bca.bsi.ui.basenavigation.information.forum.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.utils.dummydata.DummyData;

public class ChildMainForumViewModel extends AndroidViewModel {

    private ApiInterface apiInterface;
    private IChildMainForumCallback callback;

    public void setCallback(IChildMainForumCallback callback) {
        this.callback = callback;
    }

    public ChildMainForumViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadForumPost(String type){
        callback.onLoadData(DummyData.getPostList());
    }
}

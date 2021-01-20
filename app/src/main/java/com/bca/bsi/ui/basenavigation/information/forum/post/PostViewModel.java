package com.bca.bsi.ui.basenavigation.information.forum.post;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.utils.dummydata.DummyData;

public class PostViewModel extends AndroidViewModel {

    private IPostCallback callback;
    private ApiInterface apiInterface;

    public void setCallback(IPostCallback callback) {
        this.callback = callback;
    }

    public PostViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadCategoryData() {
        callback.onLoadCategoryData(DummyData.getCategoryList());
    }
}

package com.bca.bsi.ui.basenavigation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.model.Forum;

public class BaseNavigationViewModel extends AndroidViewModel {

    private ApiInterface apiInterface;
    private IBaseNavigatonCallback callback;

    public void setCallback(IBaseNavigatonCallback callback) {
        this.callback = callback;
    }

    public BaseNavigationViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void reportPostOrForumWith(Forum.Report report){

    }


}

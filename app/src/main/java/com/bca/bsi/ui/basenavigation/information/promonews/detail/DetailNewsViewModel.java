package com.bca.bsi.ui.basenavigation.information.promonews.detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;

public class DetailNewsViewModel extends AndroidViewModel {

    private ApiInterface apiInterface;
    private IDetailNewsCallback callback;

    public void setCallback(IDetailNewsCallback callback) {
        this.callback = callback;
    }

    public DetailNewsViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadNews(String token, String newsID){

    }
}

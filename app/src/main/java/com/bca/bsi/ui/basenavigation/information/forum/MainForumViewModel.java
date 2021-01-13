package com.bca.bsi.ui.basenavigation.information.forum;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.utils.dummydata.DummyData;

public class MainForumViewModel extends AndroidViewModel {

    private IMainForumCallback callback;
    private ApiInterface apiInterface;

    public void setCallback(IMainForumCallback callback) {
        this.callback = callback;
    }

    public MainForumViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadReportData(){
        callback.onLoadReportData(DummyData.getReportList());
    }

}

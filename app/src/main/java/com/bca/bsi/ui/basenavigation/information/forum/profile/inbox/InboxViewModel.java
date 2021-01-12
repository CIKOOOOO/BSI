package com.bca.bsi.ui.basenavigation.information.forum.profile.inbox;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.utils.dummydata.DummyData;

public class InboxViewModel extends AndroidViewModel {

    private IInboxCallback callback;
    private ApiInterface apiInterface;

    public void setCallback(IInboxCallback callback) {
        this.callback = callback;
    }

    public InboxViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadData(){
        callback.onLoadInbox(DummyData.getInboxList());
    }
}

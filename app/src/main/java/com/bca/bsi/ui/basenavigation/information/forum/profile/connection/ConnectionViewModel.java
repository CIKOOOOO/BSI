package com.bca.bsi.ui.basenavigation.information.forum.profile.connection;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;
import com.bca.bsi.utils.constant.Type;

public class ConnectionViewModel extends AndroidViewModel {

    private IConnectionCallback callback;
    private ApiInterface apiInterface;

    public void setCallback(IConnectionCallback callback) {
        this.callback = callback;
    }

    public ConnectionViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void loadConnection(String type) {
        if (type.equals(Type.FOLLOWING)) {
            loadFollowing();
        } else {
            loadFollowers();
        }
    }

    private void loadFollowing() {

    }

    private void loadFollowers() {

    }

}

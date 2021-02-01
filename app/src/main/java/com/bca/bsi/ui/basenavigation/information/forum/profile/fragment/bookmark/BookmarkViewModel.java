package com.bca.bsi.ui.basenavigation.information.forum.profile.fragment.bookmark;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.api.ApiClient;
import com.bca.bsi.api.ApiInterface;

public class BookmarkViewModel extends AndroidViewModel {

    private ApiInterface apiInterface;

    public BookmarkViewModel(@NonNull Application application) {
        super(application);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }



}

package com.bca.bsi.ui.basenavigation.information.promonews.news;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.utils.dummydata.DummyData;

public class NewsInformationViewModel extends AndroidViewModel {

    private INewsInformationCallback callback;

    public void setCallback(INewsInformationCallback callback) {
        this.callback = callback;
    }

    public NewsInformationViewModel(@NonNull Application application) {
        super(application);
    }

    public void getNewsList() {
        callback.resultOf(DummyData.getPromoNewsList());
    }
}

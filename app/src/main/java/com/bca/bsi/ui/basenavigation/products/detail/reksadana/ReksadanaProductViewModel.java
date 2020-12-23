package com.bca.bsi.ui.basenavigation.products.detail.reksadana;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.utils.dummydata.DummyData;

public class ReksadanaProductViewModel extends AndroidViewModel {


    private IReksaDanaProductCallback callback;

    public void setCallback(IReksaDanaProductCallback callback) {
        this.callback = callback;
    }

    public ReksadanaProductViewModel(@NonNull Application application) {
        super(application);
    }


    public void getReksaDanaList() {
        callback.resultOf(DummyData.getReksaDanaDummyList());
    }
}

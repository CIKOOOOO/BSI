package com.bca.bsi.ui.basenavigation.information.promonews.promo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bca.bsi.utils.dummydata.DummyData;

public class PromoInformationViewModel extends AndroidViewModel {

    private IPromoInformationCallback callback;

    public void setCallback(IPromoInformationCallback callback) {
        this.callback = callback;
    }

    public PromoInformationViewModel(@NonNull Application application) {
        super(application);
    }

    public void getPromoList() {
        callback.resultOf(DummyData.getPromoNewsList());
    }

}

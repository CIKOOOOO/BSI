package com.bca.bsi.ui.basenavigation.information.promonews.detail;

import com.bca.bsi.model.PromoNews;

public interface IDetailNewsCallback {

    void onLoadDetailNews(PromoNews promoNews);

    void onFailed(String msg);

    void onSessionExpired();
}

package com.bca.bsi.ui.basenavigation.portfolio.purchasing;

import com.bca.bsi.model.Portfolio;

import java.util.List;

public interface IPurchasingSmartbotCallback {

    void onLoadData(List<Portfolio> bundles);

    void onLoadDataCustom(List<Portfolio> bundles);

    void onFail(String msg);

    void onSessionExpired();
}

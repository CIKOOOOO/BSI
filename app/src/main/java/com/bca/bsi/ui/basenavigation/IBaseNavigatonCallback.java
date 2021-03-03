package com.bca.bsi.ui.basenavigation;

import com.bca.bsi.model.TipsOfTheWeek;

public interface IBaseNavigatonCallback {
    void onLoadTipsOfTheWeek(TipsOfTheWeek tipsOfTheWeek);

    void onReportSuccess();

    void onFailed(String msg);

    void onSessionExpired();
}

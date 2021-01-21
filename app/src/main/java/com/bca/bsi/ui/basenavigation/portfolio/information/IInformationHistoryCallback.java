package com.bca.bsi.ui.basenavigation.portfolio.information;

import com.bca.bsi.model.Portfolio;

import java.util.List;

public interface IInformationHistoryCallback {

    void onLoadPortfolioData(List<Portfolio.Information> informationList);

    void onLoadHistoryTransaction(List<Portfolio.History> historyList);

    void onFailed(String msg);

}

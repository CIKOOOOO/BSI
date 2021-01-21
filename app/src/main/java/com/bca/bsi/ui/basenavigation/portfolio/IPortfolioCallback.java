package com.bca.bsi.ui.basenavigation.portfolio;

import com.bca.bsi.model.Portfolio;

import java.util.List;

public interface IPortfolioCallback {
    void onLoadData(List<Portfolio> bundles);
    void onFail(String msg);
}

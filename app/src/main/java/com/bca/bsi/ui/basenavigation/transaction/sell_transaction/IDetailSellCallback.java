package com.bca.bsi.ui.basenavigation.transaction.sell_transaction;

import com.bca.bsi.model.Product;

public interface IDetailSellCallback {
    void onLoadData(Product.DetailReksaDana detailReksaDana);

    void onFailed(String msg);

    void onSessionExpired();
}

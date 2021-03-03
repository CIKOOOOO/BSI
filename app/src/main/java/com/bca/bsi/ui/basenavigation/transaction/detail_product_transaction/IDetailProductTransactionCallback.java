package com.bca.bsi.ui.basenavigation.transaction.detail_product_transaction;

import com.bca.bsi.model.Product;
import com.bca.bsi.model.User;

public interface IDetailProductTransactionCallback {
    void loadSaldo(User.BCAUser.Rekening rekening, Product.DetailReksaDana detailReksaDana);

    void onFailed(String msg);

    void onSessionExpired();
}

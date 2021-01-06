package com.bca.bsi.ui.basenavigation.products.detail.reksadana.detailreksadana;

import com.bca.bsi.model.Product;

public interface IDetailReksaDanaCallback {

    void onLoadReksaDanaDetail(Product.DetailReksaDana detailReksaDana);

    void onFailed(String msg);

}

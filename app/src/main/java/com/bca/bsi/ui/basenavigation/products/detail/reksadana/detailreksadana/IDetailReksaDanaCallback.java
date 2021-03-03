package com.bca.bsi.ui.basenavigation.products.detail.reksadana.detailreksadana;

import com.bca.bsi.model.Product;

import java.util.List;

public interface IDetailReksaDanaCallback {

    void onLoadReksaDanaDetail(Product.DetailReksaDana detailReksaDana, List<Product.Performance> performances);

    void onFailed(String msg);

    void onSessionExpired();
}

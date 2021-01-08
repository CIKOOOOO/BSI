package com.bca.bsi.ui.basenavigation.products.detail.reksadana;

import com.bca.bsi.model.Product;

import java.util.List;

public interface IReksaDanaProductCallback {
    void resultOf(List<Product.ReksaDana> reksaDanaList);

    void onFailed(String msg);
}

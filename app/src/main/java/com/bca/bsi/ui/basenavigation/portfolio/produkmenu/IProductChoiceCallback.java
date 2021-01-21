package com.bca.bsi.ui.basenavigation.portfolio.produkmenu;

import com.bca.bsi.model.Product;

import java.util.List;

public interface IProductChoiceCallback {
    void onLoadData(List<Product.ReksaDana> products);
    void onFail(String msg);
}

package com.bca.bsi.ui.basenavigation.more.calculator;

import com.bca.bsi.model.Product;

import java.util.List;

public interface IProductsCalculatorCallback {
    void onLoadData(List<Product.ReksaDana> products);
    void onFail(String msg);
}

package com.bca.bsi.ui.basenavigation.more.calculator.popup;

import com.bca.bsi.model.Product;

import java.util.List;

public interface IProductsCalculatorCallback {
    void onLoadData(List<Product.ReksaDana> products);
    void onFail(String msg);
    void onLoadReksaDanaDetail(Product.DetailReksaDana detailReksaDana, List<Product.Performance> performances);
    void onFailed(String msg);
}

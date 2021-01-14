package com.bca.bsi.model;

import java.util.List;

public class ProductRekomens {
    List<ProductRekomen> productRekomenList;

    public ProductRekomens(List<ProductRekomen> productRekomenList) {
        this.productRekomenList = productRekomenList;
    }

    public List<ProductRekomen> getProductRekomenList() {
        return productRekomenList;
    }

}

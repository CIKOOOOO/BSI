package com.bca.bsi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Portfolio {
    @SerializedName("reksa_dana_list")
    private List<ProductRekomen> productRekomenList;

    @SerializedName("return")
    private String expReturn;

    @SerializedName("risk")
    private String risk;

    @SerializedName("minimum_purchase")
    private String minPurchase;

    private String bundleName;

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public Portfolio(String expReturn, String risk, String bundleName) {
        this.expReturn = expReturn;
        this.risk = risk;
        this.bundleName = bundleName;
    }

    public String getExpReturn() {
        return expReturn;
    }

    public String getRisk() {
        return risk;
    }

    public String getBundleName() {
        return bundleName;
    }

    public String getMinPurchase() {
        return minPurchase;
    }

    public void setMinPurchase(String minPurchase) {
        this.minPurchase = minPurchase;
    }

    public List<ProductRekomen> getProductRekomenList() {
        return productRekomenList;
    }
}

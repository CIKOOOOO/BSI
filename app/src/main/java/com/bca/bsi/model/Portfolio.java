package com.bca.bsi.model;

public class Portfolio {
    private String expReturn;
    private String risk;
    private String bundleName;

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
}

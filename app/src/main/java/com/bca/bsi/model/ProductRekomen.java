package com.bca.bsi.model;

public class ProductRekomen {
    private String productName, lastDate, percentage, kinerja, nab, jenisReksadana;

    public ProductRekomen(String productName, String lastDate, String percentage, String kinerja, String nab, String jenisReksadana) {
        this.productName = productName;
        this.lastDate = lastDate;
        this.percentage = percentage;
        this.kinerja = kinerja;
        this.nab = nab;
        this.jenisReksadana = jenisReksadana;
    }

    public String getProductName() {
        return productName;
    }

    public String getLastDate() {
        return lastDate;
    }

    public String getPercentage() {
        return percentage;
    }

    public String getKinerja() {
        return kinerja;
    }

    public String getNab() {
        return nab;
    }

    public String getJenisReksadana() {
        return jenisReksadana;
    }
}

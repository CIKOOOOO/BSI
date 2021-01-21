package com.bca.bsi.model;

import com.google.gson.annotations.SerializedName;

public class ProductRekomen {
    @SerializedName("id_reksa_dana")
    private String reksaId;

    @SerializedName("nama_reksa_dana")
    private String productName;

    @SerializedName("update_date")
    private String lastDate;

    @SerializedName("persentase")
    private String percentage;

    @SerializedName("kinerja_terakhir")
    private String kinerja;

    @SerializedName("nab")
    private String nab;

    @SerializedName("jenis_reksa_dana")
    private String jenisReksadana;

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

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

    public String getReksaId() {
        return reksaId;
    }

    public void setReksaId(String reksaId) {
        this.reksaId = reksaId;
    }
}

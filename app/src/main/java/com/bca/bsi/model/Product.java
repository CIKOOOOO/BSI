package com.bca.bsi.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    public static class ProductType {
        private String title, description;
        private int image;

        public ProductType(String title, String description, int image) {
            this.title = title;
            this.description = description;
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public int getImage() {
            return image;
        }
    }

    public static class ReksaDana {

        @SerializedName("reksadana_id")
        private String reksadanaID;

        @SerializedName("reksadana_name")
        private String name;

        @SerializedName("category")
        private String type;

        @SerializedName("update_date")
        private String date;

        @SerializedName("kinerja_1_bulan")
        private String kinerja;

        @SerializedName("nab_per_unit")
        private String nab;

        public ReksaDana() {
        }

        public ReksaDana(String reksadanaID, String name, String type, String date, String kinerja, String nab) {
            this.reksadanaID = reksadanaID;
            this.name = name;
            this.type = type;
            this.date = date;
            this.kinerja = kinerja;
            this.nab = nab;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getDate() {
            return date;
        }

        public String getReksadanaID() {
            return reksadanaID;
        }

        public String getKinerja() {
            return kinerja;
        }

        public String getNab() {
            return nab;
        }
    }

    public static class DetailReksaDana {

        @SerializedName("name")
        private String name;

        @SerializedName("reksadana_id")
        private String reksadanaID;

        @SerializedName("manager_investasi")
        private String managerInvestasiID;

        @SerializedName("product_category_id")
        private String productCategoryID;

        @SerializedName("bank_kustodian")
        private String bankKustodian;

        @SerializedName("release_date")
        private String releaseDate;

        @SerializedName("minimum_pembelian_pertama")
        private String minimumPembelianPertama;

        @SerializedName("minimum_pembelian_berikut")
        private String minimumPembelianBerikut;

        @SerializedName("minimum_penjualan")
        private String mininumPenjualan;

        @SerializedName("minimum_saldo_unit")
        private String minimumSaldoUnit;

        @SerializedName("biaya_pembelian")
        private String biayaPembelian;

        @SerializedName("biaya_penjualan")
        private String biayaPenjualan;

        @SerializedName("biaya_manajer_investasi")
        private String biayaManajerInvestasi;

        @SerializedName("biaya_agen")
        private String biayaAgen;

        @SerializedName("biaya_bank_kustodian")
        private String biayaBankKustodian;

        @SerializedName("product_category")
        private String productCategory;

        @SerializedName("nab_per_unit")
        private String nabPerUnit;

        @SerializedName("update_date")
        private String updateDate;

        @SerializedName("kinerja_3_tahun")
        private String kinerja3Tahun;

        @SerializedName("kinerja_6_Bulan")
        private String kinerja6Bulan;

        @SerializedName("kinerja_5_tahun")
        private String kinerja5Tahun;

        @SerializedName("kinerja_1_tahun")
        private String kinerja1Tahun;

        @SerializedName("kinerja_1_bulan")
        private String kinerja1Bulan;

        @SerializedName("ytd")
        private String yearToDate;

        @SerializedName("RoR")
        private String ror;
		
        public DetailReksaDana() {
        }

        public String getName() {
            return name;
        }

        public String getReksadanaID() {
            return reksadanaID;
        }

        public String getManagerInvestasiID() {
            return managerInvestasiID;
        }

        public String getProductCategoryID() {
            return productCategoryID;
        }

        public String getBankKustodian() {
            return bankKustodian;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public String getMinimumPembelianPertama() {
            return minimumPembelianPertama;
        }

        public String getMinimumPembelianBerikut() {
            return minimumPembelianBerikut;
        }

        public String getMininumPenjualan() {
            return mininumPenjualan;
        }

        public String getMinimumSaldoUnit() {
            return minimumSaldoUnit;
        }

        public String getBiayaPembelian() {
            return biayaPembelian;
        }

        public String getBiayaPenjualan() {
            return biayaPenjualan;
        }

        public String getBiayaManajerInvestasi() {
            return biayaManajerInvestasi;
        }

        public String getBiayaAgen() {
            return biayaAgen;
        }

        public String getBiayaBankKustodian() {
            return biayaBankKustodian;
        }

        public String getProductCategory() {
            return productCategory;
        }

        public String getNabPerUnit() {
            return nabPerUnit;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public String getKinerja3Tahun() {
            return kinerja3Tahun;
        }

        public String getKinerja6Bulan() {
            return kinerja6Bulan;
        }

        public String getKinerja5Tahun() {
            return kinerja5Tahun;
        }

        public String getKinerja1Tahun() {
            return kinerja1Tahun;
        }

        public String getKinerja1Bulan() {
            return kinerja1Bulan;
        }

        public String getYearToDate() {
            return yearToDate;
        }

        public String getRor() {
            return ror;
        }
    }

    public static class Performance {
        private String period;
        private double value;

        public Performance(String period, double value) {
            this.period = period;
            this.value = value;
        }

        public String getPeriod() {
            return period;
        }

        public double getValue() {
            return value;
        }
    }

    public static class ProductTransaction{
        private String name;
        private String date;
        private String price;

        public ProductTransaction(String name, String date, String price) {
            this.name = name;
            this.date = date;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public String getPrice() {
            return price;
        }
    }
}

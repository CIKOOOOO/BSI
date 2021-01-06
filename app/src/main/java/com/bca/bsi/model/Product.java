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

        @SerializedName("Kinerja_1_bulan")
        private String kinerja;

        @SerializedName("NAB_per_unit")
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

        @SerializedName("manager_investasi_id")
        private String managerInvestasiID;

        @SerializedName("product_category_id")
        private String productCategoryID;

        @SerializedName("bank_kustodian_id")
        private String bankKustodianID;

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

        @SerializedName("kinerja_1_bulan")
        private String kinerjaSatuBulan;

        @SerializedName("NAB_1_unit")
        private String nabSatuBulan;

        @SerializedName("update_date")
        private String updateDate;

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

        public String getBankKustodianID() {
            return bankKustodianID;
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

        public String getKinerjaSatuBulan() {
            return kinerjaSatuBulan;
        }

        public String getNabSatuBulan() {
            return nabSatuBulan;
        }

        public String getUpdateDate() {
            return updateDate;
        }
    }


}

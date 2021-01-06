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


}

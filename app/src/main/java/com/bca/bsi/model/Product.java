package com.bca.bsi.model;

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

        private String name, type, date;
        private double kinerja, nab;

        public ReksaDana(String name, String type, String date, double kinerja, double nab) {
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

        public double getKinerja() {
            return kinerja;
        }

        public double getNab() {
            return nab;
        }
    }


}

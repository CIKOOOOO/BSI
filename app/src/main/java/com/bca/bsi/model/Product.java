package com.bca.bsi.model;

public class Product {

    public static class ProductType{
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



}

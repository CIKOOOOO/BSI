package com.bca.bsi.model;

public class ProductIH {
    private String name,jenis,unit,cost,date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ProductIH(String name, String jenis, String unit, String cost, String date) {
        this.name = name;
        this.jenis = jenis;
        this.unit = unit;
        this.cost = cost;
        this.date = date;
    }
}

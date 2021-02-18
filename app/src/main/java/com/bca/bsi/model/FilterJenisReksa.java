package com.bca.bsi.model;

public class FilterJenisReksa {
    String jenisReksa;
    boolean isChoosen;

    public FilterJenisReksa(String jenisReksa, boolean isChoosen) {
        this.jenisReksa = jenisReksa;
        this.isChoosen = isChoosen;
    }

    public void setChoosen(boolean choosen) {
        isChoosen = choosen;
    }

    public String getJenisReksa() {
        return jenisReksa;
    }

    public boolean isChoosen() {
        return isChoosen;
    }
}

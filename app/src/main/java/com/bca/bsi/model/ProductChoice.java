package com.bca.bsi.model;

public class ProductChoice {

    private String title,type,lastDate,nab,kinerja;
    private boolean isChoosen;

    public ProductChoice(String title, String type, String lastDate, String nab, String kinerja, boolean isChoosen) {
        this.title = title;
        this.type = type;
        this.lastDate = lastDate;
        this.nab = nab;
        this.kinerja = kinerja;
        this.isChoosen = isChoosen;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getLastDate() {
        return lastDate;
    }

    public String getNab() {
        return nab;
    }

    public String getKinerja() {
        return kinerja;
    }

    public boolean isChoosen() {
        return isChoosen;
    }
}

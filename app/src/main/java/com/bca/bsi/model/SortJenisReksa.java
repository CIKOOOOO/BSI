package com.bca.bsi.model;

public class SortJenisReksa {
    String sortName, sortTypeStart, sortTypeEnd;
    boolean isChoosen;

    public void setChoosen(boolean choosen) {
        isChoosen = choosen;
    }

    public SortJenisReksa(String sortName, String sortTypeStart, String sortTypeEnd, boolean isChoosen) {
        this.sortName = sortName;
        this.sortTypeStart = sortTypeStart;
        this.isChoosen = isChoosen;
        this.sortTypeEnd = sortTypeEnd;
    }

    public String getSortTypeEnd() {
        return sortTypeEnd;
    }

    public String getSortName() {
        return sortName;
    }

    public String getSortTypeStart() {
        return sortTypeStart;
    }

    public boolean isChoosen() {
        return isChoosen;
    }
}

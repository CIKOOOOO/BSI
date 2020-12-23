package com.bca.bsi.utils.dummydata;

import com.bca.bsi.model.Product;

import java.util.ArrayList;
import java.util.List;

public class DummyData {

    private static final String[] title = {"Ashmore Dana Ekuitas Nusantara", "Ashmore Dana Progresif Nusantara", "Bahana Pendapatan Tetap Makara Prima"};
    private static final String[] type = {"Saham", "Saham", "Pendapatan Tetap"};
    private static final String[] date = {"16/12/20", "16/12/20", "16/12/20"};
    private static final double[] kinerja = {14.13, 14.89, 10.13};
    private static final double[] nab = {1462.81, 1575.39, 1672.98};

    public static List<Product.ReksaDana> getReksaDanaDummyList() {
        List<Product.ReksaDana> reksaDanaList = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            Product.ReksaDana reksaDana = new Product.ReksaDana(title[i], type[i], date[i], kinerja[i], nab[i]);
            reksaDanaList.add(reksaDana);
        }
        return reksaDanaList;
    }

}

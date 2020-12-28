package com.bca.bsi.utils.constant;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Constant {

    public static final long SPLASH_DURATION = 2000;
    public static final long MAX_USAGE_DURATION_APPS = 600000;

    public static final String EMPTY = "";

    public static final String[] PRODUCT_TITLE = {"Reksa Dana", "Obligasi", "Asuransi"};
    private static final String[] PRODUCT_DESCRIPTION = {"Investasi Dana Terjangkau", "Investasi Berpendapatan Tetap", "Perlindungan Jiwa, Kesehatan, dan Harta Benda"};
    private static final int[] PRODUCT_IMAGE = {R.drawable.img_asset_reksa_dana, R.drawable.img_asset_obligasi, R.drawable.img_asset_asuransi};


    public static List<Object> getProductTypeList() {
        List<Object> productTypeList = new ArrayList<>();
        for (int i = 0; i < PRODUCT_TITLE.length; i++) {
            Product.ProductType productType = new Product.ProductType(PRODUCT_TITLE[i], PRODUCT_DESCRIPTION[i], PRODUCT_IMAGE[i]);
            productTypeList.add(productType);
        }
        return productTypeList;
    }
}

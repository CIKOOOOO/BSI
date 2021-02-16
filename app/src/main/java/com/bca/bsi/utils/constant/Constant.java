package com.bca.bsi.utils.constant;

import com.bca.bsi.R;
import com.bca.bsi.model.Privacy;
import com.bca.bsi.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Constant {

    public static final long SPLASH_DURATION = 2000;
    public static final long MAX_USAGE_DURATION_APPS = 600000;

    public static final String EMPTY = "";
    //    public static final String BASE_URL = "http://192.168.0.8:8000/";
    public static final String BASE_URL = "http://10.43.2.206:30706/";
    public static final String DATE_FORMAT_1 = "dd MMMM yyyy";
    public static final String DATE_FORMAT_2 = "dd/MM/yyyy";
    public static final String DATE_FORMAT_3 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_FROM_DB = "dd-MMM-yy";

    public static final String[] PRODUCT_TITLE = {"Reksa Dana", "Obligasi", "Asuransi"};
    private static final String[] PRODUCT_DESCRIPTION = {"Investasi Dana Terjangkau", "Investasi Berpendapatan Tetap", "Perlindungan Jiwa, Kesehatan, dan Harta Benda"};
    private static final int[] PRODUCT_IMAGE = {R.drawable.img_asset_reksa_dana, R.drawable.img_asset_obligasi, R.drawable.img_asset_asuransi};

    public static final String[] FORUM_MENU = {"Trending", "Strategy", "Share Trade", "News", "Timeline"};
    public static final String[] FORUM_PROFILE_MENU = {"Posting", "Bookmark"};

    private static final String[] SHARE_TO_NAME = {"Public", "Followers", "Direct"};
    private static final int[] SHARE_IMAGE = {R.drawable.img_public, R.drawable.img_followers, R.drawable.img_direct_message};
    private static final int[] SHARE_IMAGE_CLICK = {R.drawable.img_public_click, R.drawable.img_followers_click, R.drawable.img_direct_message_click};

    public static List<Object> getProductTypeList() {
        List<Object> productTypeList = new ArrayList<>();
        for (int i = 0; i < PRODUCT_TITLE.length; i++) {
            Product.ProductType productType = new Product.ProductType(PRODUCT_TITLE[i], PRODUCT_DESCRIPTION[i], PRODUCT_IMAGE[i]);
            productTypeList.add(productType);
        }
        return productTypeList;
    }

    public static List<Privacy> getListPrivacy() {
        List<Privacy> privacies = new ArrayList<>();
        for (int i = 0; i < SHARE_IMAGE.length; i++) {
            Privacy privacy = new Privacy(SHARE_TO_NAME[i], SHARE_IMAGE[i], SHARE_IMAGE_CLICK[i], false);
            privacies.add(privacy);
        }
        return privacies;
    }
}

package com.bca.bsi.utils.dummydata;

import com.bca.bsi.model.Product;
import com.bca.bsi.model.PromoNews;

import java.util.ArrayList;
import java.util.List;

public class DummyData {

    private static final String[] title = {"Ashmore Dana Ekuitas Nusantara", "Ashmore Dana Progresif Nusantara", "Bahana Pendapatan Tetap Makara Prima"};
    private static final String[] type = {"Saham", "Saham", "Pendapatan Tetap"};
    private static final String[] date = {"16/12/20", "16/12/20", "16/12/20"};
    private static final String[] promoTitle = {"Cashback hingga Rp300 ribu di Promo Leave Contact Asuransi via Welma", "Investasi ST007 untuk Bumi, Dapatkan cashback Rp70 Ribu!"};
    private static final String[] promoDescription = {"#PasangKudaKuda tanpa keluar rumah, dapat cashback hingga Rp300 ribu", "#PasangKudaKuda tanpa keluar rumah, dapat cashback hingga Rp300 ribu"};
    private static final String[] content = {"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"};

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

    public static List<PromoNews> getPromoNewsList() {
        List<PromoNews> promoNewsList = new ArrayList<>();
        for (int i = 0; i < promoTitle.length; i++) {
            PromoNews promoNews = new PromoNews(promoTitle[i], promoDescription[i], content[i], date[i]);
            promoNewsList.add(promoNews);
        }
        return promoNewsList;
    }

}

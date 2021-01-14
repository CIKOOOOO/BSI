package com.bca.bsi.utils.dummydata;

import com.bca.bsi.R;
import com.bca.bsi.model.FilterJenisReksa;
import com.bca.bsi.model.LearningChapter;
import com.bca.bsi.model.Product;
import com.bca.bsi.model.ProductChoice;
import com.bca.bsi.model.ProductRekomen;
import com.bca.bsi.model.PromoNews;
import com.bca.bsi.model.SortJenisReksa;

import java.util.ArrayList;
import java.util.List;

public class DummyData {

    private static final String[] ID = {"ID01", "ID02", "ID03"};
    private static final String[] title = {"Ashmore Dana Ekuitas Nusantara", "Ashmore Dana Progresif Nusantara", "Bahana Pendapatan Tetap Makara Prima"};
    private static final String[] type = {"Saham", "Saham", "Pendapatan Tetap"};
    private static final String[] date = {"16/12/20", "16/12/20", "16/12/20"};
    private static final String[] promoTitle = {"Cashback hingga Rp300 ribu di Promo Leave Contact Asuransi via Welma", "Investasi ST007 untuk Bumi, Dapatkan cashback Rp70 Ribu!"};
    private static final String[] promoDescription = {"#PasangKudaKuda tanpa keluar rumah, dapat cashback hingga Rp300 ribu", "#PasangKudaKuda tanpa keluar rumah, dapat cashback hingga Rp300 ribu"};
    private static final String[] content = {"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"};

    private static final String[] kinerja = {"14.13", "14.89", "10.13"};
    private static final String[] nab = {"1462.81", "1575.39", "1672.98"};

    private static final String[] period = {"1 Bulan", "6 Bulan", "YTD", "1 Tahun", "3 Tahun", "5 Tahun"};
    private static final double[] performance = {5.2, 35.6, 2.6, -0.08, -3.24, 27.57};

    private static final int[] learningChapterRDImage = {R.drawable.img_materi_reksadana_1,R.drawable.img_materi_reksadana_2,R.drawable.img_materi_reksadana_3,R.drawable.img_materi_kuis};
    private static final String[] learningChapterRDTitle = {"Reksa Dana", "Jenis Produk Reksa Dana", "Manfaat & Risiko Reksa Dana", "Kuis Interaktif"};
    private static final String[] learningChapterRDExplanation = {"Merupakan wadah yang dipergunakan untuk menghitung dana dari masyarakat pemodal " +
            "untuk selanjutnya diinvestasikan dalam portofolio Efek oleh Manajer Investasi", "Sangat Konservatif: \n \u25CF Reksa Dana Pasar Uang \n\n Konservatif: " +
            "\n \u25CF Reksa Dana Pendapatan Tetap \n \u25CF Reksa Dana Terproteksi \n Moderat: \n \u25CF Reksa Dana Campuran \n Agresif: \n \u25CF Reksa Dana Saham"};


    private static final String[] kinerjaString = {"+14.13%", "+14.89%", "+10.13%"};
    private static final String[] nabString = {"1462.81", "1575.39", "1672.98"};
    private static final String[] percentage = {"70","20","10"};
    private static final String[] percentage2 = {"10","20","30"};
    private static final boolean[] isChoosen = {false,false,false};

    private static final String[] filterJenisReksaName = {"Pasar Uang","Pendapatan Tetap","Campuran","Saham","Terproteksi"};
    private static final boolean[] isChoosenFilter = {false,false,false,false,false};
    private static final boolean[] isChoosenFilterDefault = {true,true,true,true,true};

    private static final String[] sortJenisReksaName = {"Nama Produk","Nama Produk","Kinerja","Kinerja"};
    private static final String[] sortTypeStart = {"A","Z","Tinggi","Rendah"};
    private static final String[] sortTypeEnd = {"Z","A","Rendah","Tinggi"};
    private static final boolean[] isChoosenSort = {true,false,false,false};
    private static final boolean[] isChoosenSortDefault = {false,false,false,false};


    public static List<Product.ReksaDana> getReksaDanaDummyList() {
        List<Product.ReksaDana> reksaDanaList = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            Product.ReksaDana reksaDana = new Product.ReksaDana(ID[i], title[i], type[i], date[i], kinerja[i], nab[i]);
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

    public static List<Product.Performance> getPerformanceList() {
        List<Product.Performance> performanceList = new ArrayList<>();
        for (int i = 0; i < period.length; i++) {
            Product.Performance performances = new Product.Performance(period[i], performance[i]);
            performanceList.add(performances);
        }
        return performanceList;
    }

    public static List<LearningChapter> getLearningChapterRDList() {
        List<LearningChapter> rdChapterList = new ArrayList<>();
        for (int i = 0; i < learningChapterRDTitle.length; i++) {
            LearningChapter rdChapter = new LearningChapter(learningChapterRDImage[i],learningChapterRDTitle[i],learningChapterRDExplanation[i]);
            rdChapterList.add(rdChapter);
        }
        return rdChapterList;
    }

    public static List<ProductRekomen> getProductRekomenList() {
        List<ProductRekomen> productRekomenList = new ArrayList<>();
        for(int i=0;i<title.length;i++){
            ProductRekomen produk = new ProductRekomen(title[i],date[i],percentage[i],kinerjaString[i],nabString[i],type[i]);
            productRekomenList.add(produk);
        }
        return productRekomenList;
    }

    public static List<ProductRekomen> getProductRekomenListPurchase() {
        List<ProductRekomen> productRekomenList = new ArrayList<>();
        for(int i=0;i<title.length;i++){
            ProductRekomen produk = new ProductRekomen(title[i],date[i],percentage2[i],kinerjaString[i],nabString[i],type[i]);
            productRekomenList.add(produk);
        }
        return productRekomenList;
    }

    public static List<ProductChoice> getProductChoiceList() {
        List<ProductChoice> productChoices = new ArrayList<>();
        for(int i=0;i<title.length;i++){
            ProductChoice produk = new ProductChoice(title[i],type[i],date[i],nabString[i],kinerjaString[i],isChoosen[i]);
            productChoices.add(produk);
        }
        return productChoices;
    }

    public static List<FilterJenisReksa> getFilterJenisReksaList(){
        List<FilterJenisReksa> res = new ArrayList<>();
        for(int i=0;i<5;i++){
            FilterJenisReksa filterJenisReksa = new FilterJenisReksa(filterJenisReksaName[i],isChoosenFilter[i]);
            res.add(filterJenisReksa);
        }
        return res;
    }

    public static List<FilterJenisReksa> getFilterJenisReksaListDefault(){
        List<FilterJenisReksa> res = new ArrayList<>();
        for(int i=0;i<5;i++){
            FilterJenisReksa filterJenisReksa = new FilterJenisReksa(filterJenisReksaName[i],isChoosenFilterDefault[i]);
            res.add(filterJenisReksa);
        }
        return res;
    }

    public static List<SortJenisReksa> getSortJenisReksaList(){
        List<SortJenisReksa> res = new ArrayList<>();
        for(int i=0;i<4;i++){
            SortJenisReksa sortJenisReksa = new SortJenisReksa(sortJenisReksaName[i],sortTypeStart[i],sortTypeEnd[i],isChoosenSort[i]);
            res.add(sortJenisReksa);
        }
        return res;
    }

    public static List<SortJenisReksa> getSortJenisReksaListFalse(){
        List<SortJenisReksa> res = new ArrayList<>();
        for(int i=0;i<4;i++){
            SortJenisReksa sortJenisReksa = new SortJenisReksa(sortJenisReksaName[i],sortTypeStart[i],sortTypeEnd[i],isChoosenSortDefault[i]);
            res.add(sortJenisReksa);
        }
        return res;
    }
}

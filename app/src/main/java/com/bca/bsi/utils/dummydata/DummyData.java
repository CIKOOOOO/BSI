package com.bca.bsi.utils.dummydata;

import com.bca.bsi.R;
import com.bca.bsi.model.LearningChapter;
import com.bca.bsi.model.Product;

import java.util.ArrayList;
import java.util.List;

public class DummyData {

    private static final String[] title = {"Ashmore Dana Ekuitas Nusantara", "Ashmore Dana Progresif Nusantara", "Bahana Pendapatan Tetap Makara Prima"};
    private static final String[] type = {"Saham", "Saham", "Pendapatan Tetap"};
    private static final String[] date = {"16/12/20", "16/12/20", "16/12/20"};
    private static final double[] kinerja = {14.13, 14.89, 10.13};
    private static final double[] nab = {1462.81, 1575.39, 1672.98};

    private static final int[] learningChapterRDImage = {R.drawable.img_materi_reksadana_1,R.drawable.img_materi_reksadana_2,R.drawable.img_materi_reksadana_3,R.drawable.img_materi_kuis};
    private static final String[] learningChapterRDTitle = {"Reksa Dana", "Jenis Produk Reksa Dana", "Manfaat & Risiko Reksa Dana", "Kuis Interaktif"};
    private static final String[] learningChapterRDExplanation = {"Merupakan wadah yang dipergunakan untuk menghitung dana dari masyarakat pemodal " +
            "untuk selanjutnya diinvestasikan dalam portofolio Efek oleh Manajer Investasi", "Sangat Konservatif: \n \u25CF Reksa Dana Pasar Uang \n\n Konservatif: " +
            "\n \u25CF Reksa Dana Pendapatan Tetap \n \u25CF Reksa Dana Terproteksi \n Moderat: \n \u25CF Reksa Dana Campuran \n Agresif: \n \u25CF Reksa Dana Saham"};


    public static List<Product.ReksaDana> getReksaDanaDummyList() {
        List<Product.ReksaDana> reksaDanaList = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            Product.ReksaDana reksaDana = new Product.ReksaDana(title[i], type[i], date[i], kinerja[i], nab[i]);
            reksaDanaList.add(reksaDana);
        }
        return reksaDanaList;
    }

    public static List<LearningChapter> getLearningChapterRDList() {
        List<LearningChapter> rdChapterList = new ArrayList<>();
        for (int i = 0; i < learningChapterRDTitle.length; i++) {
            LearningChapter rdChapter = new LearningChapter(learningChapterRDImage[i],learningChapterRDTitle[i],learningChapterRDExplanation[i]);
            rdChapterList.add(rdChapter);
        }
        return rdChapterList;
    }

}

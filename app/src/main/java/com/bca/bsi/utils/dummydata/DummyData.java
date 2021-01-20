package com.bca.bsi.utils.dummydata;

import android.text.Html;

import com.bca.bsi.R;
import com.bca.bsi.model.FilterJenisReksa;
import com.bca.bsi.model.Forum;
import com.bca.bsi.model.KuisData;
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

    private static final int[] learningChapterRDImage = {R.drawable.img_materi_reksadana_1, R.drawable.img_materi_reksadana_2, R.drawable.img_materi_reksadana_3, R.drawable.img_materi_kuis};
    private static final String[] learningChapterRDTitle = {"Reksa Dana", "Jenis Produk Reksa Dana", "Manfaat & Risiko Reksa Dana", "Kuis Interaktif"};
    private static final String[] learningChapterRDExplanation = {"Merupakan wadah yang dipergunakan untuk menghitung dana dari masyarakat pemodal " +
            "untuk selanjutnya diinvestasikan dalam portofolio Efek oleh Manajer Investasi", "Sangat Konservatif: \n \u25CF Reksa Dana Pasar Uang \n\n Konservatif: " +
            "\n \u25CF Reksa Dana Pendapatan Tetap \n \u25CF Reksa Dana Terproteksi \n Moderat: \n \u25CF Reksa Dana Campuran \n Agresif: \n \u25CF Reksa Dana Saham"};

    private static final String[] kinerjaString = {"+14.13%", "+14.89%", "+10.13%"};
    private static final String[] nabString = {"1462.81", "1575.39", "1672.98"};
    private static final String[] percentage = {"70%", "20%", "10%"};
    private static final boolean[] isChoosen = {false, false, false};

    private static final String[] inboxUsername = {"User123", "Andrew Abednego Gunawan", "Andrew001"};
    private static final String[] inboxDate = {"22/12/2021 10:00", "23/12/2021 10:00", "24/12/2021 10:00"};
    private static final String[] inboxContent = {"Merupakan wadah yang dipergunakan untuk menghitung dana dari masyarakat pemodal", "Merupakan wadah yang dipergunakan untuk menghitung dana dari masyarakat pemodal ", "Merupakan wadah yang dipergunakan untuk menghitung dana dari masyarakat pemodal "};

    private static final String[] reportID = {"1", "2", "3", "4"};
    private static final String[] reportContent = {"Spam", "Hate Speech", "Advertisement", "Unrelevant"};

    private static final String[] percentage2 = {"10", "20", "30"};

    private static final String[] filterJenisReksaName = {"Pasar Uang", "Pendapatan Tetap", "Campuran", "Saham", "Terproteksi"};
    private static final boolean[] isChoosenFilter = {false, false, false, false, false};
    private static final boolean[] isChoosenFilterDefault = {true, true, true, true, true};

    private static final String[] sortJenisReksaName = {"Nama Produk", "Nama Produk", "Kinerja", "Kinerja"};
    private static final String[] sortTypeStart = {"A", "Z", "Tinggi", "Rendah"};
    private static final String[] sortTypeEnd = {"Z", "A", "Rendah", "Tinggi"};
    private static final boolean[] isChoosenSort = {true, false, false, false};
    private static final boolean[] isChoosenSortDefault = {false, false, false, false};

    private static final String[] imageProfile = {"https://qph.fs.quoracdn.net/main-qimg-40eacebc7bb9f75b867254ed81b45181", "https://upload.wikimedia.org/wikipedia/commons/5/52/Chaeyoung_at_Gaon_Awards_red_carpet_on_January_23%2C_2019.jpg", "https://media.matamata.com/thumbs/2020/10/18/92787-jeongyeon-twice-instagramattwicetagram/745x489-img-92787-jeongyeon-twice-instagramattwicetagram.jpg"};
    private static final String[] postProfileName = {"Chou Tzuyu", "Son Chaeyoung", "Yoo Jeongyeon"};
    private static final String[] postDate = {"18 January 2021 10:00", "16 January 2021 11:00", "15 January 2021 12:00"};
    private static final String[] postType = {"strategi", "news", "share trade"};
    private static final String[] postLike = {"15", "11", "10"};
    private static final String[] postStatusLike = {"true", "false", "false"};
    private static final String[] postComment = {"20", "10", "5"};
    private static final String[] postShare = {"30", "25", "22"};
    private static final String[] postID = {"1", "2", "3"};
    private static final String[] postProfileID = {"YCHRIS", "2", "3"};
    private static final String[] postContent = {"Secara historis, pengunjung suku kanibal sering kali terlihat sangat aman dan memang diperlakukan dengan baik. Seperti mafia, orang seperti itu biasanya hanya membunuh sendiri. Pada akhir abad ke-16 misalnya, penulis Prancis AndreThevet mengamati kanibalisme Brasil secara pribadi dan pulang tanpa cedera. Thevet menyaksikan baik kanibalisme perang yang kejam dan agresif dan apa yang sekarang dikenal sebagai kanibalisme konsensual atau penguburan. Orang-orang Tapuia, memakan kerabat mereka sendiri yang telah meninggal untuk mengampuni mereka dari penghinaan membusuk di bumi. Sekitar waktu yang sama, orang Prancis lainnya tinggal bersama Tupinamba di Brasil. Namun terlepas dari reputasi mereka sebagai beberapa kanibal paling kejam yang dikenal saat itu, ia juga hidup untuk menceritakan kisah para pelancongnya.", "Mungkin yang paling aneh dari semuanya adalah kisah Joseph Kabris, seorang pelaut Prancis yang datang untuk tinggal di antara suku kanibal di Nuku Hiva dari tahun 1796-1804. Setelahbeberapa ketakutan awal tentang dimakan, Kabris sebenarnya menjalani kehidupan barunya dengan semangat yang sedemikian rupa sehingga dia benar-benar menjadi pribumi. Dia ditato dengan gaya kesukuan, menikah dengan dua istri yang berbeda, dan bertempur dalam pertempuran dengan tuan rumah barunya. Pada waktunya dia seharusnya melupakan bahasa Prancis dan bahkan namanya sendiri. Kabris mengklaim selain saat kelaparan atau perang, penduduk pulau ini sangat lembut. Orang asing diperlakukan dengan sangat hormat dan dapat melakukan perjalanan melalui darat  dengan keamanan penuh.", "Memang, perang dan kelaparan di Nuku Hiva adalah masalah lain. Tentara angkat Kabris menceritakan tentang bagaimana, setelah pertempuran para tahanan dimakan. Mata, otak dan pipi dianggap sebagai makanan lezat,tengkorak dibelah, otak dan darah diminum, kemudian dikerok bersih. Kabris juga berbicara tentang bagaimana, selama kelaparan yang disebabkan oleh kekeringan penduduk pulau akan bertengkar di antara mereka sendiri, paling tidak perselisihan, dan memotong leher untuk menyediakan makanan. Di masa Kabris, penduduk pulau tampaknya tidak mempraktikkan kanibalisme pemakaman. Namun, seperti halnya suku Tapuia, kanibalisme internal ini jauh lebih umum daripada yang mungkin disarankan oleh banyak penulis perjalanan Barat. "};

    private static final String[] categoryID = {"1", "2", "3"};
    private static final String[] categoryName = {"Category 1", "Category 2", "Category 3"};

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
            LearningChapter rdChapter = new LearningChapter(learningChapterRDImage[i], learningChapterRDTitle[i], learningChapterRDExplanation[i]);
            rdChapterList.add(rdChapter);
        }
        return rdChapterList;
    }

    public static List<ProductRekomen> getProductRekomenList() {
        List<ProductRekomen> productRekomenList = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            ProductRekomen produk = new ProductRekomen(title[i], date[i], percentage[i], kinerjaString[i], nabString[i], type[i]);
            productRekomenList.add(produk);
        }
        return productRekomenList;
    }

    public static List<ProductRekomen> getProductRekomenListPurchase() {
        List<ProductRekomen> productRekomenList = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            ProductRekomen produk = new ProductRekomen(title[i], date[i], percentage2[i], kinerjaString[i], nabString[i], type[i]);
            productRekomenList.add(produk);
        }
        return productRekomenList;
    }

    public static List<ProductChoice> getProductChoiceList() {
        List<ProductChoice> productChoices = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            ProductChoice produk = new ProductChoice(title[i], type[i], date[i], nabString[i], kinerjaString[i], isChoosen[i]);
            productChoices.add(produk);
        }
        return productChoices;
    }

    public static List<LearningChapter> setMateriReksaDana() {
        List<LearningChapter> models = new ArrayList<>();
        models.add(new LearningChapter(R.drawable.img_materi_reksadana_1, "Reksa Dana", "Merupakan wadah yang dipergunakan untuk menghitung" +
                "dana dari masyarakat pemodal untuk selanjutnya diinvestasikan dalam portofolio Efek oleh Manajer Investasi"));
        models.add(new LearningChapter(R.drawable.img_materi_reksadana_2, "Jenis Produk Reksa Dana", "Sangat Konservatif: \n \u25CF Reksa Dana Pasar Uang" +
                "\n Konservatif: \n \u25CF Reksa Dana Pendapatan Tetap \n \u25CF Reksa Dana Terproteksi \n Moderat: \n \u25CF Reksa Dana Campuran \n Agresif: \n \u25CF " +
                "Reksa Dana Saham"));
        models.add(new LearningChapter(R.drawable.img_materi_reksadana_3, "Manfaat & Risiko Reksa Dana", "Manfaat Reksa Dana: \n 1. Pengelolaan yang " +
                "Profesional \n 2. Diversifikasi Investasi \n 3. Transparansi \n 4. Dana Investasi yang Terjangkau \n Risiko Reksa Dana: \n 1. Risiko Berkurangnya Unit " +
                "Penyertaan \n 2. Risiko Perubahan Kondisi Politik dan Ekonomi \n 3. Risiko Likuiditas \n 4. Risiko Nilai Tukar \n 5. Risiko Perubahan Peraturan dan " +
                "Ketentuan Pajak \n 6. Risiko Pembubaran dan Likuiditas \n\n Informasi Lebih Lanjut:"));
        models.add(new LearningChapter(R.drawable.img_materi_kuis, "Kuis Interaktif", "Asah kembali pengetahuanmu mengenai Reksa Dana dengan menjawab ulang pertanyaan kuis"));

        return models;
    }


    public static List<LearningChapter> setMateriObligasi() {
        List<LearningChapter> models = new ArrayList<>();
        models.add(new LearningChapter(R.drawable.img_materi_obligasi_1, "Obligasi", "Obligasi pemerintah adalah surat berharga dalam mata uang" +
                "Rupiah maupun valuta asing yang dijamin pembayaran Kupon/Imbalan dan pokoknya oleh Negara Republik Indonesia"));
        models.add(new LearningChapter(R.drawable.img_materi_obligasi_2, "Jenis Produk Obligasi", "Konservatif: \n \u25CF ORI/SR/SBR/ST" +
                "\n Moderat: \n \u25CF Obligasi FR/PBS \n \u25CF Obligasi Negara Valas (Indon) \n \u25CF Obligasi Negara Valas Syariah (Indois)"));
        models.add(new LearningChapter(R.drawable.img_materi_obligasi_3, "Manfaat & Risiko Reksa Dana", "Manfaat Obligasi \n1. Aman \n2. Kupon/Imbalan Kompetitif " +
                "\n3. Mudah & Likuid" + Html.fromHtml("<sup>*</sup>") + "\n4. Potensi Keuntungan (Capital Gain)" + Html.fromHtml("<sup>*</sup>") + "" +
                "\nRisiko Obligasi: \n1. Risiko Gagal Bayar \n2. Risiko Pasar \n3. Risiko Likuiditas\n\n" + Html.fromHtml("<sup>*</sup>") + Html.fromHtml("<font size=\"16\">) Khusus untuk obligasi pemerintah yang dapat diperdagangkan di Pasar Sekunder</font>") +
                "\n\n Informasi Lebih Lanjut:"));
        models.add(new LearningChapter(R.drawable.img_materi_kuis, "Kuis Interaktif", "Mari ikuti kuis untuk mengasah pemahaman Anda terkait Obligasi"));

        return models;
    }

    public static List<LearningChapter> setMateriAsuransi() {
        List<LearningChapter> models = new ArrayList<>();
        models.add(new LearningChapter(R.drawable.img_materi_asuransi_1, "Asuransi", Html.fromHtml("<font><B>a. Asuransi Jiwa</B></font>") + "\nPerlindungan atas risiko finansial" +
                "yang mungkin terjadi terhadap jiwa seseorang yang dipertanggungjawabkan apabila terjadi hal yang tidak diharapkan. \n\n" + Html.fromHtml("<font><B>a. Asuransi Umum</B></font>") + "" +
                "\nPerlindungan atas risiko finansial yang mungkin terjadi karena kerugian, kerusakan, atau kehilangan terhadap nilai suatu aset yang dipertanggungkan."));
        models.add(new LearningChapter(R.drawable.img_materi_asuransi_2, "Jenis Produk Asuransi", Html.fromHtml("<font><B>Asuransi Jiwa</B></font>") + "" +
                "\n \u25CF Asuransi Tradisional\n - Asuransi Jiwa Berjangka " + Html.fromHtml("<font><i>(Term Life)</i></font>") + "\nProduk asuransi jiwa yang memberikan perlindungan" +
                " jiwa selama periode tertentu; misalnya 1, 5, 10, 15, 20 tahun. \n\n- Asuransi Jiwa Seumur Hidup " + Html.fromHtml("<font><i>(Whole Life)</i></font>") + "\nProduk asuransi jiwa yang memberikan" +
                " perlindungan jiwa seumur hidup. \n\n- Asuransi Jiwa Dwiguna " + Html.fromHtml("<font><i>(Endowment)</i></font>") + "\nProduk asuransi yang" +
                " memberikan manfaat perlindungan jiwa dan memberikan nilai tunai (tabungan) yang dijamin pada akhir masa asuransi. \n\n \u25CF Asuransi Unit Link" +
                "\nProduk asuransi jiwa yang memberikan manfaat perlindungan jiwa sekaligus manfaat investasi.\n\n" + Html.fromHtml("<font><B>Asuransi Umum</B></font>") + "" +
                "\n \u25CF Asuransi Harta Benda (Property) \n \u25CF Asuransi Kendaraan Bermotor \n \u25CF Asuransi Perjalanan" + Html.fromHtml("<font><i>(Travel Insurance)</i></font>")));
        models.add(new LearningChapter(R.drawable.img_materi_asuransi_3, "Manfaat Asuransi", "a. Memberikan rasa aman dan perlindungan finansial bagi keluarga" +
                " terhadap risiko atas kejadian yang tidak diharapkan dan tidak dapat diduga \n\nb. Memberikan penggantian finansial pada saat ketidak mampuan " + Html.fromHtml("<font><i>(disability)</i></font>") + "" +
                " terjadi. \n\nc. Menjadi salah satu wujud perencanaan keuangan jangka panjang untuk memenuhi kebutuhan finansial hidup di masa yang akan datang.\n\n Informasi Lebih Lanjut:"));
        models.add(new LearningChapter(R.drawable.img_materi_kuis, "Kuis Interaktif", "Mari ikuti kuis untuk mengasah pemahaman Anda mengenai Asuransi"));

        return models;
    }


    public static KuisData setKuisDataDummy() {
        KuisData.DataJawaban jawabanOpsiASoal1 = new KuisData.DataJawaban("1", "a", "ini jawaban opsi a soal nomer 1");
        KuisData.DataJawaban jawabanOpsiASoal2 = new KuisData.DataJawaban("2", "a", "ini jawaban opsi a soal nomer 2");
        KuisData.DataJawaban jawabanOpsiASoal3 = new KuisData.DataJawaban("3", "a", "ini jawaban opsi a soal nomer 3");
        KuisData.DataJawaban jawabanOpsiASoal4 = new KuisData.DataJawaban("4", "a", "ini jawaban opsi a soal nomer 4");
        KuisData.DataJawaban jawabanOpsiASoal5 = new KuisData.DataJawaban("5", "a", "ini jawaban opsi a soal nomer 5");
        KuisData.DataJawaban jawabanOpsiBSoal1 = new KuisData.DataJawaban("6", "b", "ini jawaban opsi b soal nomer 1");
        KuisData.DataJawaban jawabanOpsiBSoal2 = new KuisData.DataJawaban("7", "b", "ini jawaban opsi b soal nomer 2");
        KuisData.DataJawaban jawabanOpsiBSoal3 = new KuisData.DataJawaban("8", "b", "ini jawaban opsi b soal nomer 3");
        KuisData.DataJawaban jawabanOpsiBSoal4 = new KuisData.DataJawaban("9", "b", "ini jawaban opsi b soal nomer 4");
        KuisData.DataJawaban jawabanOpsiBSoal5 = new KuisData.DataJawaban("10", "b", "ini jawaban opsi b soal nomer 5");
        KuisData.DataJawaban jawabanOpsiCSoal1 = new KuisData.DataJawaban("11", "c", "ini jawaban opsi c soal nomer 1");
        KuisData.DataJawaban jawabanOpsiCSoal2 = new KuisData.DataJawaban("12", "c", "ini jawaban opsi c soal nomer 2");
        KuisData.DataJawaban jawabanOpsiCSoal3 = new KuisData.DataJawaban("13", "c", "ini jawaban opsi c soal nomer 3");
        KuisData.DataJawaban jawabanOpsiCSoal4 = new KuisData.DataJawaban("14", "c", "ini jawaban opsi c soal nomer 4");
        KuisData.DataJawaban jawabanOpsiCSoal5 = new KuisData.DataJawaban("15", "c", "ini jawaban opsi c soal nomer 5");

        List<KuisData.DataJawaban> opsiSoal1 = new ArrayList<>();
        opsiSoal1.add(jawabanOpsiASoal1);
        opsiSoal1.add(jawabanOpsiBSoal1);
        opsiSoal1.add(jawabanOpsiCSoal1);

        List<KuisData.DataJawaban> opsiSoal2 = new ArrayList<>();
        opsiSoal2.add(jawabanOpsiASoal2);
        opsiSoal2.add(jawabanOpsiBSoal2);
        opsiSoal2.add(jawabanOpsiCSoal2);

        List<KuisData.DataJawaban> opsiSoal3 = new ArrayList<>();
        opsiSoal3.add(jawabanOpsiASoal3);
        opsiSoal3.add(jawabanOpsiBSoal3);
        opsiSoal3.add(jawabanOpsiCSoal3);

        List<KuisData.DataJawaban> opsiSoal4 = new ArrayList<>();
        opsiSoal4.add(jawabanOpsiASoal4);
        opsiSoal4.add(jawabanOpsiBSoal4);
        opsiSoal4.add(jawabanOpsiCSoal4);

        List<KuisData.DataJawaban> opsiSoal5 = new ArrayList<>();
        opsiSoal5.add(jawabanOpsiASoal5);
        opsiSoal5.add(jawabanOpsiBSoal5);
        opsiSoal5.add(jawabanOpsiCSoal5);

        KuisData.Quiz soal1 = new KuisData.Quiz("1", "apakah ini soal no 1?", opsiSoal1, jawabanOpsiASoal1, "exp soal 1 benar", "exp soal 1 salah");
        KuisData.Quiz soal2 = new KuisData.Quiz("2", "apakah ini soal no 2?", opsiSoal2, jawabanOpsiBSoal1, "exp soal 2 benar", "exp soal 2 salah");
        KuisData.Quiz soal3 = new KuisData.Quiz("3", "apakah ini soal no 3?", opsiSoal3, jawabanOpsiCSoal1, "exp soal 3 benar", "exp soal 3 salah");
        KuisData.Quiz soal4 = new KuisData.Quiz("4", "apakah ini soal no 4?", opsiSoal4, jawabanOpsiBSoal1, "exp soal 4 benar", "exp soal 4 salah");
        KuisData.Quiz soal5 = new KuisData.Quiz("5", "apakah ini soal no 5?", opsiSoal5, jawabanOpsiASoal1, "exp soal 5 benar", "exp soal 5 salah");

        List<KuisData.Quiz> pertanyaan = new ArrayList<>();
        pertanyaan.add(soal1);
        pertanyaan.add(soal2);
        pertanyaan.add(soal3);
        pertanyaan.add(soal4);
        pertanyaan.add(soal5);

        KuisData kuisData = new KuisData("1", pertanyaan);


        return kuisData;
    }

    public static List<Forum.Inbox> getInboxList() {
        List<Forum.Inbox> productChoices = new ArrayList<>();
        for (int i = 0; i < inboxUsername.length; i++) {
            Forum.Inbox inbox = new Forum.Inbox(inboxUsername[i], inboxDate[i], inboxContent[i]);
            productChoices.add(inbox);
        }
        return productChoices;
    }

    public static List<Forum.Report> getReportList() {
        List<Forum.Report> reportList = new ArrayList<>();
        for (int i = 0; i < reportID.length; i++) {
            Forum.Report report = new Forum.Report(reportID[i], reportContent[i], false);
            reportList.add(report);
        }
        return reportList;
    }

    public static List<FilterJenisReksa> getFilterJenisReksaList() {
        List<FilterJenisReksa> res = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FilterJenisReksa filterJenisReksa = new FilterJenisReksa(filterJenisReksaName[i], isChoosenFilter[i]);
            res.add(filterJenisReksa);
        }
        return res;
    }

    public static List<FilterJenisReksa> getFilterJenisReksaListDefault() {
        List<FilterJenisReksa> res = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FilterJenisReksa filterJenisReksa = new FilterJenisReksa(filterJenisReksaName[i], isChoosenFilterDefault[i]);
            res.add(filterJenisReksa);
        }
        return res;
    }

    public static List<SortJenisReksa> getSortJenisReksaList() {
        List<SortJenisReksa> res = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            SortJenisReksa sortJenisReksa = new SortJenisReksa(sortJenisReksaName[i], sortTypeStart[i], sortTypeEnd[i], isChoosenSort[i]);
            res.add(sortJenisReksa);
        }
        return res;
    }

    public static List<SortJenisReksa> getSortJenisReksaListFalse() {
        List<SortJenisReksa> res = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            SortJenisReksa sortJenisReksa = new SortJenisReksa(sortJenisReksaName[i], sortTypeStart[i], sortTypeEnd[i], isChoosenSortDefault[i]);
            res.add(sortJenisReksa);
        }
        return res;
    }

    public static List<Forum.Post> getPostList() {
        List<Forum.Post> postList = new ArrayList<>();
        for (int i = 0; i < postComment.length; i++) {
            Forum.Post post = new Forum.Post(postID[i], imageProfile[i], postProfileName[i], postDate[i], postType[i], postContent[i], "", postLike[i], postComment[i], postShare[i], postStatusLike[i], postProfileID[i]);
            postList.add(post);
        }
        return postList;
    }

    public static List<Forum.Category> getCategoryList() {
        List<Forum.Category> categoryList = new ArrayList<>();
        for (int i = 0; i < categoryID.length; i++) {
            Forum.Category category = new Forum.Category(categoryID[i], categoryName[i], false);
            categoryList.add(category);
        }
        return categoryList;
    }
}

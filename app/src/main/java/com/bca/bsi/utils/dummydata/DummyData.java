package com.bca.bsi.utils.dummydata;

import android.text.Html;

import com.bca.bsi.R;
import com.bca.bsi.model.FilterJenisReksa;
import com.bca.bsi.model.Forum;
import com.bca.bsi.model.KuisData;
import com.bca.bsi.model.LearningChapter;
import com.bca.bsi.model.Product;
import com.bca.bsi.model.ProductChoice;
import com.bca.bsi.model.ProductIH;
import com.bca.bsi.model.ProductRekomen;
import com.bca.bsi.model.PromoNews;
import com.bca.bsi.model.SortJenisReksa;
import com.bca.bsi.model.TipsOfTheWeek;
import com.bca.bsi.model.User;

import java.util.ArrayList;
import java.util.List;

public class DummyData {

    private static final String[] ID = {"ID01", "ID02", "ID03"};
    private static final String[] title = {"Ashmore Dana Ekuitas Nusantara", "Ashmore Dana Progresif Nusantara", "Bahana Pendapatan Tetap Makara Prima"};
    private static final String[] type = {"Saham", "Saham", "Pendapatan Tetap"};
    private static final String[] date = {"16/12/20", "16/12/20", "16/12/20"};
    private static final String[] promoTitle = {"Cashback hingga Rp300 ribu di Promo Leave Contact Asuransi via Welma", "Investasi ST007 untuk Bumi, Dapatkan cashback Rp70 Ribu!", "Investasi ST007 untuk Bumi, Dapatkan cashback Rp70 Ribu!"};
    private static final String[] promoImage = {"https://nos.jkt-1.neo.id/mcdonalds/promos/November2020/tCaLR1RWEPfD0rYQT8QB.jpg", "https://cbn.id/images/uploads/2018/august/img-bca.jpg", "https://ik.imagekit.io/tvlk/blog/2020/10/fields-FrontApp_PromoFrontImage_a.jpg?tr=dpr-1,w-675"};
    private static final String[] promoDescription = {"#PasangKudaKuda tanpa keluar rumah, dapat cashback hingga Rp300 ribu", "#PasangKudaKuda tanpa keluar rumah, dapat cashback hingga Rp300 ribu", "#PasangKudaKuda tanpa keluar rumah, dapat cashback hingga Rp300 ribu"};
    private static final String[] content = {"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"};

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
    private static final String[] percentage = {"70", "20", "10"};
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

    private static final String[] imageProfile = {"https://qph.fs.quoracdn.net/main-qimg-40eacebc7bb9f75b867254ed81b45181", "https://upload.wikimedia.org/wikipedia/commons/5/52/Chaeyoung_at_Gaon_Awards_red_carpet_on_January_23%2C_2019.jpg", "https://media.matamata.com/thumbs/2020/10/18/92787-jeongyeon-twice-instagramattwicetagram/745x489-img-92787-jeongyeon-twice-instagramattwicetagram.jpg", "https://www.wowkeren.com/images/news/00148900.jpg"};
    private static final String[] postProfileName = {"Chou Tzuyu", "Son Chaeyoung", "Yoo Jeongyeon", "Im Nayeon"};
    private static final String[] postDate = {"18 January 2021 10:00", "16 January 2021 11:00", "15 January 2021 12:00", "12 January 2021 12:00"};
    private static final String[] postType = {"news", "news", "news", "news"};
    private static final String[] postLike = {"15", "11", "10", "12"};
    private static final String[] postStatusLike = {"true", "false", "false", "true"};
    private static final String[] postComment = {"20", "10", "5", "20"};
    private static final String[] postShare = {"30", "25", "22", "1"};
    private static final String[] postID = {"1", "2", "3", "4"};
    private static final String[] postProfileID = {"YCHRIS", "2", "3", "4"};
    private static final String[] postContent = {"Secara historis, pengunjung suku kanibal sering kali terlihat sangat aman dan memang diperlakukan dengan baik. Seperti mafia, orang seperti itu biasanya hanya membunuh sendiri. Pada akhir abad ke-16 misalnya, penulis Prancis AndreThevet mengamati kanibalisme Brasil secara pribadi dan pulang tanpa cedera. Thevet menyaksikan baik kanibalisme perang yang kejam dan agresif dan apa yang sekarang dikenal sebagai kanibalisme konsensual atau penguburan. Orang-orang Tapuia, memakan kerabat mereka sendiri yang telah meninggal untuk mengampuni mereka dari penghinaan membusuk di bumi. Sekitar waktu yang sama, orang Prancis lainnya tinggal bersama Tupinamba di Brasil. Namun terlepas dari reputasi mereka sebagai beberapa kanibal paling kejam yang dikenal saat itu, ia juga hidup untuk menceritakan kisah para pelancongnya.", "Secara historis, pengunjung suku kanibal sering kali terlihat sangat aman dan memang diperlakukan dengan baik. Seperti mafia, orang seperti itu biasanya hanya membunuh sendiri. Pada akhir abad ke-16 misalnya, penulis Prancis AndreThevet mengamati kanibalisme Brasil secara pribadi dan pulang tanpa cedera. Thevet menyaksikan baik kanibalisme perang yang kejam dan agresif dan apa yang sekarang dikenal sebagai kanibalisme konsensual atau penguburan. Orang-orang Tapuia, memakan kerabat mereka sendiri yang telah meninggal untuk mengampuni mereka dari penghinaan membusuk di bumi. Sekitar waktu yang sama, orang Prancis lainnya tinggal bersama Tupinamba di Brasil. Namun terlepas dari reputasi mereka sebagai beberapa kanibal paling kejam yang dikenal saat itu, ia juga hidup untuk menceritakan kisah para pelancongnya.", "Mungkin yang paling aneh dari semuanya adalah kisah Joseph Kabris, seorang pelaut Prancis yang datang untuk tinggal di antara suku kanibal di Nuku Hiva dari tahun 1796-1804. Setelahbeberapa ketakutan awal tentang dimakan, Kabris sebenarnya menjalani kehidupan barunya dengan semangat yang sedemikian rupa sehingga dia benar-benar menjadi pribumi. Dia ditato dengan gaya kesukuan, menikah dengan dua istri yang berbeda, dan bertempur dalam pertempuran dengan tuan rumah barunya. Pada waktunya dia seharusnya melupakan bahasa Prancis dan bahkan namanya sendiri. Kabris mengklaim selain saat kelaparan atau perang, penduduk pulau ini sangat lembut. Orang asing diperlakukan dengan sangat hormat dan dapat melakukan perjalanan melalui darat  dengan keamanan penuh.", "Memang, perang dan kelaparan di Nuku Hiva adalah masalah lain. Tentara angkat Kabris menceritakan tentang bagaimana, setelah pertempuran para tahanan dimakan. Mata, otak dan pipi dianggap sebagai makanan lezat,tengkorak dibelah, otak dan darah diminum, kemudian dikerok bersih. Kabris juga berbicara tentang bagaimana, selama kelaparan yang disebabkan oleh kekeringan penduduk pulau akan bertengkar di antara mereka sendiri, paling tidak perselisihan, dan memotong leher untuk menyediakan makanan. Di masa Kabris, penduduk pulau tampaknya tidak mempraktikkan kanibalisme pemakaman. Namun, seperti halnya suku Tapuia, kanibalisme internal ini jauh lebih umum daripada yang mungkin disarankan oleh banyak penulis perjalanan Barat. "};

    private static final String[] categoryID = {"1", "2", "3"};
    private static final String[] categoryName = {"Category 1", "Category 2", "Category 3"};

    private static final String[] ihReksaName = {"Bahana Pendapatan Tetap Makara Prima", "Ashmore lalala", "Batavia itu Jakarta"};
    private static final String[] infJenisReksa = {"Pendapatan Tetap", "Saham", "Terproteksi"};
    private static final String[] ihDate = {"10/12/2020", "11/11/2021", "02/02/2020"};
    private static final String[] ihCost = {"200.000", "100.000", "300.000"};
    private static final String[] ihUnit = {"123123", "20,44", "2323"};
    private static final String[] hisJenisTransaksi = {"Pembelian", "Pembelian", "Penjualan"};
    private static final String[] iRaise = {"+10.000,00", "-5.000,00", "+2.000,00"};

    public static List<ProductIH> getProductsInformation() {
        List<ProductIH> res = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ProductIH prod = new ProductIH(ihReksaName[i], infJenisReksa[i], ihUnit[i], ihCost[i], ihDate[i]);
            prod.setRaise(iRaise[i]);
            res.add(prod);
        }
        return res;
    }

    public static List<ProductIH> getProductsHistory() {
        List<ProductIH> res = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ProductIH prod = new ProductIH(ihReksaName[i], hisJenisTransaksi[i], ihUnit[i], ihCost[i], ihDate[i]);
            res.add(prod);
        }
        return res;
    }


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
            PromoNews promoNews = new PromoNews(i + "", promoTitle[i], promoDescription[i], promoImage[i], content[i], date[i]);
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

    public static List<Forum.Post> getPostNewsList() {
        List<Forum.Post> postList = new ArrayList<>();
        for (int i = 0; i < getPromoNewsList().size(); i++) {
            Forum.Post post = new Forum.Post(postID[i], imageProfile[i], postProfileName[i], postDate[i], postType[i], postContent[i], "", postLike[i], postComment[i], postShare[i], postStatusLike[i], postProfileID[i], getPromoNewsList().get(i));
            postList.add(post);
        }
        return postList;
    }

    private static final String[] shareTradePostID = {"1", "2", "3", "4"};
    private static final String[] shareTradeImageProfile = {"https://qph.fs.quoracdn.net/main-qimg-40eacebc7bb9f75b867254ed81b45181", "https://upload.wikimedia.org/wikipedia/commons/5/52/Chaeyoung_at_Gaon_Awards_red_carpet_on_January_23%2C_2019.jpg", "https://media.matamata.com/thumbs/2020/10/18/92787-jeongyeon-twice-instagramattwicetagram/745x489-img-92787-jeongyeon-twice-instagramattwicetagram.jpg", "https://www.wowkeren.com/images/news/00148900.jpg"};
    private static final String[] shareTradePostProfileName = {"Chou Tzuyu", "Son Chaeyoung", "Yoo Jeongyeon", "Im Nayeon"};
    private static final String[] shareTradePostDate = {"18 January 2021 10:00", "16 January 2021 11:00", "15 January 2021 12:00", "12 January 2021 12:00"};
    private static final String[] shareTradePostType = {"share trade", "share trade", "share trade", "share trade"};
    private static final String[] shareTradePostLike = {"15", "11", "10", "12"};
    private static final String[] shareTradePostStatusLike = {"true", "false", "false", "true"};
    private static final String[] shareTradePostComment = {"20", "10", "5", "20"};
    private static final String[] shareTradePostContent = {"Aku tadi ngejual karena bosan, mau coba yang lain ah...", "Aku tadi beli karena menurut orang kepercayaanku, produk ini mau naik loh hihihi", "Aku invest di siniii, terus naik doooong. Jadi cuaaan", "Hmmmm sekarang sabar duluuu, yakin naik lagi deeeh"};

    private static final String[] shareTradeID = {"1", "2", "3", "4"};
    private static final String[] shareTradeTitle = {"Saya baru saja menjual", "Saya baru saja membeli", "Nilai Investasi Saya", "Nilai Investasi Saya"};
    private static final String[] shareTradeProduct = {"Bahana Pendapatan tetap Makara Prima", "Ashmore Dana Ekuitas Nusantara", "Ashmore Dana Progresif Nusantara", "Batavia Dana Kas Maxima"};
    private static final String[] shareTradeValue = {"2510", "3220", "1.4", "-2.0"};
    private static final String[] shareTradeType = {"Jual", "Beli", "Naik", "Turun"};
    private static final String[] shareTradeDate = {"8 Januari 2019", "20 Januari 2020", "21 Februari 2020", "29 Desember 2020"};

    public static List<Forum.Post> getPostShareTradeList() {
        List<Forum.Post> postList = new ArrayList<>();
        for (int i = 0; i < shareTradeID.length; i++) {
            Forum.ShareTrade shareTrade = new Forum.ShareTrade(shareTradeID[i], shareTradeTitle[i], shareTradeType[i], shareTradeValue[i], shareTradeProduct[i], shareTradeDate[i]);
            Forum.Post post = new Forum.Post(shareTradePostID[i], shareTradeImageProfile[i], shareTradePostProfileName[i], shareTradePostDate[i], shareTradePostType[i], shareTradePostContent[i], "", shareTradePostLike[i], shareTradePostComment[i], postShare[i], shareTradePostStatusLike[i], postProfileID[i], shareTrade);
            postList.add(post);
        }
        return postList;
    }

    private static final String[] strategyPostID = {"1", "2", "3", "4"};
    private static final String[] strategyImageProfile = {"https://qph.fs.quoracdn.net/main-qimg-40eacebc7bb9f75b867254ed81b45181", "https://upload.wikimedia.org/wikipedia/commons/5/52/Chaeyoung_at_Gaon_Awards_red_carpet_on_January_23%2C_2019.jpg", "https://media.matamata.com/thumbs/2020/10/18/92787-jeongyeon-twice-instagramattwicetagram/745x489-img-92787-jeongyeon-twice-instagramattwicetagram.jpg", "https://www.wowkeren.com/images/news/00148900.jpg"};
    private static final String[] strategyPostProfileName = {"Chou Tzuyu", "Son Chaeyoung", "Yoo Jeongyeon", "Im Nayeon"};
    private static final String[] strategyPostDate = {"18 January 2021 10:00", "16 January 2021 11:00", "15 January 2021 12:00", "12 January 2021 12:00"};
    private static final String[] strategyPostType = {"strategy", "strategy", "strategy", "strategy"};
    private static final String[] strategyPostLike = {"15", "11", "10", "12"};
    private static final String[] strategyPostStatusLike = {"true", "false", "false", "true"};
    private static final String[] strategyPostComment = {"20", "10", "5", "20"};
    private static final String[] strategyPostContent = {"Wabah virus corona menghantui perekonomian global, termasuk kurs dollar Australia. Lalu, bagaimana nasib dari kurs dollar Australia ini? Untuk menjawabnya, simak ulasan berikut ini.\nNilai tukar rupiah terpantau menguat tajam dalam upaya melawan dollar Australia di awal perdagangan pada akhir pekan ini. Namun menjelang penutupan, penguatan terus terjadi dan kurs dollar Australia semakin tergerus.\n" +
            "\n" +
            "Pada awal perdagangan, nilai tukar rupiah sempat menguat 1,45 persen yakni pada level Rp9.726 per dollar Australia. Namun demikian, penguatan mata uang terus terpangkas sampai dengan berbalik melemah 0,19 persen yakni pada level Rp9.887 per dollar Australia tengah sesi perdagangan.\n" +
            "\n" +
            "Pergerakan ini juga sama yang mana terjadi pada pekan lalu dimana rupiah terpantau pada awal perdagangan melesat menguat bahkan hingga nyaris menuju penguatan 3 persen, tetapi pada akhir perdagangan justru rupiah malah berbalik melemah 0,7 persen.\n" +
            "\n" +
            "Rupiah saat ini berada pada dekat level terlemah selama 9 bulan yakni Rp9.988 per dollar Australia atau setelah sempat amblas pada lebih dari 10 persen dalam sepekan terakhir. Padahal hari Rabu pekan lalu, rupiah sempat bertengger dan berada pada level terkuat sejak Desember 2011 dalam melawan kurs dollar Australia.", "Investasi adalah satu dari sekian banyak cara dalam memperoleh tambahan uang. Setiap orang yang melakukan investasi tentunya menginginkan keuntungan. Namun, bagi kamu yang sangat baru dalam dunia investasi dan belum berpengalaman, pastinya ada keraguan dan ketakutan akan terjadinya kerugian.\n" +
            "\n" +
            "Di sisi lain, dengan investasi kamu akan mendapatkan penghasilan tanpa perlu bekerja dan menghabisakan waktu mengerjakan sesuatu tanpa bersusah payah. Dalam investasi, uang yang kamu tanamkan akan secara otomatis bekerja untuk kamu dan mendapatkan keuntungan alias passive income.\n" +
            "\n" +
            "Adapun beberapa tips yang bisa kamu lakukan untuk mendapatkan keuntungan dalam berinvestasi, antara lain:\n" +
            "\n" +
            "    Menentukan tujuan kamu dalam melakukan investasi.\n" +
            "    Mencari tahu apa itu investasi dan menetapkan tujuan kamu.\n" +
            "    Mulai membuat rencana untuk makukan pelunasan utang.\n" +
            "\n" +
            "Investasi adalah sebuah wadah yang bisa dilakukan untuk mendapatkan penghasilan tambahan. Semua orang tentunya melakukan investasi dan berkeinginan memperoleh keuntungan dari investasi tersebut.", "PT Astra International Tbk (ASII) pada tahun 2019 berhasil mengumpulkan keuntungan senilai Rp21,7 triliun. Laba bersih perseroan tersebut tak jauh berbeda dengan posisi tahun 2018 yang meraih keuntungan Rp21,67 triliun. \n" +
            "\n" +
            "Kinerja keuangan Astra International kembali terjadi penurunan pada tahun 2020 dikarenakan adanya penurunan volume penjualan mobil sepanjang Januari dan Februari 2019. Padahal pada tahun 2019 kinerja keuangan Astra Internasional sempat pulih karena didukung pulihnya penjualan mobil nasional tahun 2019 dan realisasi volume penjualan mobil perseroan sepanjang tahun lalu sudah sesuai ekspektasi. Astra International berusaha mempertahankan pencapaiannya tersebut agar terealisasi lagi di tahun 2020 ini, namun kenyataannya tidak semudah itu. Meski demikian, penurunan dan pelemahan tersebut tidak akan menggoyahkan perkiraan peningkatan volume penjualan mobil perseroan hingga akhir tahun 2020.\n" +
            "\n" +
            "Sepanjang pembukaan tahun 2020 yaitu bulan Januari, penjualan mobil turun sebesar 2,4% menjadi 79.983 unit dibandingkan periode Januari 2018. Penurunan tersebut diperkirakan akibat banjir yang melanda sebagian besar wilayah ibu kota awal tahun ini. Lain halnya jika dilihat dari sudut pangsa pasar, Astra International justru mampu untuk menaikkan pangsa pasarnya menjadi 50,4% pada Januari 2020 dibandingkan dengan bulan Desember 2019 yang sebesar 47,4%. Meski naik dari bulan sebelumnya, jika dilihat dari sepanjang tahun 2019 pangsa pasar Astra International tetap turun karena sepanjang tahun 2019 pangsa pasarnya sebesar 51,5%.", "Kamu bertanya-tanya cara investasi bagi mahasiswa yang tepat? Tenang, redaksi Ajaib akan menjawabnya secara lengkap melalui ulasan berikut ini. Disimak ya!\n" +
            "\n" +
            "Mengenyam pendidikan sekaligus bekerja bukanlah sesuatu yang mudah. Mengingat banyaknya kebutuhan yang harus dipenuhi demi menunjang kebutuhan perkuliahan tidak aneh rasanya apabila sebagai mahasiswa pun sudah harus memikirkan investasi. Memang tidak mudah tetapi sebenarnya hal ini pun tidak sulit untuk dilakukan. Banyak beberapa cara bagi mahasiswa untuk tetap dapat berinvestasi meskipun masih mengikuti kegiatan pembelajaran.\n" +
            "\n" +
            "Selain banyaknya pilihan untuk berinvestasi, perkembangan teknologi yang canggih juga memungkinkan mahasiswa untuk memiliki pendapatan tambahan melalui pekerjaan sampingan yang disediakan oleh internet. Mulai dari penulis lepas hingga reseller bisa dilakukan demi pemasukan tambahan. Namun pada artikel kali ini, kita akan membahas lebih lanjut mengenai cara investasi bagi mahasiswa. Tidak perlu melakukan pekerjaan tersebut, cukup dengan berinvestasi kamu bisa langsung melipatgandakan asetmu. Hal ini sangat cocok untuk para milenial yang suka dengan sesuatu yang praktis."};

    public static List<Forum.Post.ImagePost> getPostImageList(int i) {
        List<Forum.Post.ImagePost> imagePostList = new ArrayList<>();
        for (int j = 0; j <= i; j++) {
            imagePostList.add(new Forum.Post.ImagePost(i + "", strategyImageProfile[j]));
        }
        return imagePostList;
    }

    public static List<Forum.Post> getPostStrategyList() {
        List<Forum.Post> postList = new ArrayList<>();
        for (int i = 0; i < shareTradeID.length; i++) {
            Forum.Post post = new Forum.Post(strategyPostID[i], strategyImageProfile[i], strategyPostProfileName[i], strategyPostDate[i], strategyPostType[i], strategyPostContent[i], "", strategyPostLike[i], strategyPostComment[i], postShare[i], strategyPostStatusLike[i], postProfileID[i], getPostImageList(i));
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

    public static TipsOfTheWeek getTipsOfTheWeek() {
        return new TipsOfTheWeek("Yuk belajar analisa sahammu!", "Berikut informasi yang kamu perlu baca untuk pemilihan sahammu:\n" +
                "1. Laporan Keuangan\n" +
                "2. Laporan Tahunan\n" +
                "3. Corporate Action\n" +
                "4. Public Expose\n" +
                "Informasi di atas bisa dibaca secara bebas dan gratis loh!", "https://awsimages.detik.net.id/community/media/visual/2019/09/23/23c082cf-81e3-41d5-b8ad-bb5b6101de77_43.jpeg?w=700&q=90");
    }

    private static final String[] directImageProfile = {"https://qph.fs.quoracdn.net/main-qimg-40eacebc7bb9f75b867254ed81b45181"
            , "https://upload.wikimedia.org/wikipedia/commons/5/52/Chaeyoung_at_Gaon_Awards_red_carpet_on_January_23%2C_2019.jpg"
            , "https://media.matamata.com/thumbs/2020/10/18/92787-jeongyeon-twice-instagramattwicetagram/745x489-img-92787-jeongyeon-twice-instagramattwicetagram.jpg"
            , "https://www.wowkeren.com/images/news/00148900.jpg"
            , "https://i.pinimg.com/originals/fd/66/48/fd6648d97f920447576363607cc833bd.jpg"
            , "https://cdn-2.tstatic.net/manado/foto/bank/images/mina-twice-1d.jpg"
            , "https://cdn.idntimes.com/content-images/community/2020/12/els1vqvxuae4jyc-edbe2f50e2e7e76a907f34c405aea3f2-5e503a9df8032e880d6025b6317ae761_600x400.jpg"
            , "https://akcdn.detik.net.id/visual/2019/08/05/72446715-5a3a-47ef-b21e-1f27a998204f_169.jpeg?w=650"
            , "https://i.pinimg.com/originals/25/b8/5b/25b85b7820d5ee63f379438511d80c8d.jpg"};
    private static final String[] directProfileName = {"Chou Tzuyu", "Son Chaeyoung", "Yoo Jeongyeon"
            , "Im Nayeon", "Kim Dahyun", "Myoi Mina", "Minatozaki Sana", "Park Jihyo","Hirai Momo"};

    public static List<User.ForumUser> getForumUser() {
        List<User.ForumUser> forumUserList = new ArrayList<>();
        for (int i = 0; i < directImageProfile.length; i++) {
            User.ForumUser forumUser = new User.ForumUser(i + "", directProfileName[i], directImageProfile[i]);
            forumUserList.add(forumUser);
        }
        return forumUserList;
    }

}

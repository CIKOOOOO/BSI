package com.bca.bsi.ui.basenavigation.more.learningmenu.materi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bca.bsi.R;
import com.bca.bsi.adapter.LearningMateriAdapter;
import com.bca.bsi.model.LearningChapter;
import com.bca.bsi.ui.basenavigation.more.learningmenu.quiz.KuisAsuransiActivity;
import com.bca.bsi.ui.basenavigation.more.learningmenu.quiz.KuisReksaDanaActivity;
import com.bca.bsi.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MateriAsuransiActivity extends BaseActivity implements View.OnClickListener, LearningMateriAdapter.onItemClick {

    private ImageButton backBtn;
    private TextView titlePage;

    private List<LearningChapter> models;
    private ViewPager viewPager;
    private LearningMateriAdapter adapter;
    private Button button;
    private ImageView pagination;

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_asuransi);

        titlePage = findViewById(R.id.tv_title_toolbar_back);
        backBtn = findViewById(R.id.img_btn_back_toolbar);
        pagination = findViewById(R.id.pagination);

        titlePage.setText(getString(R.string.asuransi_capslock));
        backBtn.setOnClickListener(this);

        models = new ArrayList<>();
        models.add(new LearningChapter(R.drawable.img_materi_asuransi_1,"Asuransi",Html.fromHtml("<font><B>a. Asuransi Jiwa</B></font>")+"\nPerlindungan atas risiko finansial" +
                "yang mungkin terjadi terhadap jiwa seseorang yang dipertanggungjawabkan apabila terjadi hal yang tidak diharapkan. \n\n"+Html.fromHtml("<font><B>a. Asuransi Umum</B></font>")+"" +
                "\nPerlindungan atas risiko finansial yang mungkin terjadi karena kerugian, kerusakan, atau kehilangan terhadap nilai suatu aset yang dipertanggungkan."));
        models.add(new LearningChapter(R.drawable.img_materi_asuransi_2,"Jenis Produk Asuransi",Html.fromHtml("<font><B>Asuransi Jiwa</B></font>")+"" +
                "\n \u25CF Asuransi Tradisional\n - Asuransi Jiwa Berjangka "+Html.fromHtml("<font><i>(Term Life)</i></font>")+"\nProduk asuransi jiwa yang memberikan perlindungan" +
                " jiwa selama periode tertentu; misalnya 1, 5, 10, 15, 20 tahun. \n\n- Asuransi Jiwa Seumur Hidup "+Html.fromHtml("<font><i>(Whole Life)</i></font>")+"\nProduk asuransi jiwa yang memberikan" +
                " perlindungan jiwa seumur hidup. \n\n- Asuransi Jiwa Dwiguna "+Html.fromHtml("<font><i>(Endowment)</i></font>")+"\nProduk asuransi yang" +
                " memberikan manfaat perlindungan jiwa dan memberikan nilai tunai (tabungan) yang dijamin pada akhir masa asuransi. \n\n \u25CF Asuransi Unit Link" +
                "\nProduk asuransi jiwa yang memberikan manfaat perlindungan jiwa sekaligus manfaat investasi.\n\n"+Html.fromHtml("<font><B>Asuransi Umum</B></font>")+"" +
                "\n \u25CF Asuransi Harta Benda (Property) \n \u25CF Asuransi Kendaraan Bermotor \n \u25CF Asuransi Perjalanan"+Html.fromHtml("<font><i>(Travel Insurance)</i></font>")));
        models.add(new LearningChapter(R.drawable.img_materi_asuransi_3,"Manfaat Asuransi","a. Memberikan rasa aman dan perlindungan finansial bagi keluarga" +
                " terhadap risiko atas kejadian yang tidak diharapkan dan tidak dapat diduga \n\nb. Memberikan penggantian finansial pada saat ketidak mampuan "+Html.fromHtml("<font><i>(disability)</i></font>")+"" +
                " terjadi. \n\nc. Menjadi salah satu wujud perencanaan keuangan jangka panjang untuk memenuhi kebutuhan finansial hidup di masa yang akan datang.\n\n Informasi Lebih Lanjut:"));
        models.add(new LearningChapter(R.drawable.img_materi_kuis,"Kuis Interaktif","Mari ikuti kuis untuk mengasah pemahaman Anda terkait Asuransi"));

        adapter = new LearningMateriAdapter(models,this, this);

        viewPager = findViewById(R.id.viewPagerAsuransiMateri);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(50,0,50, 0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                switch (position){
                    case 0:
                        pagination.setImageResource(R.drawable.asset_pagination_1_4);
                        break;
                    case 1:
                        pagination.setImageResource(R.drawable.asset_pagination_2_4);
                        break;
                    case 2:
                        pagination.setImageResource(R.drawable.asset_pagination_3_4);
                        break;
                    case 3:
                        pagination.setImageResource(R.drawable.asset_pagination_4_4);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onClick() {
        switch (currentPage){
            case 2 :
                Log.e("asd","hit");
                Intent intentBrowse = new Intent();
                intentBrowse.setAction(Intent.ACTION_VIEW);
                intentBrowse.addCategory(Intent.CATEGORY_BROWSABLE);
                intentBrowse.setData(Uri.parse("https://www.bca.co.id/id/Individu/Produk/Investasi-dan-Asuransi/Bancassurance"));
                startActivity(intentBrowse);
                break;

            case 3:
                Intent intent = new Intent(this, KuisAsuransiActivity.class);
                startActivity(intent);
                break;
        }
    }
}
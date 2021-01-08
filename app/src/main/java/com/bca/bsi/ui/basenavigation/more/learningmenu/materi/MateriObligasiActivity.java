package com.bca.bsi.ui.basenavigation.more.learningmenu.materi;

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
import com.bca.bsi.ui.basenavigation.more.learningmenu.quiz.KuisObligasiActivity;
import com.bca.bsi.ui.basenavigation.more.learningmenu.quiz.KuisReksaDanaActivity;
import com.bca.bsi.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MateriObligasiActivity extends BaseActivity implements View.OnClickListener, LearningMateriAdapter.onItemClick {

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
        setContentView(R.layout.activity_materi_obligasi);

        titlePage = findViewById(R.id.tv_title_toolbar_back);
        backBtn = findViewById(R.id.img_btn_back_toolbar);
        pagination = findViewById(R.id.pagination);

        titlePage.setText(getString(R.string.obligasi_capslock));
        backBtn.setOnClickListener(this);

        models = new ArrayList<>();
        models.add(new LearningChapter(R.drawable.img_materi_obligasi_1,"Obligasi","Obligasi pemerintah adalah surat berharga dalam mata uang" +
                "Rupiah maupun valuta asing yang dijamin pembayaran Kupon/Imbalan dan pokoknya oleh Negara Republik Indonesia"));
        models.add(new LearningChapter(R.drawable.img_materi_obligasi_2,"Jenis Produk Obligasi","Konservatif: \n \u25CF ORI/SR/SBR/ST" +
                "\n Moderat: \n \u25CF Obligasi FR/PBS \n \u25CF Obligasi Negara Valas (Indon) \n \u25CF Obligasi Negara Valas Syariah (Indois)"));
        models.add(new LearningChapter(R.drawable.img_materi_obligasi_3,"Manfaat & Risiko Reksa Dana","Manfaat Obligasi \n1. Aman \n2. Kupon/Imbalan Kompetitif " +
                "\n3. Mudah & Likuid"+ Html.fromHtml("<sup>*</sup>") +"\n4. Potensi Keuntungan (Capital Gain)"+ Html.fromHtml("<sup>*</sup>") +"" +
                "\nRisiko Obligasi: \n1. Risiko Gagal Bayar \n2. Risiko Pasar \n3. Risiko Likuiditas\n\n"+Html.fromHtml("<sup>*</sup>")+Html.fromHtml("<font size=\"16\">) Khusus untuk obligasi pemerintah yang dapat diperdagangkan di Pasar Sekunder</font>")+
                "\n\n Informasi Lebih Lanjut:"));
        models.add(new LearningChapter(R.drawable.img_materi_kuis,"Kuis Interaktif","Mari ikuti kuis untuk mengasah pemahaman Anda terkait Obligasi"));

        adapter = new LearningMateriAdapter(models,this, this);

        viewPager = findViewById(R.id.viewPagerObligasiMateri);
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
                Intent intentBrowse = new Intent();
                intentBrowse.setAction(Intent.ACTION_VIEW);
                intentBrowse.addCategory(Intent.CATEGORY_BROWSABLE);
                intentBrowse.setData(Uri.parse("https://www.bca.co.id/id/Individu/Produk/Investasi-dan-Asuransi/Obligasi"));
                startActivity(intentBrowse);
                break;

            case 3:
                Intent intent = new Intent(this, KuisObligasiActivity.class);
                intent.putExtra("topic","obligasi");
                startActivity(intent);
                break;
        }

    }
}
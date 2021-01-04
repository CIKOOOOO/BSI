package com.bca.bsi.ui.basenavigation.more.learningmenu.materi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.bca.bsi.R;
import com.bca.bsi.adapter.LearningMateriAdapter;
import com.bca.bsi.model.LearningChapter;
import com.bca.bsi.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MateriReksaDanaActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton backBtn;
    private TextView titlePage;

    private List<LearningChapter> models;
    private ViewPager viewPager;
    private LearningMateriAdapter adapter;
    private Button button;
    private ImageView pagination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_reksa_dana);

        titlePage = findViewById(R.id.tv_title_toolbar_back);
        backBtn = findViewById(R.id.img_btn_back_toolbar);
        button = findViewById(R.id.quiz_button);
        pagination = findViewById(R.id.pagination);

        titlePage.setText(getString(R.string.reksadana_capslock));
        backBtn.setOnClickListener(this);

        models = new ArrayList<>();
        models.add(new LearningChapter(R.drawable.img_materi_reksadana_1,"Reksa Dana","Merupakan wadah yang dipergunakan untuk menghitung" +
                "dana dari masyarakat pemodal untuk selanjutnya diinvestasikan dalam portofolio Efek oleh Manajer Investasi"));
        models.add(new LearningChapter(R.drawable.img_materi_reksadana_2,"Jenis Produk Reksa Dana","Sangat Konservatif: \n \u25CF Reksa Dana Pasar Uang" +
                "\n Konservatif: \n \u25CF Reksa Dana Pendapatan Tetap \n \u25CF Reksa Dana Terproteksi \n Moderat: \n \u25CF Reksa Dana Campuran \n Agresif: \n \u25CF " +
                "Reksa Dana Saham"));
        models.add(new LearningChapter(R.drawable.img_materi_reksadana_3,"Manfaat & Risiko Reksa Dana","Manfaat Reksa Dana: \n 1. Pengelolaan yang " +
                "Profesional \n 2. Diversifikasi Investasi \n 3. Transparansi \n 4. Dana Investasi yang Terjangkau \n Risiko Reksa Dana: \n 1. Risiko Berkurangnya Unit " +
                "Penyertaan \n 2. Risiko Perubahan Kondisi Politik dan Ekonomi \n 3. Risiko Likuiditas \n 4. Risiko Nilai Tukar \n 5. Risiko Perubahan Peraturan dan " +
                "Ketentuan Pajak \n 6. Risiko Pembubaran dan Likuiditas \n\n Informasi Lebih Lanjut:"));
        models.add(new LearningChapter(R.drawable.img_materi_kuis,"Kuis Interaktif","Mari ikuti kuis untuk mengasah pemahaman Anda terkait Reksa Dana"));

        adapter = new LearningMateriAdapter(models,this);

        viewPager = findViewById(R.id.viewPagerRDMateri);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(50,0,50, 0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

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

                //if(position == models.size()-2) {
                    /*
                    button.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {
                                                      Intent intent = new Intent();
                                                      intent.setAction(Intent.ACTION_VIEW);
                                                      intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                                      intent.setData(Uri.parse("https://www.bca.co.id/id/Individu/Produk/Investasi-dan-Asuransi/Reksadana"));
                                                      startActivity(intent);
                                                  }
                                              });
                    */
                //}else if(position == models.size() - 1) {
                    /*
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.addCategory(Intent.CATEGORY_BROWSABLE);
                            intent.setData(Uri.parse("https://www.bca.co.id/id/Individu/Produk/Investasi-dan-Asuransi/Obligasi"));
                            startActivity(intent);
                        }
                    });
                    */
                //}



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.bca.co.id/id/Individu/Produk/Investasi-dan-Asuransi/Reksadana"));
                startActivity(intent);
                */
                    /*
                    if(button.getText().toString().equalsIgnoreCase(getString(R.string.klik_disini))){
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse("https://www.bca.co.id/id/Individu/Produk/Investasi-dan-Asuransi/Reksadana"));
                        startActivity(intent);
                    }else if(button.getText().toString().equalsIgnoreCase(getString(R.string.ayo_ikuti_kuis))){
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse("https://www.bca.co.id/id/Individu/Produk/Investasi-dan-Asuransi/Obligasi"));
                        startActivity(intent);
                    }
                    */
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
}
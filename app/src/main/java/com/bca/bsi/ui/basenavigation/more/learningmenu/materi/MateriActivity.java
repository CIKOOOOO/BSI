package com.bca.bsi.ui.basenavigation.more.learningmenu.materi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.bca.bsi.R;
import com.bca.bsi.adapter.LearningMateriAdapter;
import com.bca.bsi.model.KuisData;
import com.bca.bsi.model.LearningChapter;
import com.bca.bsi.ui.basenavigation.more.learningmenu.quiz.QuizActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.dummydata.DummyData;

import java.util.List;

public class MateriActivity extends BaseActivity implements View.OnClickListener, LearningMateriAdapter.onItemClick, IMateriQuizCallback {

    private ImageButton backBtn;
    private TextView titlePage;

    private List<LearningChapter> models;
    private ViewPager viewPager;
    private LearningMateriAdapter adapter;
    private Button button;
    private ImageView pagination;
    private String topic;
    private MateriQuizViewModel viewModel;

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);

        titlePage = findViewById(R.id.tv_title_toolbar_back);
        backBtn = findViewById(R.id.img_btn_back_toolbar);
        pagination = findViewById(R.id.pagination);

        //AMBIL EXTRA DARI INTENT
        Intent intent = getIntent();
        topic = intent.getStringExtra("topic");

        backBtn.setOnClickListener(this);

        //BIKIN SWITCH CASE DI SINI
        switch (topic) {
            case "1":
                titlePage.setText(getString(R.string.reksadana_capslock));
                models = DummyData.setMateriReksaDana();
                System.out.println("INI MODEEEEEEEEEEL LEARNING: " + models.get(0).getTitle());
                break;

            case "2":
                titlePage.setText(getString(R.string.obligasi_capslock));
                models = DummyData.setMateriObligasi();
                break;

            case "3":
                titlePage.setText(getString(R.string.asuransi_capslock));
                models = DummyData.setMateriAsuransi();
                break;
        }

        viewModel = new ViewModelProvider(this).get(MateriQuizViewModel.class);
        viewModel.setCallback(this);

        viewModel.getScoreData(prefConfig.getTokenUser(), "ychris", topic);

        viewPager = findViewById(R.id.view_pager);
        viewPager.setPadding(50, 0, 50, 0);

        /*
        adapter = new LearningMateriAdapter(models,this, this,null);
        viewPager.setAdapter(adapter);
        */

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                System.out.println("INI TOPIC YANG TERPILIH: " + topic);
                currentPage = position;
                switch (position) {
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
        switch (v.getId()) {
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onClick() {
        switch (currentPage) {
            case 2:
                Log.e("asd", "hit");
                Intent intentBrowse = new Intent();
                intentBrowse.setAction(Intent.ACTION_VIEW);
                intentBrowse.addCategory(Intent.CATEGORY_BROWSABLE);

                //SET SWITCH CASE
                switch (topic) {
                    case "1":
                        intentBrowse.setData(Uri.parse("https://www.bca.co.id/id/Individu/Produk/Investasi-dan-Asuransi/Reksadana"));
                        break;

                    case "2":
                        intentBrowse.setData(Uri.parse("https://www.bca.co.id/id/Individu/Produk/Investasi-dan-Asuransi/Obligasi"));
                        break;

                    case "3":
                        intentBrowse.setData(Uri.parse("https://www.bca.co.id/id/Individu/Produk/Investasi-dan-Asuransi/Bancassurance"));
                        break;
                }

                startActivity(intentBrowse);
                break;

            case 3:
                Intent intent = new Intent(this, QuizActivity.class);
                intent.putExtra("topic", topic);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onRetriveData(KuisData.UserScore userScore) {
        adapter = new LearningMateriAdapter(models, this, this, userScore);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFailed(String msg) {
        System.out.println("ERROR: " + msg);
    }
}
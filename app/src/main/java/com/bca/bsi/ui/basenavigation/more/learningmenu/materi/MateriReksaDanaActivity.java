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

import androidx.viewpager.widget.ViewPager;

import com.bca.bsi.R;
import com.bca.bsi.adapter.LearningMateriAdapter;
import com.bca.bsi.model.LearningChapter;
import com.bca.bsi.ui.basenavigation.more.learningmenu.quiz.QuizActivity;
import com.bca.bsi.ui.basenavigation.more.learningmenu.quiz.reksadana.KuisReksaDanaActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.dummydata.DummyData;

import java.util.List;

public class MateriReksaDanaActivity extends BaseActivity implements View.OnClickListener, LearningMateriAdapter.onItemClick {

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
        setContentView(R.layout.activity_materi_reksa_dana);

        titlePage = findViewById(R.id.tv_title_toolbar_back);
        backBtn = findViewById(R.id.img_btn_back_toolbar);
        //button = findViewById(R.id.quiz_button);
        pagination = findViewById(R.id.pagination);

        titlePage.setText(getString(R.string.reksadana_capslock));
        backBtn.setOnClickListener(this);

        models = DummyData.setMateriReksaDana();

        adapter = new LearningMateriAdapter(models,this, this);

        viewPager = findViewById(R.id.viewPagerRDMateri);
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
        Log.e("asd", currentPage+"my current page ");
        switch (currentPage){
            case 2 :
                Log.e("asd","hit");
                Intent intentBrowse = new Intent();
                intentBrowse.setAction(Intent.ACTION_VIEW);
                intentBrowse.addCategory(Intent.CATEGORY_BROWSABLE);
                intentBrowse.setData(Uri.parse("https://www.bca.co.id/id/Individu/Produk/Investasi-dan-Asuransi/Reksadana"));
                startActivity(intentBrowse);
                break;

            case 3:
                Intent intent = new Intent(this, QuizActivity.class);
                intent.putExtra("topic","1");
                startActivity(intent);
                break;
        }
    }
}
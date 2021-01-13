package com.bca.bsi.ui.basenavigation.more.learningmenu.quiz.asuransi;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bca.bsi.R;
import com.bca.bsi.adapter.QuizAdapter;
import com.bca.bsi.model.KuisData;
import com.bca.bsi.ui.basenavigation.more.learningmenu.TopicListActivity;
import com.bca.bsi.ui.basenavigation.more.learningmenu.quiz.CustomViewPager;
import com.bca.bsi.ui.basenavigation.more.learningmenu.quiz.IQuizCallback;
import com.bca.bsi.ui.basenavigation.more.learningmenu.quiz.QuizViewModel;
import com.bca.bsi.ui.basenavigation.products.detail.DetailProductActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.constant.SwipeDirection;
import com.bca.bsi.utils.dummydata.DummyData;

public class KuisAsuransiActivity extends BaseActivity implements View.OnClickListener, QuizAdapter.onItemClick, IQuizCallback {

    private ImageButton backBtn;
    private TextView titlePage;
    private TextView titleChild;
    private CustomViewPager viewPager;
    private QuizAdapter adapter;
    private ImageView pagination;
    private int currentPage;
    private QuizViewModel viewModel;
    private KuisData kuisData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis_asuransi);

        titlePage = findViewById(R.id.tv_title_toolbar_back);
        backBtn = findViewById(R.id.img_btn_back_toolbar);
        titleChild = findViewById(R.id.tv_child_toolbar_back);
        pagination = findViewById(R.id.pagination);

        titlePage.setText(getString(R.string.kuis_capslock));
        titleChild.setText(getString(R.string.asuransi));
        backBtn.setOnClickListener(this);

        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        viewModel.setCallback(KuisAsuransiActivity.this);

        kuisData = DummyData.setKuisDataDummy();

        adapter = new QuizAdapter(kuisData,this,this);

        viewPager = findViewById(R.id.viewPagerKuis);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(50,0,50, 0);
        viewPager.setAllowedSwipeDirection(SwipeDirection.left);
        viewPager.setOffscreenPageLimit(6);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                switch (position){
                    case 0:
                        pagination.setImageResource(R.drawable.asset_pagination_1_6);
                        break;
                    case 1:
                        pagination.setImageResource(R.drawable.asset_pagination_2_6);
                        break;
                    case 2:
                        pagination.setImageResource(R.drawable.asset_pagination_3_6);
                        break;
                    case 3:
                        pagination.setImageResource(R.drawable.asset_pagination_4_6);
                        break;
                    case 4:
                        pagination.setImageResource(R.drawable.asset_pagination_5_6);
                        break;
                    case 5:
                        pagination.setImageResource(R.drawable.asset_pagination_6_6);
                        adapter.setNilai();
                        adapter.notifyDataSetChanged();
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
        if(currentPage<5){
            viewPager.setCurrentItem(currentPage+1);
        }else{
            Intent intent = new Intent(this, DetailProductActivity.class);
            intent.putExtra(DetailProductActivity.PRODUCT_TYPE,2);
            startActivity(intent);
        }
    }

    @Override
    public void openPage() {
        Intent intent = new Intent(this, TopicListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onRetriveData(KuisData kuisData) {

    }

    @Override
    public void onFailed(String msg) {

    }
}
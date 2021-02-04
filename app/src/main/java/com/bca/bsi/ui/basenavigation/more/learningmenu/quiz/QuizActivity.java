package com.bca.bsi.ui.basenavigation.more.learningmenu.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.bca.bsi.R;
import com.bca.bsi.adapter.QuizAdapter;
import com.bca.bsi.model.KuisData;
import com.bca.bsi.ui.basenavigation.more.learningmenu.TopicListActivity;
import com.bca.bsi.ui.basenavigation.products.detail.DetailProductActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.constant.SwipeDirection;

public class QuizActivity extends BaseActivity implements View.OnClickListener, QuizAdapter.onItemClick, IQuizCallback {

    private ImageButton backBtn;
    private TextView titlePage;
    private TextView titleChild;
    private CustomViewPager viewPager;
    private QuizAdapter adapter;
    private ImageView pagination;
    private int currentPage;
    private QuizViewModel viewModel;
    private String topic;
    //private KuisData kuisData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        titlePage = findViewById(R.id.tv_title_toolbar_back);
        backBtn = findViewById(R.id.img_btn_back_toolbar);
        titleChild = findViewById(R.id.tv_child_toolbar_back);
        pagination = findViewById(R.id.pagination);

        titlePage.setText(getString(R.string.kuis_capslock));

        //AMBIL EXTRA DARI INTENT
        Intent intent = getIntent();
        topic = intent.getStringExtra("topic");

        //BIKIN SWITCH CASE UNTUK INIIII
        switch (topic) {
            case "1":
                titleChild.setText(getString(R.string.reksadana));
                break;

            case "2":
                titleChild.setText(getString(R.string.obligasi));
                break;

            case "3":
                titleChild.setText(getString(R.string.asuransi));
                break;
        }

        backBtn.setOnClickListener(this);

        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        viewModel.setCallback(this);

        //BIKIN SWITCH CASE
        viewModel.getData(prefConfig.getTokenUser(), topic);

        viewPager = findViewById(R.id.viewPagerKuis);
        viewPager.setPadding(50, 0, 50, 0);
        viewPager.setAllowedSwipeDirection(SwipeDirection.left);
        viewPager.setOffscreenPageLimit(6);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                switch (position) {
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
        switch (v.getId()) {
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onClick(KuisData.UserScore userScore) {
        if (currentPage == 4) {
            viewModel.putUserScore(prefConfig.getTokenUser(), userScore.getBcaId(), userScore.getCategoryId(), Integer.parseInt(userScore.getScore()));
            viewModel.getScore(prefConfig.getTokenUser(), userScore.getBcaId(), userScore.getCategoryId());
            viewPager.setCurrentItem(currentPage + 1);
        } else if (currentPage < 5) {
            viewPager.setCurrentItem(currentPage + 1);
        } else {
            System.out.println("udah masuk siniii");
            Intent intent = new Intent(this, DetailProductActivity.class);
            //BIKIN SWITCH CASE PRODUCT TYPE
            int productType = 0;
            intent.putExtra(DetailProductActivity.PRODUCT_TYPE, productType);
            intent.putExtra(DetailProductActivity.PRODUCT_TYPE, 1);
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
        adapter = new QuizAdapter(kuisData, this, this);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFailed(String msg) {
        System.out.println("ERROR: " + msg);
    }

    @Override
    public void onRetrieveDataGetUserScore(KuisData.UserScore userScore) {

    }

    @Override
    public void onFailedScoreGetUserScore(String msg) {
        System.out.println("ERROR GETSCORE DARI QUIZVIEWMODEL: " + msg);
    }
}
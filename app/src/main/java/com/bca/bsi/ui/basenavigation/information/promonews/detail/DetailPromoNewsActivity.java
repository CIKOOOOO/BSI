package com.bca.bsi.ui.basenavigation.information.promonews.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bca.bsi.R;
import com.bca.bsi.model.PromoNews;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.constant.Type;
import com.google.gson.Gson;

public class DetailPromoNewsActivity extends BaseActivity implements View.OnClickListener {

    public static final String TYPE = "type";
    public static final String DATA = "data";

    private PromoNews promoNews;

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_promo_news);
        initVar();
    }

    private void initVar() {

        TextView tvTitleToolbar = findViewById(R.id.tv_title_toolbar_back);
        TextView tvChildToolbar = findViewById(R.id.tv_child_toolbar_back);
        TextView tvTitle = findViewById(R.id.tv_title_detail_promo_news);
        TextView tvDate = findViewById(R.id.tv_date_detail_promo_news);
        TextView tvContent = findViewById(R.id.tv_content_detail_promo_news);
        TextView tvShare = findViewById(R.id.tv_share_to_forum_detail_promo_news);
        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar);

        tvChildToolbar.setVisibility(View.GONE);

        tvShare.setAllCaps(false);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(TYPE) && intent.hasExtra(DATA)) {
            Gson gson = new Gson();
            String data = intent.getStringExtra(DATA);
            this.type = intent.getStringExtra(TYPE);
            this.promoNews = gson.fromJson(data, PromoNews.class);

            String title = this.type.equals(Type.NEWS) ? getString(R.string.detail_news) : getString(R.string.detail_promo);
            tvTitleToolbar.setText(title);

            tvTitle.setText(this.promoNews.getTitle());
            tvDate.setText("Dirilis pada " + this.promoNews.getDate());
            tvContent.setText(this.promoNews.getContent());
        }

        tvShare.setOnClickListener(this);
        imgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_share_to_forum_detail_promo_news:


                break;
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
        }
    }
}
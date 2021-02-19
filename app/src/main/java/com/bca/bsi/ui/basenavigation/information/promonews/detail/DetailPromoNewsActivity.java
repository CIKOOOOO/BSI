package com.bca.bsi.ui.basenavigation.information.promonews.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.bca.bsi.R;
import com.bca.bsi.model.PromoNews;
import com.bca.bsi.ui.basenavigation.information.forum.post.PostActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.Utils;
import com.squareup.picasso.Picasso;

public class DetailPromoNewsActivity extends BaseActivity implements View.OnClickListener, IDetailNewsCallback {

    public static final String DATA = "data";

    private PromoNews promoNews;
    private DetailNewsViewModel viewModel;
    private ImageView imgNews;
    private TextView tvTitle, tvDate, tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_promo_news);
        initVar();
    }

    private void initVar() {
        TextView tvTitleToolbar = findViewById(R.id.tv_title_toolbar_back);
        TextView tvChildToolbar = findViewById(R.id.tv_child_toolbar_back);
        TextView tvShare = findViewById(R.id.tv_share_to_forum_detail_promo_news);
        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar);

        imgNews = findViewById(R.id.img_detail_promo_news);
        tvTitle = findViewById(R.id.tv_title_detail_promo_news);
        tvDate = findViewById(R.id.tv_date_detail_promo_news);
        tvContent = findViewById(R.id.tv_content_detail_promo_news);

        viewModel = new ViewModelProvider(this).get(DetailNewsViewModel.class);
        viewModel.setCallback(this);

        tvChildToolbar.setVisibility(View.GONE);

        tvShare.setAllCaps(false);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(DATA)) {
            String data = intent.getStringExtra(DATA);
            tvTitleToolbar.setText(getString(R.string.detail_news));
            viewModel.loadNews(prefConfig.getTokenUser(), data);
        }

        tvShare.setOnClickListener(this);
        imgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_share_to_forum_detail_promo_news:
                if (prefConfig.isForumAccountBanned().equalsIgnoreCase("Y")) {
                    showSnackBar("Akun Anda dibekukan, silahkan hubungi HaloBCA");
                } else {
                    Intent intent = new Intent(this, PostActivity.class);
                    intent.putExtra(PostActivity.DATA, Utils.toJSON(this.promoNews));
                    intent.putExtra(PostActivity.POST_TYPE, PostActivity.SHARE_NEWS);
                    startActivity(intent);
                }
                break;
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onLoadDetailNews(PromoNews promoNews) {
        this.promoNews = promoNews;

        Picasso.get()
                .load(Utils.imageURL(promoNews.getImage()))
                .into(imgNews);

        tvTitle.setText(this.promoNews.getTitle());
        tvDate.setText("Dirilis pada " + this.promoNews.getDate());
        tvContent.setText(this.promoNews.getContent());
    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }
}
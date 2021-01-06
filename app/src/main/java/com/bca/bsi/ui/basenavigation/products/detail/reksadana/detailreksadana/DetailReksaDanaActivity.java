package com.bca.bsi.ui.basenavigation.products.detail.reksadana.detailreksadana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bca.bsi.R;
import com.bca.bsi.utils.BaseActivity;

public class DetailReksaDanaActivity extends BaseActivity implements View.OnClickListener {

    public static final String REKSA_DANA_ID = "reksa_dana_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reksa_dana);
        initVar();
    }

    private void initVar() {
        TextView tvTitle = findViewById(R.id.tv_title_toolbar_back);
        TextView tvChild = findViewById(R.id.tv_child_toolbar_back);
        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar);

        tvTitle.setText(getString(R.string.detail_product));
        tvChild.setVisibility(View.GONE);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(REKSA_DANA_ID)) {

        }

        imgBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
        }
    }
}
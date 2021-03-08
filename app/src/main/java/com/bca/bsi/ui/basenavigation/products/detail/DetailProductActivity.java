package com.bca.bsi.ui.basenavigation.products.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bca.bsi.R;
import com.bca.bsi.ui.basenavigation.BaseNavigationActivity;
import com.bca.bsi.ui.basenavigation.products.detail.reksadana.ReksadanaProductFragment;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.constant.Constant;
import com.bca.bsi.utils.constant.Type;

public class DetailProductActivity extends BaseActivity implements View.OnClickListener {

    public static final String PRODUCT_TYPE = "product_type";
    public static final String FROM = "from_quiz";

    private TextView btnLeft, btnRight;
    private String type;
    private int fromQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        initVar();
    }

    private void initVar() {
        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar);

        btnLeft = findViewById(R.id.btn_left_detail_product);
        btnRight = findViewById(R.id.btn_right_detail_product);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(PRODUCT_TYPE)) {
            TextView tvTitleToolbar = findViewById(R.id.tv_title_toolbar_back);
            TextView tvChildToolbar = findViewById(R.id.tv_child_toolbar_back);
            int pos = intent.getIntExtra(PRODUCT_TYPE, -1);
            fromQuiz = intent.getIntExtra(FROM, -1);
            if (pos == -1) {
                onBackPressed();
            }

            switch (pos) {
                case 0:
                    tvTitleToolbar.setText(this.getString(R.string.product));
                    tvChildToolbar.setText(Constant.PRODUCT_TITLE[pos]);
                    type = Type.REKSA_DANA;
                    btnLeft.setText(this.getString(R.string.all_product));
                    btnRight.setText(this.getString(R.string.invest_manager));
                    changeFragment(new ReksadanaProductFragment());
                    break;
                case 1:
                    tvTitleToolbar.setText(this.getString(R.string.pasar_perdana));
                    tvChildToolbar.setVisibility(View.GONE);
                    type = Type.OBLIGASI_PASAR_PERDANA;
                    btnLeft.setText(this.getString(R.string.product));
                    btnRight.setText(this.getString(R.string.transaction_status));
                    break;
            }
        } else {
            onBackPressed();
        }

        btnRight.setTextColor(this.getResources().getColor(R.color.white_palette));
        btnLeft.setTextColor(this.getResources().getColor(R.color.black_palette));
        btnLeft.setBackground(ContextCompat.getDrawable(this, R.drawable.rectangle_rounded_orange_light_20dp));
        btnRight.setBackground(ContextCompat.getDrawable(this, R.drawable.rectangle_rounded_sherpa_blue));

        imgBack.setOnClickListener(this);

        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
            case R.id.btn_left_detail_product:
                btnRight.setTextColor(this.getResources().getColor(R.color.white_palette));
                btnLeft.setTextColor(this.getResources().getColor(R.color.black_palette));
                btnLeft.setBackground(ContextCompat.getDrawable(this, R.drawable.rectangle_rounded_orange_light_20dp));
                btnRight.setBackground(ContextCompat.getDrawable(this, R.drawable.rectangle_rounded_sherpa_blue));
                if (type.equals(Type.REKSA_DANA)) {
                    changeFragment(new ReksadanaProductFragment());
                }
                break;
            case R.id.btn_right_detail_product:
                btnRight.setTextColor(this.getResources().getColor(R.color.black_palette));
                btnLeft.setTextColor(this.getResources().getColor(R.color.white_palette));
                btnLeft.setBackground(ContextCompat.getDrawable(this, R.drawable.rectangle_rounded_sherpa_blue));
                btnRight.setBackground(ContextCompat.getDrawable(this, R.drawable.rectangle_rounded_orange_light_20dp));
                break;
        }
    }

    public void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_detail_product, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (fromQuiz == -1) {
            super.onBackPressed();
        } else {
            Intent intent = new Intent(this, BaseNavigationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}
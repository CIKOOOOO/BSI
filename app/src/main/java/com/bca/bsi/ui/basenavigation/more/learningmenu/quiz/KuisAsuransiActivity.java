package com.bca.bsi.ui.basenavigation.more.learningmenu.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bca.bsi.R;
import com.bca.bsi.utils.BaseActivity;

public class KuisAsuransiActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton backBtn;
    private TextView titlePage;
    private TextView titleChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis_asuransi);

        titlePage = findViewById(R.id.tv_title_toolbar_back);
        backBtn = findViewById(R.id.img_btn_back_toolbar);
        titleChild = findViewById(R.id.tv_child_toolbar_back);

        titlePage.setText(getString(R.string.kuis_capslock));
        titleChild.setText(getString(R.string.asuransi));
        backBtn.setOnClickListener(this);
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
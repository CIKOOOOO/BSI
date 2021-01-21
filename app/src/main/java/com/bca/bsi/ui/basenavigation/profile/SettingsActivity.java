package com.bca.bsi.ui.basenavigation.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.bca.bsi.R;
import com.bca.bsi.utils.BaseActivity;

public class SettingsActivity extends BaseActivity {
    TextView toolbarTitle, toolbarSubtitle;
    ImageButton backButton;
    Switch swTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setToolbar();
        setSwTips();
    }

    private void setToolbar(){
        toolbarTitle = findViewById(R.id.tv_title_toolbar_back);
        toolbarSubtitle = findViewById(R.id.tv_child_toolbar_back);
        backButton = findViewById(R.id.img_btn_back_toolbar);

        toolbarTitle.setText("SETTINGS");
        toolbarSubtitle.setVisibility(View.GONE);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setSwTips(){
        // Atur switch di sini
        swTips = findViewById(R.id.sw_tips);
        swTips.setChecked(prefConfig.getTipsActivated());
        swTips.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                prefConfig.setTipsOfTheWeek(isChecked);
            }
        });
    }
}
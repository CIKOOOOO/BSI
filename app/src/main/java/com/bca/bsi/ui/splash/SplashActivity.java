package com.bca.bsi.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bca.bsi.R;
import com.bca.bsi.ui.login.LoginActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.constant.Constant;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initVar();
    }

    private void initVar() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                finishAffinity();
//                prefConfig.setLogin(false);
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        };

        new Handler().postDelayed(runnable, Constant.SPLASH_DURATION);
    }
}
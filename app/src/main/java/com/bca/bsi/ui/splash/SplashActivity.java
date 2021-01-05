package com.bca.bsi.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.bca.bsi.R;
import com.bca.bsi.ui.login.LoginActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.constant.Constant;

public class SplashActivity extends BaseActivity implements ISplashCallback {

    private SplashViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initVar();
    }

    private void initVar() {
        viewModel = new ViewModelProvider(this).get(SplashViewModel.class);
        viewModel.setCallback(this);

        prefConfig.removeTokenAccess();
        prefConfig.logOut();

        viewModel.getAccessToken();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        };

        new Handler().postDelayed(runnable, Constant.SPLASH_DURATION);
    }

    @Override
    public void onGettingToken(String token) {
        prefConfig.setTokenAccess(token);
    }

    @Override
    public void onFailed() {
//        Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
    }
}
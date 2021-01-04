package com.bca.bsi.utils;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bca.bsi.R;
import com.google.android.material.snackbar.Snackbar;

public class BaseActivity extends AppCompatActivity {

    public PrefConfig prefConfig;

    private View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = findViewById(android.R.id.content);
        prefConfig = new PrefConfig(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.sherpa_blue_palette));
        }
    }

    public void showSnackBar(String msg) {
        Snackbar snackbar = Snackbar
                .make(view, msg, Snackbar.LENGTH_LONG)
                .setBackgroundTint(getResources().getColor(R.color.dark_gray_palette))
                .setTextColor(getResources().getColor(R.color.white_palette))
                .setDuration(3000);
        snackbar.show();
    }
}

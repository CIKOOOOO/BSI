package com.bca.bsi.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bca.bsi.R;
import com.bca.bsi.utils.dialog.CustomDialog;
import com.google.android.material.snackbar.Snackbar;

public class BaseActivity extends AppCompatActivity implements CustomDialog.onClick {

    public PrefConfig prefConfig;

    private View view;
    private CustomDialog customDialog;

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

        Utils.setupUI(view, this);
    }

    public void showSnackBar(String msg) {
        Snackbar snackbar = Snackbar
                .make(view, msg, Snackbar.LENGTH_LONG)
                .setBackgroundTint(getResources().getColor(R.color.dark_gray_palette))
                .setTextColor(getResources().getColor(R.color.white_palette))
                .setDuration(3000);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (!isNetworkAvailable()) {
//            customDialog = new CustomDialog(getString(R.string.internet_down), getString(R.string.close)
//                    , ContextCompat.getDrawable(this, R.drawable.ic_close_red), this);
//            customDialog.show(getSupportFragmentManager(), "");
//        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onDismissButton() {
        if (customDialog != null && customDialog.getTag() != null) {
            customDialog.dismiss();
        }
    }
}

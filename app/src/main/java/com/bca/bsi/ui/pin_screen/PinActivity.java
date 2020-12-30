package com.bca.bsi.ui.pin_screen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.bca.bsi.R;
import com.bca.bsi.ui.basenavigation.transaction.detail_transaction.DetailTransactionActivity;
import com.bca.bsi.utils.BaseActivity;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

public class PinActivity extends BaseActivity implements IPinCallback {

    private PinViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        initVar();
    }

    private void initVar() {
        TextView tvTitleToolbar = findViewById(R.id.tv_title_toolbar_back);
        TextView tvChildToolbar = findViewById(R.id.tv_child_toolbar_back);
        OtpView otpView = findViewById(R.id.otp_view_pin_activity);

        viewModel = new ViewModelProvider(this).get(PinViewModel.class);
        viewModel.setCallback(this);

        tvTitleToolbar.setText(getString(R.string.pin_code));
        tvChildToolbar.setText(getString(R.string.input_pin));

        otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                viewModel.checkingPin(otp);
            }
        });
    }

    @Override
    public void onSuccessPin() {
        startActivity(new Intent(PinActivity.this, DetailTransactionActivity.class));
    }

    @Override
    public void onWrongPin() {

    }
}
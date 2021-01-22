package com.bca.bsi.ui.pin_screen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.bca.bsi.R;
import com.bca.bsi.model.Transaction;
import com.bca.bsi.ui.basenavigation.transaction.confirmation.ConfirmationTransactionActivity;
import com.bca.bsi.ui.basenavigation.transaction.detail_transaction.DetailTransactionActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.CustomLoading;
import com.bca.bsi.utils.Utils;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

public class PinActivity extends BaseActivity implements IPinCallback, View.OnClickListener {

    public static final String TRANSACTION_TYPE = "transaction_type";
    public static final String PARCEL_DATA = "parcel_data";

    private PinViewModel viewModel;
    private CustomLoading customLoading;

    private String data, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        initVar();
    }

    private void initVar() {
        TextView tvTitleToolbar = findViewById(R.id.tv_title_toolbar_back);
        TextView tvChildToolbar = findViewById(R.id.tv_child_toolbar_back);
        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar);
        OtpView otpView = findViewById(R.id.otp_view_pin_activity);

        customLoading = new CustomLoading();

        viewModel = new ViewModelProvider(this).get(PinViewModel.class);
        viewModel.setCallback(this);

        tvTitleToolbar.setText(getString(R.string.pin_code));
        tvChildToolbar.setText(getString(R.string.input_pin));

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(TRANSACTION_TYPE) && intent.hasExtra(PARCEL_DATA)) {
            this.type = intent.getStringExtra(TRANSACTION_TYPE);
            this.data = intent.getStringExtra(PARCEL_DATA);
        }

        otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                Log.e("asd", otp);
                customLoading.show(getSupportFragmentManager(), "");
                viewModel.checkingPin(type, otp, prefConfig.getBCAID(), data);
            }
        });
        imgBack.setOnClickListener(this);
    }

    @Override
    public void onSuccessPin(Object o) {
        if (customLoading != null && customLoading.getTag() != null) {
            customLoading.dismiss();
        }

        switch (type) {
            case ConfirmationTransactionActivity.PURCHASE_FROM_CONFIRMATION_ACTIVITY:
            case ConfirmationTransactionActivity.SELLING_FROM_CONFIRMATION_ACTIVITY:
                Transaction.TransactionResult transactionResult = (Transaction.TransactionResult) o;

                Intent intent = new Intent(this, DetailTransactionActivity.class);
                intent.putExtra(DetailTransactionActivity.PARCEL_DATA, Utils.toJSON(transactionResult));
                intent.putExtra(DetailTransactionActivity.RESULT_TYPE, type);
                startActivity(intent);
                break;
        }

//        startActivity(new Intent(PinActivity.this, DetailTransactionActivity.class));
    }

    @Override
    public void onWrongPin() {
        if (customLoading != null && customLoading.getTag() != null) {
            customLoading.dismiss();
        }

    }

    @Override
    public void onFailed(String msg) {
        if (customLoading != null && customLoading.getTag() != null) {
            customLoading.dismiss();
        }

        showSnackBar(msg);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
        }
    }
}
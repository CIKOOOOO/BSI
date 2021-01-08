package com.bca.bsi.ui.pin_screen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.bca.bsi.R;
import com.bca.bsi.model.Transaction;
import com.bca.bsi.ui.basenavigation.transaction.confirmation.ConfirmationTransactionActivity;
import com.bca.bsi.ui.basenavigation.transaction.detail_transaction.DetailTransactionActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.Utils;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

public class PinActivity extends BaseActivity implements IPinCallback {

    public static final String TRANSACTION_TYPE = "transaction_type";
    public static final String PARCEL_DATA = "parcel_data";

    private PinViewModel viewModel;

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
        OtpView otpView = findViewById(R.id.otp_view_pin_activity);

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
                viewModel.checkingPin(type, otp, data);
            }
        });
    }

    @Override
    public void onSuccessPin(Object o) {
        switch (type) {
            case ConfirmationTransactionActivity.FROM_CONFIRMATION_ACTIVITY:
                Transaction.TransactionResult transactionResult = (Transaction.TransactionResult) o;

                Intent intent = new Intent(this, DetailTransactionActivity.class);
                intent.putExtra(DetailTransactionActivity.PARCEL_DATA, Utils.toJSON(transactionResult));
                startActivity(intent);
                break;
        }

        startActivity(new Intent(PinActivity.this, DetailTransactionActivity.class));
    }

    @Override
    public void onWrongPin() {

    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }
}
package com.bca.bsi.ui.basenavigation.transaction.detail_transaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bca.bsi.R;
import com.bca.bsi.model.Transaction;
import com.bca.bsi.utils.BaseActivity;
import com.google.gson.Gson;

public class DetailTransactionActivity extends BaseActivity implements View.OnClickListener {

    public static final String PARCEL_DATA = "parcel_data";

    private TextView tvProductName, tvTransactionType, tvNominalPembelian, tvNominalBiayaPembelian, tvNominalTotalPembelian, tvRekeningSumberDana, tvTransactionTime, tvReferenceNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaction);
        initVar();
    }

    private void initVar() {

        TextView tvTitle = findViewById(R.id.tv_title_toolbar_back_image);
        Button btnFinish = findViewById(R.id.btn_finish_detail_transaction);

        tvProductName = findViewById(R.id.tv_name_detail_transaction);
        tvTransactionType = findViewById(R.id.tv_type_detail_transaction);
        tvNominalPembelian = findViewById(R.id.tv_nominal_pembelian_detail_transaction);
        tvNominalBiayaPembelian = findViewById(R.id.tv_nominal_biaya_pembelian_detail_pembelian);
        tvNominalTotalPembelian = findViewById(R.id.tv_nominal_total_pembelian_detail_pembelian);
        tvRekeningSumberDana = findViewById(R.id.tv_rekening_sumber_dana_detail_pembelian);
        tvTransactionTime = findViewById(R.id.tv_time_detail_transaction);
        tvReferenceNumber = findViewById(R.id.tv_referensi_number_detail_transaction);

        tvTitle.setText(getString(R.string.product_detail));

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(PARCEL_DATA)) {
            String data = intent.getStringExtra(PARCEL_DATA);
            Gson gson = new Gson();
            Transaction.TransactionResult transactionResult = gson.fromJson(data, Transaction.TransactionResult.class);

            tvProductName.setText(transactionResult.getProductName());
            tvTransactionType.setText(transactionResult.getPaymentType());
            tvNominalPembelian.setText(transactionResult.getNominalPembelian());
            tvNominalBiayaPembelian.setText(transactionResult.getNominalBiayaPembelian());
            tvNominalTotalPembelian.setText(transactionResult.getNominalTotalPembelian());
            tvRekeningSumberDana.setText(transactionResult.getRekeningSumberDana());
            tvTransactionTime.setText(transactionResult.getTransactionTime());
            tvReferenceNumber.setText(transactionResult.getReferenceNumber());
        }

        btnFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_finish_detail_transaction:

                break;
        }
    }
}
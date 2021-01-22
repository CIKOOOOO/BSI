package com.bca.bsi.ui.basenavigation.transaction.detail_transaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bca.bsi.R;
import com.bca.bsi.model.Transaction;
import com.bca.bsi.ui.basenavigation.BaseNavigationActivity;
import com.bca.bsi.ui.basenavigation.transaction.confirmation.ConfirmationTransactionActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Type;
import com.google.gson.Gson;

public class DetailTransactionActivity extends BaseActivity implements View.OnClickListener {

    public static final String PARCEL_DATA = "parcel_data";
    public static final String RESULT_TYPE = "type";

    private TextView tvProductName, tvTransactionType, tvNominalPembelian, tvNominalBiayaPembelian, tvNominalTotalPembelian, tvRekeningSumberDana, tvTransactionTime, tvReferenceNumber;

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaction);
        initVar();
    }

    private void initVar() {

        TextView tvTitle = findViewById(R.id.tv_title_toolbar_image);
        Button btnFinish = findViewById(R.id.btn_finish_detail_transaction);

        tvTransactionType = findViewById(R.id.tv_type_detail_transaction);
        tvNominalPembelian = findViewById(R.id.tv_nominal_pembelian_detail_transaction);
        tvNominalBiayaPembelian = findViewById(R.id.tv_nominal_biaya_pembelian_detail_pembelian);
        tvNominalTotalPembelian = findViewById(R.id.tv_nominal_total_pembelian_detail_pembelian);
        tvRekeningSumberDana = findViewById(R.id.tv_rekening_sumber_dana_detail_pembelian);
        tvTransactionTime = findViewById(R.id.tv_time_detail_transaction);
        tvReferenceNumber = findViewById(R.id.tv_referensi_number_detail_transaction);
        tvProductName = findViewById(R.id.tv_product_name_detail_transaction);

        tvTitle.setText(getString(R.string.product_detail));

        findViewById(R.id.img_btn_action_toolbar_image).setVisibility(View.GONE);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(PARCEL_DATA) && intent.hasExtra(RESULT_TYPE)) {
            String data = intent.getStringExtra(PARCEL_DATA);
            this.type = intent.getStringExtra(RESULT_TYPE);
            Gson gson = new Gson();
            Transaction.TransactionResult transactionResult = gson.fromJson(data, Transaction.TransactionResult.class);

            String dummyNominalPembelian, dummyNominalBiayaPembelian, dummyTotalPenjualan, dummyRekeningTujuan;

            String paymentType;
            if(type.equals(ConfirmationTransactionActivity.SELLING_FROM_CONFIRMATION_ACTIVITY)){
                paymentType = transactionResult.getPaymentType();
                dummyNominalPembelian = "Nominal Penjualan";
                dummyNominalBiayaPembelian = "Nominal Biaya Penjualan";
                dummyTotalPenjualan = "Nominal Total Penjualan";
                dummyRekeningTujuan = "Rekening Tujuan";
            }else{
                paymentType = transactionResult.getPaymentType().equals(Type.PEMBELIAN_BERKALA) ? "Pembelian Berkala" : "Pembelian Sekali Bayar";
                dummyNominalPembelian = "Nominal Pembelian";
                dummyNominalBiayaPembelian = "Nominal Biaya Pembelian";
                dummyTotalPenjualan = "Nominal Total Pembelian";
                dummyRekeningTujuan = "Rekening Sumber Dana";
            }

            ((TextView)findViewById(R.id.tv_01_detail_transaction)).setText(dummyNominalPembelian);
            ((TextView)findViewById(R.id.tv_02_detail_transaction)).setText(dummyNominalBiayaPembelian);
            ((TextView)findViewById(R.id.tv_03_detail_transaction)).setText(dummyTotalPenjualan);
            ((TextView)findViewById(R.id.tv_04_detail_transaction)).setText(dummyRekeningTujuan);

            tvProductName.setText(transactionResult.getProductName());
            tvTransactionType.setText(paymentType);
            tvNominalPembelian.setText("Rp " + Utils.formatUang2(Double.parseDouble(transactionResult.getNominalTransaksi())));
            tvNominalBiayaPembelian.setText("Rp " + Utils.formatUang2(Double.parseDouble(transactionResult.getNominalBiayaTransaksi())));
            tvNominalTotalPembelian.setText("Rp " + Utils.formatUang2(Double.parseDouble(transactionResult.getNominalTotalTransaksi())));
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
                Intent intent = new Intent(this, BaseNavigationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {

    }
}
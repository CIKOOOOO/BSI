package com.bca.bsi.ui.basenavigation.transaction.detail_transaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Transaction;
import com.bca.bsi.ui.basenavigation.BaseNavigationActivity;
import com.bca.bsi.ui.basenavigation.transaction.confirmation.ConfirmationTransactionActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.constant.Type;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity to show detail transaction after success purchasing or selling
 */
public class DetailTransactionActivity extends BaseActivity implements View.OnClickListener {

    public static final String PARCEL_DATA = "parcel_data";
    public static final String RESULT_TYPE = "type";

    private TextView tvTransactionType, tvRekeningSumberDana, tvTransactionTime;
    private DetailTransactionAdapter adapter;

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
        RecyclerView recyclerView = findViewById(R.id.recycler_detail_transaction);

        tvTransactionType = findViewById(R.id.tv_type_detail_transaction);
        tvRekeningSumberDana = findViewById(R.id.tv_rekening_sumber_dana_detail_pembelian);
        tvTransactionTime = findViewById(R.id.tv_time_detail_transaction);

        adapter = new DetailTransactionAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tvTitle.setText(getString(R.string.product_detail));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.img_btn_action_toolbar_image).setVisibility(View.GONE);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(PARCEL_DATA) && intent.hasExtra(RESULT_TYPE)) {
            String data = intent.getStringExtra(PARCEL_DATA);
            this.type = intent.getStringExtra(RESULT_TYPE);
            Gson gson = new Gson();
            List<Transaction.TransactionResult> transactionResultList = new ArrayList<>();
            String dummyRekeningTujuan = "";
            if (this.type.equals(Type.PURCHASING_WITH_SMARTBOT)) {

                transactionResultList = intent.getParcelableArrayListExtra(PARCEL_DATA);

                String paymentType = transactionResultList.get(0).getPaymentType().equals(Type.PEMBELIAN_BERKALA) ? "Pembelian Berkala" : "Pembelian Sekali Bayar";

                tvTransactionType.setText(paymentType);
                tvRekeningSumberDana.setText(transactionResultList.get(0).getRekeningSumberDana());
                tvTransactionTime.setText(transactionResultList.get(0).getTransactionTime());

                dummyRekeningTujuan = "Rekening Sumber Dana";
            } else {
                Transaction.TransactionResult transactionResult = gson.fromJson(data, Transaction.TransactionResult.class);

                transactionResultList.add(transactionResult);

                String paymentType;
                if (type.equals(ConfirmationTransactionActivity.SELLING_FROM_CONFIRMATION_ACTIVITY)) {
                    paymentType = transactionResult.getPaymentType();
                    dummyRekeningTujuan = "Rekening Tujuan";
                } else {
                    paymentType = transactionResult.getPaymentType().equals(Type.PEMBELIAN_BERKALA) ? "Pembelian Berkala" : "Pembelian Sekali Bayar";
                    dummyRekeningTujuan = "Rekening Sumber Dana";
                }
                tvTransactionType.setText(paymentType);
                tvRekeningSumberDana.setText(transactionResult.getRekeningSumberDana());
                tvTransactionTime.setText(transactionResult.getTransactionTime());
            }

            ((TextView) findViewById(R.id.tv_04_detail_transaction)).setText(dummyRekeningTujuan);

            adapter.setType(type);
            adapter.setTransactionResultList(transactionResultList);
            adapter.notifyDataSetChanged();
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
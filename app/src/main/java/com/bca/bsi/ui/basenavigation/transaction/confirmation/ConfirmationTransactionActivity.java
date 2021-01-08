package com.bca.bsi.ui.basenavigation.transaction.confirmation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;
import com.bca.bsi.model.Transaction;
import com.bca.bsi.ui.pin_screen.PinActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.Utils;
import com.google.gson.Gson;

public class ConfirmationTransactionActivity extends BaseActivity implements View.OnClickListener {

    public static final String DATA_REKSA_DANA = "dana_reksa_data";
    public static final String DATA_TRANSACTION = "transaction_data";
    public static final String FROM_CONFIRMATION_ACTIVITY = "from_conformation_activity";

    private TextView tvTransactionType, tvNominalPembelian, tvNominalBiayaPembelian, tvTotalPembelian, tvRekeningSumberDana, tvProductName;
    private CheckBox cbConfirmation;
    private Product.DetailReksaDana detailReksaDana;
    private Transaction.Purchasing purchasing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_confirmation);
        initVar();
    }

    private void initVar() {

        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar);
        final Button btnNext = findViewById(R.id.btn_next_transaction_confirmation);

        tvTransactionType = findViewById(R.id.tv_transaction_type_confirmation);
        tvNominalPembelian = findViewById(R.id.tv_nominal_pembelian_confirmation);
        tvNominalBiayaPembelian = findViewById(R.id.tv_nominal_biaya_pembelian_confirmation);
        tvTotalPembelian = findViewById(R.id.tv_nominal_total_pembelian_confirmation);
        tvRekeningSumberDana = findViewById(R.id.tv_rekening_sumber_dana_confirmation);
        cbConfirmation = findViewById(R.id.cb_confirmation_transaction);
        tvProductName = findViewById(R.id.tv_name_confirmation_transaction);

        ((TextView) findViewById(R.id.tv_title_toolbar_back)).setText(getString(R.string.confirmation));
        ((TextView) findViewById(R.id.tv_child_toolbar_back)).setVisibility(View.GONE);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(DATA_REKSA_DANA) && intent.hasExtra(DATA_TRANSACTION)) {
            String reksadana = intent.getStringExtra(DATA_REKSA_DANA);
            String transaksi = intent.getStringExtra(DATA_TRANSACTION);
            Gson gson = new Gson();
            this.detailReksaDana = gson.fromJson(reksadana, Product.DetailReksaDana.class);
            this.purchasing = gson.fromJson(transaksi, Transaction.Purchasing.class);
            tvProductName.setText(this.detailReksaDana.getName());
            tvTransactionType.setText(this.purchasing.getTransactionType());
            tvNominalPembelian.setText("Rp " + this.purchasing.getAmount());
            tvNominalBiayaPembelian.setText("Rp " + this.purchasing.getNominalBiayaPembelian() + " (" + this.detailReksaDana.getBiayaPembelian() + "%)");
            tvTotalPembelian.setText("Rp " + (Double.parseDouble(this.purchasing.getAmount()) + this.purchasing.getNominalBiayaPembelian()));
            tvRekeningSumberDana.setText(prefConfig.getAccountNumber());
        }

        imgBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        cbConfirmation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int backgroundColor, textColor;
                if (b) {
                    textColor = getResources().getColor(android.R.color.black);
                    backgroundColor = getResources().getColor(android.R.color.holo_orange_light);
                } else {
                    textColor = getResources().getColor(R.color.white_palette);
                    backgroundColor = getResources().getColor(android.R.color.darker_gray);
                }
                btnNext.setTextColor(textColor);
                btnNext.setBackgroundColor(backgroundColor);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
            case R.id.btn_next_transaction_confirmation:
                if (cbConfirmation.isChecked()) {
                    this.purchasing.setBcaID(prefConfig.getBCAID());
                    this.purchasing.setAccountNumber(prefConfig.getAccountNumber());

                    Intent intent = new Intent(this, PinActivity.class);

                    intent.putExtra(PinActivity.TRANSACTION_TYPE, FROM_CONFIRMATION_ACTIVITY);
                    intent.putExtra(PinActivity.PARCEL_DATA, Utils.toJSON(this.purchasing));

                    startActivity(intent);
                }
                break;
        }
    }
}
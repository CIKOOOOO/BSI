package com.bca.bsi.ui.basenavigation.transaction.confirmation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;
import com.bca.bsi.model.Transaction;
import com.bca.bsi.ui.pin_screen.PinActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Type;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ConfirmationTransactionActivity extends BaseActivity implements View.OnClickListener {

    public static final String DATA_REKSA_DANA = "dana_reksa_data";
    public static final String DATA_TRANSACTION = "transaction_data";
    public static final String PURCHASE_FROM_CONFIRMATION_ACTIVITY = "purchase_from_confirmation_activity";
    public static final String SELLING_FROM_CONFIRMATION_ACTIVITY = "selling_from_confirmation_activity";
    public static final String CONFIRMATION_TYPE = "confirmation_type";

    private TextView tvPaymentType, tvNominalPembelian, tvNominalBiayaPembelian, tvTotalPembelian, tvRekeningSumberDana, tvProductName;
    private CheckBox cbConfirmation;
    private Product.DetailReksaDana detailReksaDana;
    private Transaction.Purchasing purchasing;
    private ProductNameConfirmationAdapter productNameConfirmationAdapter;

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_confirmation);
        initVar();
    }

    private void initVar() {

        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar);
        final Button btnNext = findViewById(R.id.btn_next_transaction_confirmation);
        RecyclerView recycler_product = findViewById(R.id.recycler_product_confirmation_transaction);
        TextView tvJumlahUnitPenjualan = findViewById(R.id.tv_jumlah_unit_penjualan_confirmation);
        TextView tvBiayaPembelian = findViewById(R.id.tv_nominal_biaya_pembelian_confirmation);

        tvPaymentType = findViewById(R.id.tv_transaction_type_confirmation);
        tvRekeningSumberDana = findViewById(R.id.tv_rekening_sumber_dana_confirmation);
        cbConfirmation = findViewById(R.id.cb_confirmation_transaction);

        ((TextView) findViewById(R.id.tv_title_toolbar_back)).setText(getString(R.string.confirmation));
        ((TextView) findViewById(R.id.tv_child_toolbar_back)).setVisibility(View.GONE);

        productNameConfirmationAdapter = new ProductNameConfirmationAdapter();

        recycler_product.setLayoutManager(new LinearLayoutManager(this));
        recycler_product.setAdapter(productNameConfirmationAdapter);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(DATA_REKSA_DANA) && intent.hasExtra(DATA_TRANSACTION)
                && intent.hasExtra(CONFIRMATION_TYPE)) {
            String reksadana = intent.getStringExtra(DATA_REKSA_DANA);
            String transaksi = intent.getStringExtra(DATA_TRANSACTION);
            this.type = intent.getStringExtra(CONFIRMATION_TYPE);

            Gson gson = new Gson();
            List<Product.DetailReksaDana> detailReksaDanaList = new ArrayList<>();

            this.purchasing = gson.fromJson(transaksi, Transaction.Purchasing.class);

            if (type.equals(Type.PURCHASING_WITH_SMARTBOT)) {

            } else if (type.equals(Type.SELLING)) {
                this.detailReksaDana = gson.fromJson(reksadana, Product.DetailReksaDana.class);
                String biayaProdukPenjualan = detailReksaDana.getBiayaPenjualan().substring(0, 1).equals(".") ? "0" + detailReksaDana.getBiayaPenjualan() + "%" : Double.parseDouble(detailReksaDana.getBiayaPenjualan()) + "%";
                tvBiayaPembelian.setText(biayaProdukPenjualan);
                tvPaymentType.setText(this.purchasing.getPaymentType());
                tvRekeningSumberDana.setText(prefConfig.getAccountNumber());
                tvJumlahUnitPenjualan.setText(this.purchasing.getReksaDanaUnit());
                detailReksaDanaList.add(this.detailReksaDana);
                productNameConfirmationAdapter.setType(type);
                productNameConfirmationAdapter.setDetailReksaDanas(detailReksaDanaList);
                productNameConfirmationAdapter.notifyDataSetChanged();
            } else {
                this.detailReksaDana = gson.fromJson(reksadana, Product.DetailReksaDana.class);
                String paymentType = this.purchasing.getPaymentType().equals(Type.PEMBELIAN_BERKALA) ? "Pembelian Berkala" : "Pembelian Sekali Bayar";
                tvPaymentType.setText(paymentType);
                tvRekeningSumberDana.setText(prefConfig.getAccountNumber());
                detailReksaDanaList.add(this.detailReksaDana);

                List<Double> percentageList = new ArrayList<>();
                percentageList.add(1.0);

                productNameConfirmationAdapter.setDetailReksaDanas(detailReksaDanaList);
                productNameConfirmationAdapter.setPercentageList(percentageList);
                productNameConfirmationAdapter.setNominalPembelian(Double.parseDouble(this.purchasing.getAmount()));
                productNameConfirmationAdapter.notifyDataSetChanged();
            }
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

                    String transactionType = type.equals(Type.SELLING) ? SELLING_FROM_CONFIRMATION_ACTIVITY : PURCHASE_FROM_CONFIRMATION_ACTIVITY;

                    intent.putExtra(PinActivity.TRANSACTION_TYPE, transactionType);
                    intent.putExtra(PinActivity.PARCEL_DATA, Utils.toJSON(this.purchasing));

                    startActivity(intent);
                }
                break;
        }
    }
}
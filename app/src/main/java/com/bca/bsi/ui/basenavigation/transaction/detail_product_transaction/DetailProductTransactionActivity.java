package com.bca.bsi.ui.basenavigation.transaction.detail_product_transaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;
import com.bca.bsi.ui.basenavigation.more.CalculatorMore.CalculatorMoreActivity;
import com.bca.bsi.ui.basenavigation.products.detail.reksadana.CalculatorProductActivity;
import com.bca.bsi.ui.basenavigation.transaction.confirmation.ConfirmationTransactionActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Constant;
import com.bca.bsi.utils.constant.Type;
import com.google.gson.Gson;

public class DetailProductTransactionActivity extends BaseActivity implements IDetailProductTransactionCallback, View.OnClickListener {

    public static final String PRODUCT_TYPE = "type";
    public static final String SALES_TYPE = "sales_type";
    public static final String DATA = "data";

    private Product.ReksaDana reksaDana;
    private TextView tvSaldo;
    private DetailProductTransactionViewModel viewModel;

    private String productType, salesType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product_transaction);
        initVar();
    }

    private void initVar() {

        TextView tvProductName = findViewById(R.id.tv_name_detail_transaction);
        TextView tvAccountNumber = findViewById(R.id.tv_nomor_rekening_detail_transaction);
        TextView tvPrice = findViewById(R.id.tv_price_detail_transaction);
        TextView tvDate = findViewById(R.id.tv_date_detail_transaction);
        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar);
        TextView tvTitleToolbar = findViewById(R.id.tv_title_toolbar_back);
        Button btnNext = findViewById(R.id.btn_next_detail_transaction);
        TextView toc = findViewById(R.id.ketentuan_pembelian);

        toc.setOnClickListener(this);

        tvSaldo = findViewById(R.id.tv_saldo_detail_transaction);

        Gson gson = new Gson();

        viewModel = new ViewModelProvider(this).get(DetailProductTransactionViewModel.class);
        viewModel.setCallback(this);
        viewModel.getSaldo(prefConfig.getAccountNumber());

        findViewById(R.id.tv_child_toolbar_back).setVisibility(View.GONE);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(PRODUCT_TYPE) && intent.hasExtra(SALES_TYPE)
                && intent.hasExtra(DATA)) {
            productType = intent.getStringExtra(PRODUCT_TYPE);
            salesType = intent.getStringExtra(SALES_TYPE);
            String product = intent.getStringExtra(DATA);

            if (productType.equals(Type.REKSA_DANA)) {
                this.reksaDana = gson.fromJson(product, Product.ReksaDana.class);

                tvProductName.setText(this.reksaDana.getName());
                tvAccountNumber.setText(prefConfig.getAccountNumber());
                tvPrice.setText("Rp. " + this.reksaDana.getNab() + "\nNAB/Unit");
                tvDate.setText(Utils.getTime(Constant.DATE_FORMAT_1));
            }

            if (salesType.equals(Type.PURCHASING)) {
                tvTitleToolbar.setText(getString(R.string.purchasing));
            }
        }

        imgBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void loadSaldo(double saldo) {
        tvSaldo.setText("Rp " + Utils.priceFormat(saldo));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
            case R.id.btn_next_detail_transaction:
                startActivity(new Intent(DetailProductTransactionActivity.this, ConfirmationTransactionActivity.class));
                break;
            case R.id.ketentuan_pembelian:
                Intent intent = new Intent(this, CalculatorProductActivity.class);
                startActivity(intent);
                break;
        }
    }
}
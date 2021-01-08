package com.bca.bsi.ui.basenavigation.transaction.detail_product_transaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;
import com.bca.bsi.model.Transaction;
import com.bca.bsi.model.User;
import com.bca.bsi.ui.basenavigation.products.detail.reksadana.CalculatorProductActivity;
import com.bca.bsi.ui.basenavigation.transaction.confirmation.ConfirmationTransactionActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Type;
import com.google.gson.Gson;

public class DetailProductTransactionActivity extends BaseActivity implements IDetailProductTransactionCallback, View.OnClickListener {

    public static final String PRODUCT_TYPE = "type";
    public static final String SALES_TYPE = "sales_type";
    public static final String DATA = "data";

    private Product.ReksaDana reksaDana;
    private TextView tvSaldo, tvProductName, tvPrice, tvDate;
    private DetailProductTransactionViewModel viewModel;
    private EditText etNominalPembelian;
    private Product.DetailReksaDana detailReksaDana;
    private CheckBox cbPaymentType;

    private String productType, salesType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product_transaction);
        initVar();
    }

    private void initVar() {

        TextView tvAccountNumber = findViewById(R.id.tv_nomor_rekening_detail_transaction);
        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar);
        TextView tvTitleToolbar = findViewById(R.id.tv_title_toolbar_back);
        Button btnNext = findViewById(R.id.btn_next_detail_transaction);
        TextView toc = findViewById(R.id.ketentuan_pembelian);

        cbPaymentType = findViewById(R.id.cb_berkala_detail_transaction);

        toc.setOnClickListener(this);

        tvSaldo = findViewById(R.id.tv_saldo_detail_transaction);
        etNominalPembelian = findViewById(R.id.et_nominal_pembelian_detail_transaction);
        tvPrice = findViewById(R.id.tv_price_detail_transaction);
        tvDate = findViewById(R.id.tv_date_detail_transaction);
        tvProductName = findViewById(R.id.tv_name_detail_transaction);

        Gson gson = new Gson();

        viewModel = new ViewModelProvider(this).get(DetailProductTransactionViewModel.class);
        viewModel.setCallback(this);

        findViewById(R.id.tv_child_toolbar_back).setVisibility(View.GONE);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(PRODUCT_TYPE) && intent.hasExtra(SALES_TYPE)
                && intent.hasExtra(DATA)) {
            productType = intent.getStringExtra(PRODUCT_TYPE);
            salesType = intent.getStringExtra(SALES_TYPE);
            String product = intent.getStringExtra(DATA);

            tvAccountNumber.setText(prefConfig.getAccountNumber());

            if (productType.equals(Type.REKSA_DANA)) {
                this.reksaDana = gson.fromJson(product, Product.ReksaDana.class);
                viewModel.loadDetailTransaksi(prefConfig.getAccountNumber(), this.reksaDana.getReksadanaID());
            }

            if (salesType.equals(Type.PURCHASING)) {
                tvTitleToolbar.setText(getString(R.string.purchasing));
            }
        }

        imgBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
            case R.id.btn_next_detail_transaction:
                String nominal = etNominalPembelian.getText().toString().trim();
                if (nominal.isEmpty()) {
                    showSnackBar("Mohon isi nominal pembelian");
                } else if (this.detailReksaDana == null) {
                    onResume();
                } else if (Double.parseDouble(this.detailReksaDana.getMinimumPembelianPertama()) < Double.parseDouble(nominal)) {
                    showSnackBar("Jumlah nominal pembelian lebih kecil daripada minimum pembelian pertama");
                } else {
                    String type = cbPaymentType.isChecked() ? Type.PEMBELIAN_BERKALA : Type.PEMBELIAN_SEKALI_BAYAR;
                    double biayaPembelian = Double.parseDouble(this.detailReksaDana.getBiayaPembelian()) * Double.parseDouble(nominal);
                    double reksaDanaUnit = Double.parseDouble(nominal) / Double.parseDouble(this.detailReksaDana.getNabSatuBulan());

                    Transaction.Purchasing purchasing = new Transaction.Purchasing();
                    purchasing.setPaymentType(Type.PURCHASING);
                    purchasing.setAmount(nominal);
                    purchasing.setPaymentType(type);
                    purchasing.setNominalBiayaPembelian(biayaPembelian);
                    purchasing.setReksaDanaID(this.detailReksaDana.getReksadanaID());
                    purchasing.setReksaDanaUnit(String.valueOf(reksaDanaUnit));

                    Intent intent = new Intent(this, ConfirmationTransactionActivity.class);
                    intent.putExtra(ConfirmationTransactionActivity.DATA_TRANSACTION, Utils.toJSON(purchasing));
                    intent.putExtra(ConfirmationTransactionActivity.DATA_REKSA_DANA, Utils.toJSON(this.detailReksaDana));
                    startActivity(intent);
                }
                break;
            case R.id.ketentuan_pembelian:
                Intent intent = new Intent(this, CalculatorProductActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void loadSaldo(User.BCAUser bcaUser, Product.DetailReksaDana detailReksaDana) {
        this.detailReksaDana = detailReksaDana;

        tvSaldo.setText(bcaUser.getSaldo());

        tvProductName.setText(detailReksaDana.getName());
        tvPrice.setText("Rp. " + detailReksaDana.getNabSatuBulan() + "\nNAB/Unit");
        tvDate.setText(detailReksaDana.getUpdateDate());

    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }
}
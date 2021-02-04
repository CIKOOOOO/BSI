package com.bca.bsi.ui.basenavigation.transaction.sell_transaction;

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
import com.bca.bsi.model.Portfolio;
import com.bca.bsi.model.Product;
import com.bca.bsi.model.Transaction;
import com.bca.bsi.ui.basenavigation.transaction.confirmation.ConfirmationTransactionActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Constant;
import com.bca.bsi.utils.constant.Type;
import com.google.gson.Gson;

import java.text.ParseException;

public class DetailSellActivity extends BaseActivity implements View.OnClickListener, IDetailSellCallback {

    public static final String DATA = "data";

    private EditText etNominal;
    private CheckBox cbSellAll;
    private DetailSellViewModel viewModel;
    private Product.DetailReksaDana detailReksaDana;
    private String reksadanaID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sell);
        initVar();
    }

    private void initVar() {
        TextView tvTitle = findViewById(R.id.tv_title_toolbar_back);
        TextView tvReksadanaName = findViewById(R.id.recycler_tv_name_detail_transaction);
        TextView tvDate = findViewById(R.id.recycler_tv_date_detail_transaction);
        TextView tvNab = findViewById(R.id.recycler_tv_price_detail_transaction);
        TextView tvAccountNumber = findViewById(R.id.tv_nomor_rekening_detail_sell);
        TextView tvJumlahUnit = findViewById(R.id.tv_jumlah_unit_detail_sell);
        Button btnSell = findViewById(R.id.btn_next_detail_sell);
        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar);

        etNominal = findViewById(R.id.et_nominal_penjualan_detail_sell);
        cbSellAll = findViewById(R.id.cb_sell_all_detail_transaction);

        viewModel = new ViewModelProvider(this).get(DetailSellViewModel.class);
        viewModel.setCallback(this);

        tvTitle.setText(getString(R.string.selling));
        findViewById(R.id.tv_child_toolbar_back).setVisibility(View.GONE);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(DATA)) {
            String data = intent.getStringExtra(DATA);
            Gson gson = new Gson();
            Portfolio.Information information = gson.fromJson(data, Portfolio.Information.class);
            tvReksadanaName.setText(information.getName());
            tvNab.setText("Rp " + information.getNab() + "\nNAB/Unit");
            tvAccountNumber.setText(prefConfig.getAccountNumber());
            tvJumlahUnit.setText(String.valueOf(information.getUnit()));
            viewModel.loadData(prefConfig.getTokenUser(), information.getReksadanaID());
            this.reksadanaID = information.getReksadanaID();

            try {
                String date = Utils.formatDateFromDateString(Constant.DATE_FORMAT_3, Constant.DATE_FORMAT_2, information.getDate());
                tvDate.setText(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        btnSell.setOnClickListener(this);
        imgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
            case R.id.btn_next_detail_sell:
                String amount = etNominal.getText().toString().trim();
                if (amount.isEmpty()) {
                    showSnackBar("Mohon isi jumlah unit penjualan");
                } else if (null == this.detailReksaDana) {
                    viewModel.loadData(prefConfig.getTokenUser(), reksadanaID);
                } else {
                    double nominal = Double.parseDouble(amount) * Double.parseDouble(this.detailReksaDana.getNabPerUnit());
                    String type = cbSellAll.isChecked() ? "Penjualan Semua" : "Penjualan Sebagian";
                    String biayaProdukPenjualan = detailReksaDana.getBiayaPenjualan().substring(0, 1).equals(".") ? "0" + detailReksaDana.getBiayaPenjualan() : String.valueOf(Double.parseDouble(detailReksaDana.getBiayaPenjualan()));

                    double biayaPenjualan = biayaProdukPenjualan.equals("0") ? 0 : (Double.parseDouble(biayaProdukPenjualan) / 100) * nominal;

                    Transaction.Purchasing purchasing = new Transaction.Purchasing();
                    purchasing.setTransactionType(Type.SELLING);
                    purchasing.setAmount(String.valueOf(nominal));
                    purchasing.setPaymentType(type);
                    purchasing.setNominalBiayaPembelian(String.format("%.2f", biayaPenjualan));
                    purchasing.setReksaDanaID(this.detailReksaDana.getReksadanaID());
                    purchasing.setReksaDanaUnit(amount);
                    purchasing.setNab(String.valueOf(this.detailReksaDana.getNabPerUnit()));

                    Intent intent = new Intent(this, ConfirmationTransactionActivity.class);
                    intent.putExtra(ConfirmationTransactionActivity.DATA_TRANSACTION, Utils.toJSON(purchasing));
                    intent.putExtra(ConfirmationTransactionActivity.DATA_REKSA_DANA, Utils.toJSON(this.detailReksaDana));
                    intent.putExtra(ConfirmationTransactionActivity.CONFIRMATION_TYPE, Type.SELLING);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onLoadData(Product.DetailReksaDana detailReksaDana) {
        this.detailReksaDana = detailReksaDana;
    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }
}
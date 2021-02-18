package com.bca.bsi.ui.basenavigation.portfolio.purchasing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Portfolio;
import com.bca.bsi.model.ProductRekomen;
import com.bca.bsi.ui.basenavigation.transaction.detail_product_transaction.DetailProductTransactionActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Type;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PurchasingSmartbotActivity extends BaseActivity implements IPurchasingSmartbotCallback, View.OnClickListener, PurchasingSmartbotAdapter.onEventMatch {

    ImageButton rekomenRoboButton;
    ConstraintLayout roboHitungPopupLayout;
    ImageButton clearButton, backToolbarButton;
    TextView toolbarTitle, toolbarSubtitle, minPembelian, tvReturn, tvRisk, tvHitungSekarang;
    PurchasingSmartbotAdapter adapter;
    RecyclerView recyclerView;
    EditText etNominal;
    Portfolio portfolio;
    PurchasingSmartbotViewModel viewModel;
    private CheckBox cbAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchasing_smartbot);
        initVar();
    }

    private void initVar() {

        TextView tvLanjut = findViewById(R.id.tv_lanjut);

        rekomenRoboButton = findViewById(R.id.ib_rekomen_robo);
        roboHitungPopupLayout = findViewById(R.id.popup_rekomen_robo);
        clearButton = findViewById(R.id.ib_clear2);
        minPembelian = findViewById(R.id.tv_min_pembelian_value_p);
        etNominal = findViewById(R.id.et_nominal_pembelian_robo);
        tvReturn = findViewById(R.id.tv_return_val);
        tvRisk = findViewById(R.id.tv_risk_val);
        cbAgreement = findViewById(R.id.cb_confirmation_purchasing_smartbot);
        tvHitungSekarang = findViewById(R.id.tv_hitungsekarang);

        // inisialisasi adapter dan recycler
        adapter = new PurchasingSmartbotAdapter(this);
        recyclerView = findViewById(R.id.recycler_product_main_p);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(PurchasingSmartbotViewModel.class);
        viewModel.setCallback(this);

        // Atur nominal gbs depannya 0
        etNominal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char zero = '0';
                if (etNominal.getText().length() == 2 && etNominal.getText().charAt(0) == zero) {
                    etNominal.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Terima data dari bottom sheet Bundle atau Custom
        Intent intent = getIntent();
        List<ProductRekomen> productRekomenList;
        if (intent != null && intent.hasExtra("data")) {
            //ini kalau dari bundle
            String hasil = intent.getStringExtra("data");
            Gson gson = new Gson();
            portfolio = gson.fromJson(hasil, Portfolio.class);
            minPembelian.setText(Utils.formatUang3(Double.parseDouble(portfolio.getMinPurchase())));
//            minPembelian.setText(portfolio.getMinPurchase());
            tvReturn.setText(portfolio.getExpReturn() + "%");
            tvRisk.setText(portfolio.getRisk());
            adapter.setProductRekomenList(portfolio.getProductRekomenList());
        } else {
            // ini kalau custom
            String hasil = intent.getStringExtra("data2");
            //hit API dari sini
            viewModel.loadBundle(prefConfig.getTokenUser(), prefConfig.getAccountNumber(), hasil, "");
        }

        //Toolbar variables
        backToolbarButton = findViewById(R.id.img_btn_back_toolbar);
        toolbarTitle = findViewById(R.id.tv_title_toolbar_back);
        toolbarSubtitle = findViewById(R.id.tv_child_toolbar_back);

        //Toolbar
        toolbarTitle.setText("PEMBELIAN");
        toolbarSubtitle.setVisibility(View.GONE);
        backToolbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Klik floating robo rekomen
        rekomenRoboButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roboHitungPopupLayout.setVisibility(View.VISIBLE);
            }
        });

        //klik clear popup button x
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roboHitungPopupLayout.setVisibility(View.GONE);
            }
        });

        // Do something with adapter
        tvLanjut.setOnClickListener(this);
        // klik hitung sekarang
        tvHitungSekarang.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.loadBundle(prefConfig.getTokenUser(), prefConfig.getAccountNumber(), adapter.getReksaIds(), "");
                roboHitungPopupLayout.setVisibility(View.GONE);
            }
        }));
    }


    @Override
    public void onLoadData(List<Portfolio> bundles) {
        this.portfolio = bundles.get(0);
        adapter.setProductRekomenList(portfolio.getProductRekomenList());
        adapter.notifyDataSetChanged();
        minPembelian.setText(Utils.formatUang3(Double.parseDouble(portfolio.getMinPurchase())));
//        minPembelian.setText(portfolio.getMinPurchase());
        tvReturn.setText(portfolio.getExpReturn() + "%");
        tvRisk.setText(portfolio.getRisk());
    }

    @Override
    public void onLoadDataCustom(List<Portfolio> bundles) {
        this.portfolio = bundles.get(0);
        minPembelian.setText(Utils.formatUang3(Double.parseDouble(portfolio.getMinPurchase())));
//        minPembelian.setText(portfolio.getMinPurchase());
        tvReturn.setText(portfolio.getExpReturn() + "%");
        tvRisk.setText(portfolio.getRisk());
    }

    @Override
    public void onFail(String msg) {
        showSnackBar(msg);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_lanjut:
                String nominal = etNominal.getText().toString().trim();
                if (nominal.isEmpty()) {
                    showSnackBar("Mohon isi nominal pembelian");
                } else if (Double.parseDouble(nominal) < Double.parseDouble(this.portfolio.getMinPurchase())) {
                    showSnackBar("Nominal pembelian harus lebih besar atau sama dengan minimal pembelian");
                } else if (!adapter.is100()) {
                    showSnackBar("Pembelian produk harus 100%");
                } else if (cbAgreement.isChecked()) {
                    Intent intent = new Intent(this, DetailProductTransactionActivity.class);
                    intent.putExtra(DetailProductTransactionActivity.PRODUCT_TYPE, Type.PURCHASING_WITH_SMARTBOT);
                    intent.putExtra(DetailProductTransactionActivity.SALES_TYPE, Type.PURCHASING);
                    intent.putExtra(DetailProductTransactionActivity.NOMINAL_PEMBELIAN, nominal);
                    intent.putParcelableArrayListExtra(DetailProductTransactionActivity.DATA, (ArrayList<? extends Parcelable>) adapter.getProductRekomenList());
                    startActivity(intent);
                } else {
                    showSnackBar("Mohon setuju pada syarat dan ketentuan");
                }
                break;
        }
    }

    @Override
    public void sendValue(String reksaDanaID, String proportion) {
        if (viewModel != null) {
            viewModel.loadBundleCustom(prefConfig.getTokenUser(), prefConfig.getAccountNumber(), reksaDanaID, proportion);
        }
    }
}
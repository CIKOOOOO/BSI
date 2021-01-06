package com.bca.bsi.ui.basenavigation.products.detail.reksadana.detailreksadana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;
import com.bca.bsi.utils.BaseActivity;

public class DetailReksaDanaActivity extends BaseActivity implements View.OnClickListener, IDetailReksaDanaCallback {

    public static final String REKSA_DANA_ID = "reksa_dana_id";

    private DetailReksaDanaViewModel viewModel;
    private TextView tvProductName, tvDate, tvNAB, tvManagerInvest, tvKustodianBank, tvTypeReksaDana, tvReleaseDate, tvFirstMinimumPurchasing, tvNextMinimumPurchasing, tvSellingMinimum, tvMinimumSaldoUnit, tvPurcashingCost, tvSellingCost, tvInvestManagementCost, tvCustodianCost, tvAgentCost, tvKalkulatorPerencanaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reksa_dana);
        initVar();
    }

    private void initVar() {
        TextView tvTitle = findViewById(R.id.tv_title_toolbar_back);
        TextView tvChild = findViewById(R.id.tv_child_toolbar_back);
        ImageButton imgBack = findViewById(R.id.img_btn_back_toolbar);

        tvProductName = findViewById(R.id.tv_name_detail_reksa_dana);
        tvDate = findViewById(R.id.tv_date_detail_reksa_dana);
        tvNAB = findViewById(R.id.tv_price_detail_reksa_dana);
        tvManagerInvest = findViewById(R.id.tv_invest_manager_detail_reksa_dana);
        tvKustodianBank = findViewById(R.id.tv_kustodian_bank_detail_reksa_dana);
        tvTypeReksaDana = findViewById(R.id.tv_type_detail_reksa_dana);
        tvReleaseDate = findViewById(R.id.tv_release_date_detail_reksa_dana);
        tvFirstMinimumPurchasing = findViewById(R.id.tv_first_minimum_purchasing_detail_reksa_dana);
        tvNextMinimumPurchasing = findViewById(R.id.tv_next_purchasing_minimum_detail_reksa_dana);
        tvSellingMinimum = findViewById(R.id.tv_selling_minimum_detail_reksa_dana);
        tvMinimumSaldoUnit = findViewById(R.id.tv_minimum_saldo_unit_detail_reksa_dana);
        tvPurcashingCost = findViewById(R.id.tv_purchasing_cost_detail_reksa_dana);
        tvSellingCost = findViewById(R.id.tv_selling_cost_detail_reksa_dana);
        tvInvestManagementCost = findViewById(R.id.tv_cost_invest_management_detail_reksa_dana);
        tvCustodianCost = findViewById(R.id.tv_cost_kustodian_detail_reksa_dana);
        tvAgentCost = findViewById(R.id.tv_cost_agent_detail_reksa_dana);
        tvKalkulatorPerencanaan = findViewById(R.id.tv_kalkulator_perencanaan_detail_reksa_dana);

        viewModel = new ViewModelProvider(this).get(DetailReksaDanaViewModel.class);
        viewModel.setCallback(this);

        tvTitle.setText(getString(R.string.detail_product));
        tvChild.setVisibility(View.GONE);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(REKSA_DANA_ID)) {
            String reksaDanaID = intent.getStringExtra(REKSA_DANA_ID);
            viewModel.loadDetailReksaDana(reksaDanaID);
        }

        imgBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onLoadReksaDanaDetail(Product.DetailReksaDana detailReksaDana) {

    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }
}
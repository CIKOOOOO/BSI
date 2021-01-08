package com.bca.bsi.ui.basenavigation.products.detail.reksadana.detailreksadana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.dummydata.DummyData;

public class DetailReksaDanaActivity extends BaseActivity implements View.OnClickListener, IDetailReksaDanaCallback {

    public static final String REKSA_DANA_ID = "reksa_dana_id";

    private DetailReksaDanaViewModel viewModel;
    private ReksaDanaPerformanceAdapter danaPerformanceAdapter;
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
        RecyclerView recycler_performance = findViewById(R.id.recycler_kinerja_produk_detail_reksa_dana);

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

        danaPerformanceAdapter = new ReksaDanaPerformanceAdapter();

        viewModel = new ViewModelProvider(this).get(DetailReksaDanaViewModel.class);
        viewModel.setCallback(this);

        recycler_performance.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recycler_performance.setAdapter(danaPerformanceAdapter);

        tvTitle.setText(getString(R.string.detail_product));
        tvChild.setVisibility(View.GONE);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(REKSA_DANA_ID)) {
            String reksaDanaID = intent.getStringExtra(REKSA_DANA_ID);
            viewModel.loadDetailReksaDana(reksaDanaID);
        }

        imgBack.setOnClickListener(this);
        tvKalkulatorPerencanaan.setOnClickListener(this);


        danaPerformanceAdapter.setPerformanceList(DummyData.getPerformanceList());
        danaPerformanceAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_back_toolbar:
                onBackPressed();
                break;
            case R.id.tv_kalkulator_perencanaan_detail_reksa_dana:
                Toast.makeText(this, "kalkulator perencanaan", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onLoadReksaDanaDetail(Product.DetailReksaDana detailReksaDana) {
        tvProductName.setText(detailReksaDana.getName());
        tvDate.setText(detailReksaDana.getUpdateDate());

        String nab = detailReksaDana.getNabSatuBulan() + "\n NAB/Unit";
        tvNAB.setText(nab);

        tvManagerInvest.setText(detailReksaDana.getManagerInvestasiID());
        tvTypeReksaDana.setText(detailReksaDana.getProductCategory());
        tvReleaseDate.setText(detailReksaDana.getReleaseDate());
        tvFirstMinimumPurchasing.setText(detailReksaDana.getMinimumPembelianPertama());
        tvNextMinimumPurchasing.setText(detailReksaDana.getMinimumPembelianBerikut());
        tvSellingMinimum.setText(detailReksaDana.getMininumPenjualan());
        tvMinimumSaldoUnit.setText(detailReksaDana.getMinimumSaldoUnit());
        tvPurcashingCost.setText(detailReksaDana.getBiayaPembelian());
        tvSellingCost.setText(detailReksaDana.getBiayaPenjualan());
        tvInvestManagementCost.setText(detailReksaDana.getBiayaManajerInvestasi());
        tvCustodianCost.setText(detailReksaDana.getBiayaBankKustodian());
        tvAgentCost.setText(detailReksaDana.getBiayaAgen());

        // here
        tvKustodianBank.setText(detailReksaDana.getBankKustodianID());

//        danaPerformanceAdapter.setPerformanceList(DummyData.getPerformanceList());
//        danaPerformanceAdapter.notifyDataSetChanged();

    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }
}
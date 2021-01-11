package com.bca.bsi.ui.basenavigation.products.detail.reksadana.detailreksadana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;
import com.bca.bsi.ui.basenavigation.transaction.detail_product_transaction.DetailProductTransactionActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Constant;
import com.bca.bsi.utils.constant.Type;

import java.text.ParseException;
import java.util.List;

public class DetailReksaDanaActivity extends BaseActivity implements View.OnClickListener, IDetailReksaDanaCallback {

    public static final String REKSA_DANA_ID = "reksa_dana_id";

    private DetailReksaDanaViewModel viewModel;
    private ReksaDanaPerformanceAdapter danaPerformanceAdapter;
    private TextView tvProductName, tvDate, tvNAB, tvManagerInvest, tvKustodianBank, tvTypeReksaDana, tvReleaseDate, tvFirstMinimumPurchasing, tvNextMinimumPurchasing, tvSellingMinimum, tvMinimumSaldoUnit, tvPurcashingCost, tvSellingCost, tvInvestManagementCost, tvCustodianCost, tvAgentCost, tvKalkulatorPerencanaan;

    private Product.DetailReksaDana detailReksaDana;

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
        Button btnBuy = findViewById(R.id.btn_buy_reksa_dana_detail);

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
            if (reksaDanaID != null)
                viewModel.loadDetailReksaDana(Integer.parseInt(reksaDanaID));
        }

        imgBack.setOnClickListener(this);
        tvKalkulatorPerencanaan.setOnClickListener(this);
        btnBuy.setOnClickListener(this);
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
            case R.id.btn_buy_reksa_dana_detail:
                if (this.detailReksaDana != null) {
                    Intent intent = new Intent(this, DetailProductTransactionActivity.class);
                    intent.putExtra(DetailProductTransactionActivity.PRODUCT_TYPE, Type.REKSA_DANA);
                    intent.putExtra(DetailProductTransactionActivity.SALES_TYPE, Type.PURCHASING);
                    intent.putExtra(DetailProductTransactionActivity.DATA, Utils.toJSON(this.detailReksaDana));
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onLoadReksaDanaDetail(Product.DetailReksaDana detailReksaDana, List<Product.Performance> performanceList) {
        this.detailReksaDana = detailReksaDana;

        tvProductName.setText(detailReksaDana.getName());

        String nab = detailReksaDana.getNabSatuBulan() + "\n NAB/Unit";
        String purchasingCost = detailReksaDana.getBiayaPembelian().substring(0, 1).equals(".") ? "0" + detailReksaDana.getBiayaPembelian() : detailReksaDana.getBiayaPembelian();
        String biayaAgenPenjual = detailReksaDana.getBiayaAgen() == null ? "Rp N/A" : "Rp " + Utils.priceFormat(Double.parseDouble(detailReksaDana.getBiayaAgen()));

        tvPurcashingCost.setText(purchasingCost + "%");
        tvNAB.setText(nab);
        tvAgentCost.setText(biayaAgenPenjual);

        try {
            String updateDate = Utils.formatDateFromDateString(Constant.DATE_FORMAT_FROM_DB, Constant.DATE_FORMAT_2, detailReksaDana.getUpdateDate());
            String releaseDate = Utils.formatDateFromDateString(Constant.DATE_FORMAT_FROM_DB, Constant.DATE_FORMAT_1, detailReksaDana.getReleaseDate());
            tvReleaseDate.setText(releaseDate);
            tvDate.setText(updateDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tvTypeReksaDana.setText(detailReksaDana.getProductCategory());
        tvFirstMinimumPurchasing.setText("Rp " + Utils.priceFormat(Double.parseDouble(detailReksaDana.getMinimumPembelianPertama())));
        tvNextMinimumPurchasing.setText("Rp " + Utils.priceFormat(Double.parseDouble(detailReksaDana.getMinimumPembelianBerikut())));
        tvSellingMinimum.setText("Rp " + Utils.priceFormat(Double.parseDouble(detailReksaDana.getMininumPenjualan())));
        tvMinimumSaldoUnit.setText(detailReksaDana.getMinimumSaldoUnit());
        tvSellingCost.setText(detailReksaDana.getBiayaPenjualan());
        tvInvestManagementCost.setText(detailReksaDana.getBiayaManajerInvestasi());
        tvCustodianCost.setText(detailReksaDana.getBiayaBankKustodian());
        tvKustodianBank.setText(detailReksaDana.getBankKustodian());

        //here
        tvManagerInvest.setText(detailReksaDana.getManagerInvestasiID());

        danaPerformanceAdapter.setPerformanceList(performanceList);
        danaPerformanceAdapter.notifyDataSetChanged();

    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }
}
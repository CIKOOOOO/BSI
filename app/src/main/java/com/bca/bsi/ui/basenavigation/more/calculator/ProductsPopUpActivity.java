package com.bca.bsi.ui.basenavigation.more.calculator;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bca.bsi.model.Product;
import com.bca.bsi.ui.basenavigation.more.calculator.besarinvastasibulanan.IBesarInvestasiBulananCallback;
import com.bca.bsi.ui.basenavigation.more.calculator.popup.IProductsCalculatorCallback;
import com.bca.bsi.ui.basenavigation.more.calculator.popup.ProductsCalculatorAdapter;
import com.bca.bsi.ui.basenavigation.more.calculator.popup.ProductsCalculatorViewModel;
import com.bca.bsi.ui.basenavigation.products.detail.reksadana.ReksadanaProductAdapter;
import com.bca.bsi.ui.basenavigation.transaction.detail_product_transaction.DetailProductTransactionActivity;
import com.bca.bsi.utils.BaseActivity;

import com.bca.bsi.R;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Type;

import java.util.List;

public class ProductsPopUpActivity extends BaseActivity implements IProductsCalculatorCallback, ProductsCalculatorAdapter.onItemClick, IBesarInvestasiBulananCallback {

    private TextView tvToolbarSubtitle;
    private TextView tvToolbarTitle;
    private ImageButton backButton;
    private RecyclerView recyclerView;
    private ProductsCalculatorViewModel viewModel;
    private ProductsCalculatorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_pop_up);

        tvToolbarSubtitle = findViewById(R.id.tv_child_toolbar_back);
        tvToolbarTitle = findViewById(R.id.tv_title_toolbar_back);
        backButton = findViewById(R.id.img_btn_back_toolbar);
        recyclerView = findViewById(R.id.recycler_produk_choice_main);

        backButton.setVisibility(View.GONE);
        tvToolbarTitle.setText("PILIH PRODUK REKSA DANA");
        tvToolbarSubtitle.setText("PILIH PRODUK REKSA DANA");
        tvToolbarSubtitle.setVisibility(View.GONE);

        viewModel = new ViewModelProvider(this).get(ProductsCalculatorViewModel.class);
        viewModel.setCallbackProduct(this);
        viewModel.setCallbackBesarInvestasi(this);
        viewModel.loadProducts(prefConfig.getTokenUser(), prefConfig.getProfileRisiko());

        /*
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        */

        adapter = new ProductsCalculatorAdapter((ProductsCalculatorAdapter.onItemClick) this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//
//        int width = displayMetrics.widthPixels;
//        int height = displayMetrics.heightPixels;
//
//        getWindow().setLayout((int) (width * .95), (int) (height * .92));
//
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.gravity = Gravity.CENTER;
//        params.x = 0;
//        params.y = -20;
//
//        getWindow().setAttributes(params);
    }

    @Override
    public void onLoadData(List<Product.ReksaDana> products) {
        //adapter.setProductsCalculatorAdapter(products);
        adapter.setProductsCalculatorAdapter(null);
    }

    @Override
    public void onFail(String msg) {
        showSnackBar(msg);
    }

    @Override
    public void onLoadReksaDanaDetail(Product.DetailReksaDana detailReksaDana, List<Product.Performance> performances) {

    }

    @Override
    public void kalkulasiOutput(String formatTargetHasilInvest, String formatModalAwal, String formatRoR, String formatHasil) {

    }

    @Override
    public void resultOf(List<Product.ReksaDana> reksaDanaList) {

    }

    @Override
    public void onFailed(String msg) {

    }

    @Override
    public void onSessionExpired() {
        sessionExpired();
    }

    /*
    @Override
    public void retrieveDetailReksaDana(Product.DetailReksaDana detailReksaDana) {

    }
    */

    @Override
    public void onReksaDanaClick(Product.ReksaDana reksaDana) {
        System.out.println("INIIII YANG INI YANG KEPILIH REKSADANANYAAAA : "+reksaDana.toString());
    }
}
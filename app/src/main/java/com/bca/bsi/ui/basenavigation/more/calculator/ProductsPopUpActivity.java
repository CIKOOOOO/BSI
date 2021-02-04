package com.bca.bsi.ui.basenavigation.more.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bca.bsi.model.Product;
import com.bca.bsi.ui.basenavigation.portfolio.produkmenu.ProdukChoiceViewModel;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.BaseFragment;

import com.bca.bsi.R;

import java.util.List;

public class ProductsPopUpActivity extends BaseActivity implements IProductsCalculatorCallback {

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
        tvToolbarTitle.setText(R.string.kalkulator_investasi);
        tvToolbarSubtitle.setText("PILIH PRODUK REKSA DANA");

        viewModel = new ViewModelProvider(this).get(ProductsCalculatorViewModel.class);
        viewModel.setCallback(this);
        viewModel.loadProducts(prefConfig.getProfileRisiko());

        /*
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        */

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);
    }

    @Override
    public void onLoadData(List<Product.ReksaDana> products) {
        adapter.setProductsCalculatorAdapter(products);
    }

    @Override
    public void onFail(String msg) {
        showSnackBar(msg);
    }
}
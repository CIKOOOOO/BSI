package com.bca.bsi.ui.basenavigation.portfolio.produkmenu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;
import com.bca.bsi.ui.basenavigation.portfolio.filter.FilterSortActivity;
import com.bca.bsi.ui.basenavigation.portfolio.purchasing.PurchasingSmartbotActivity;
import com.bca.bsi.utils.BaseActivity;

import java.util.List;

public class ProdukChoiceActivity extends BaseActivity implements IProductChoiceCallback {
    ProdukChoiceAdapter produkChoiceAdapter;
    TextView tvLanjut, tvToolbarTitle, tvToolbarSubtitle;
    ImageButton backButton, filterButton;
    ProdukChoiceViewModel viewModel;

    int sortPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_choice);
        initVar();

        // Set Toolbar Title and Subtitle
        tvToolbarTitle.setText("SMARTBOT");
        tvToolbarSubtitle.setText("PILIH PRODUK REKSA DANA");

        tvLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(produkChoiceAdapter.getChoosenAmount()<2){
                    showSnackBar("Pilihan minimal sebanyak dua produk !");
                } else {
                    Intent intent = new Intent(v.getContext(), PurchasingSmartbotActivity.class);
                    intent.putExtra("data2",produkChoiceAdapter.getReksaIds());
                    v.getContext().startActivity(intent);
                }
            }
        });

        // Back Button on Toolbar
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initVar() {
        RecyclerView rec = findViewById(R.id.recycler_produk_choice_main);
        produkChoiceAdapter = new ProdukChoiceAdapter();
//        produkChoiceAdapter.setProducts(DummyData.getProductChoiceList());
        viewModel = new ViewModelProvider(this).get(ProdukChoiceViewModel.class);
        viewModel.setCallback(this);
        viewModel.loadProducts(prefConfig.getProfileRisiko());

        // Toolbar variables
        backButton = findViewById(R.id.img_btn_back_toolbar);
        tvToolbarSubtitle = findViewById(R.id.tv_child_toolbar_back);
        tvToolbarTitle = findViewById(R.id.tv_title_toolbar_back);

        // Filter button
        filterButton = findViewById(R.id.img_btn_filter_search);
        filterButton.setVisibility(View.VISIBLE); // Tampilin button filter
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FilterSortActivity.class);
                intent.putExtra("sort_position",sortPosition);
                startActivityForResult(intent, 0);
            }
        });


        tvLanjut = findViewById(R.id.tv_lanjut);

        rec.setLayoutManager(new LinearLayoutManager(this));
        rec.setAdapter(produkChoiceAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if(resultCode == 1){
                int chosenPosition = data.getIntExtra("filter_data",-1);
                if(chosenPosition != -1){
                    Log.e("asd","Data retrieve : "+chosenPosition);
                    sortPosition = chosenPosition;
                }
            }
        }
    }

    @Override
    public void onLoadData(List<Product.ReksaDana> products) {
        produkChoiceAdapter.setProducts(products);
        produkChoiceAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String msg) {
        showSnackBar(msg);
    }
}
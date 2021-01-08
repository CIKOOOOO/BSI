package com.bca.bsi.ui.basenavigation.portfolio.produkmenu;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.dummydata.DummyData;

public class ProdukChoiceActivity extends BaseActivity {
    ProdukChoiceAdapter produkChoiceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_choice);
        initVar();
    }

    private void initVar() {
        RecyclerView rec = findViewById(R.id.recycler_produk_choice_main);
        produkChoiceAdapter = new ProdukChoiceAdapter();
        produkChoiceAdapter.setProducts(DummyData.getProductChoiceList());

        rec.setLayoutManager(new LinearLayoutManager(this));
        rec.setAdapter(produkChoiceAdapter);
    }


}
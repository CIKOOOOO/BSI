package com.bca.bsi.ui.basenavigation.portfolio.purchasing;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.ProductRekomen;
import com.bca.bsi.model.ProductRekomens;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.dummydata.DummyData;
import com.google.gson.Gson;

import java.util.List;

public class PurchasingSmartbotActivity extends BaseActivity {

    ImageButton rekomenRoboButton;
    ConstraintLayout roboHitungPopupLayout;
    ImageButton clearButton,backToolbarButton;
    TextView toolbarTitle, toolbarSubtitle, minPembelian;
    PurchasingSmartbotAdapter adapter;
    RecyclerView recyclerView;
    EditText etNominal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchasing_smartbot);
        initVar();
    }

    private void initVar() {
        rekomenRoboButton = findViewById(R.id.ib_rekomen_robo);
        roboHitungPopupLayout = findViewById(R.id.popup_rekomen_robo);
        clearButton = findViewById(R.id.ib_clear2);
        minPembelian = findViewById(R.id.tv_min_pembelian_value_p);
        etNominal = findViewById(R.id.et_nominal_pembelian_robo);

        // Atur nominal gbs depannya 0
        etNominal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char zero = '0';
                if(etNominal.getText().length()==2 && etNominal.getText().charAt(0)==zero){
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
        if(intent!=null && intent.hasExtra("data")){ //ini kalau dari bundle
            String hasil = intent.getStringExtra("data");
            Gson gson = new Gson();
            ProductRekomens productRekomens = gson.fromJson(hasil, ProductRekomens.class);
            productRekomenList = productRekomens.getProductRekomenList();
            minPembelian.setText(intent.getStringExtra("minPembelian"));
        } else { // ini kalau custom
            productRekomenList = DummyData.getProductRekomenListPurchase();
            minPembelian.setText("0");
        }


        //Toolbar variables
        backToolbarButton = findViewById(R.id.img_btn_back_toolbar);
        toolbarTitle = findViewById(R.id.tv_title_toolbar_back);
        toolbarSubtitle = findViewById(R.id.tv_child_toolbar_back);

        // inisialisasi adapter dan recycler
        adapter = new PurchasingSmartbotAdapter();
        adapter.setProductRekomenList(productRekomenList);
        recyclerView = findViewById(R.id.recycler_product_main_p);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

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
    }


}
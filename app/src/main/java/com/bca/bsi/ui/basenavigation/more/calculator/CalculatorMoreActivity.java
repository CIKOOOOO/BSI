package com.bca.bsi.ui.basenavigation.more.calculator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.bca.bsi.adapter.CalculatorPagerAdapter;
import com.bca.bsi.model.Product;
import com.bca.bsi.utils.BaseActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bca.bsi.R;
import com.google.gson.Gson;

public class CalculatorMoreActivity extends BaseActivity implements View.OnClickListener {

    private TextView besarInvestasiBulananTab;
    private TextView besarHasilInvestasiTab;
    private TextView durasiInvestasiTab;
    private TextView besarRoRTab;
    private TextView lastTab;
    private TextView select;
    private TextView titlePage;
    private TextView titleChildPage;
    private ViewPager viewPager;
    private CalculatorPagerAdapter calculatorPagerAdapter;
    private ImageButton backBtn;
    private int numberOfTabs;
    private String rorValue = "0";
    private String selectedProdukReksadana = null;
    private String selectedDetailReksadana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_more);

        besarInvestasiBulananTab = findViewById(R.id.besarInvestasiBulananTab);
        besarHasilInvestasiTab = findViewById(R.id.besarHasilInvestasiTab);
        durasiInvestasiTab = findViewById(R.id.durasiInvestasiTab);
        besarRoRTab = findViewById(R.id.besarRoRTab);
        lastTab = findViewById(R.id.lastTab);
        titlePage = findViewById(R.id.tv_title_toolbar_back);
        titleChildPage = findViewById(R.id.tv_child_toolbar_back);
        viewPager = findViewById(R.id.viewPager);
        backBtn = findViewById(R.id.img_btn_back_toolbar);

        besarInvestasiBulananTab.setOnClickListener(this);
        besarHasilInvestasiTab.setOnClickListener(this);
        durasiInvestasiTab.setOnClickListener(this);
        besarRoRTab.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        besarInvestasiBulananTab.setTextColor(Color.BLACK);
        besarHasilInvestasiTab.setTextColor(Color.WHITE);
        durasiInvestasiTab.setTextColor(Color.WHITE);
        besarRoRTab.setTextColor(Color.WHITE);

        select = findViewById(R.id.select);
        titlePage.setText(getString(R.string.kalkulator_investasi));

        Intent intent = getIntent();
        numberOfTabs =  intent.getIntExtra("numberOfTabs",4);
        rorValue = intent.getStringExtra("rorValue");
        selectedProdukReksadana = intent.getStringExtra("namaProduk");
        selectedDetailReksadana = intent.getStringExtra("selectedDetailReksadana");

        Gson gson = new Gson();
        Product.DetailReksaDana detailReksaDana = gson.fromJson(selectedDetailReksadana, Product.DetailReksaDana.class);

        calculatorPagerAdapter = new CalculatorPagerAdapter(getSupportFragmentManager(),numberOfTabs,rorValue, selectedProdukReksadana, detailReksaDana);
        viewPager.setAdapter(calculatorPagerAdapter);
        viewPager.setOffscreenPageLimit(numberOfTabs);

        switch (numberOfTabs){
            case 3:
                lastTab.setVisibility(View.GONE);
                besarRoRTab.setVisibility(View.GONE);
                titleChildPage.setVisibility(View.GONE);
                titleChildPage.setText(intent.getStringExtra("namaProduk"));
                break;
            case 4:
                lastTab.setVisibility(View.VISIBLE);
                besarRoRTab.setVisibility(View.VISIBLE);
                titleChildPage.setVisibility(View.GONE);
                break;
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onChangeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void onChangeTab(int position) {
        switch (position) {
            case 0: {
                select.animate().x(0).setDuration(100);
                besarInvestasiBulananTab.setTextColor(Color.BLACK);
                besarHasilInvestasiTab.setTextColor(Color.WHITE);
                durasiInvestasiTab.setTextColor(Color.WHITE);
                besarRoRTab.setTextColor(Color.WHITE);
                viewPager.setCurrentItem(0);
                break;
            }

            case 1: {
                int size = besarHasilInvestasiTab.getWidth();
                select.animate().x(size).setDuration(100);
                besarInvestasiBulananTab.setTextColor(Color.WHITE);
                besarHasilInvestasiTab.setTextColor(Color.BLACK);
                durasiInvestasiTab.setTextColor(Color.WHITE);
                besarRoRTab.setTextColor(Color.WHITE);
                viewPager.setCurrentItem(1);
                break;
            }

            case 2: {
                int size = besarHasilInvestasiTab.getWidth() * 2;
                select.animate().x(size).setDuration(100);
                besarInvestasiBulananTab.setTextColor(Color.WHITE);
                besarHasilInvestasiTab.setTextColor(Color.WHITE);
                durasiInvestasiTab.setTextColor(Color.BLACK);
                besarRoRTab.setTextColor(Color.WHITE);
                viewPager.setCurrentItem(2);
                break;
            }

            case 3: {
                int size = besarHasilInvestasiTab.getWidth() * 3;
                select.animate().x(size).setDuration(100);
                besarInvestasiBulananTab.setTextColor(Color.WHITE);
                besarHasilInvestasiTab.setTextColor(Color.WHITE);
                durasiInvestasiTab.setTextColor(Color.WHITE);
                besarRoRTab.setTextColor(Color.BLACK);
                viewPager.setCurrentItem(3);
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.besarInvestasiBulananTab: {
                onChangeTab(0);
                break;
            }

            case R.id.besarHasilInvestasiTab: {
                onChangeTab(1);
                break;
            }

            case R.id.durasiInvestasiTab: {
                onChangeTab(2);
                break;
            }

            case R.id.besarRoRTab: {
                onChangeTab(3);
                break;
            }

            case R.id.img_btn_back_toolbar: {
                onBackPressed();
                break;
            }
        }

    }
}
package com.bca.bsi.ui.basenavigation.products.detail.reksadana;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.bca.bsi.adapter.CalculatorProductPagerAdapter;
import com.bca.bsi.utils.BaseActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bca.bsi.R;

public class CalculatorProductActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager viewPager2;
    private CalculatorProductPagerAdapter calculatorProductPagerAdapter;
    private ImageButton backBtn2;
    private TextView besarInvestasiBulananTabCalProd;
    private TextView besarHasilInvestasiTabCalProd;
    private TextView durasiInvestasiTabCalProd;
    private TextView selectCalProd;
    public static final String ROR_KEY = "rorKey";
    private double ror;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_product);

        Intent intent = getIntent();
        if(intent==null){
            onBackPressed();
        }else{
            if(intent.hasExtra(ROR_KEY)){
                this.ror = intent.getDoubleExtra(ROR_KEY,-1);
                if(this.ror == -1){
                    onBackPressed();
                }
            }
        }

        TextView titlePage = findViewById(R.id.tv_title_toolbar_back);
        titlePage.setText(getString(R.string.kalkulator_investasi));

        besarInvestasiBulananTabCalProd = findViewById(R.id.besarInvestasiBulananTabCalProd);
        besarHasilInvestasiTabCalProd = findViewById(R.id.besarHasilInvestasiTabCalProd);
        durasiInvestasiTabCalProd = findViewById(R.id.durasiInvestasiTabCalProd);
        selectCalProd = findViewById(R.id.selectCalProd);
        viewPager2 = findViewById(R.id.viewPagerCalProd);
        backBtn2 = findViewById(R.id.img_btn_back_toolbar);

        besarInvestasiBulananTabCalProd.setOnClickListener(this);
        besarHasilInvestasiTabCalProd.setOnClickListener(this);
        durasiInvestasiTabCalProd.setOnClickListener(this);
        backBtn2.setOnClickListener(this);

        besarInvestasiBulananTabCalProd.setTextColor(Color.BLACK);
        besarHasilInvestasiTabCalProd.setTextColor(Color.WHITE);
        durasiInvestasiTabCalProd.setTextColor(Color.WHITE);

        calculatorProductPagerAdapter = new CalculatorProductPagerAdapter(getSupportFragmentManager(),3);
        viewPager2.setAdapter(calculatorProductPagerAdapter);
        viewPager2.setOffscreenPageLimit(3);

        viewPager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onChageTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void onChageTab(int position) {
        switch (position){
            case 0: {
                selectCalProd.animate().x(0).setDuration(100);
                besarInvestasiBulananTabCalProd.setTextColor(Color.BLACK);
                besarHasilInvestasiTabCalProd.setTextColor(Color.WHITE);
                durasiInvestasiTabCalProd.setTextColor(Color.WHITE);
                viewPager2.setCurrentItem(0);
                break;
            }
            case 1: {
                int size = besarHasilInvestasiTabCalProd.getWidth();
                selectCalProd.animate().x(size).setDuration(100);
                besarInvestasiBulananTabCalProd.setTextColor(Color.WHITE);
                besarHasilInvestasiTabCalProd.setTextColor(Color.BLACK);
                durasiInvestasiTabCalProd.setTextColor(Color.WHITE);
                viewPager2.setCurrentItem(1);
                break;
            }
            case 2: {
                int size = besarHasilInvestasiTabCalProd.getWidth() * 2;
                selectCalProd.animate().x(size).setDuration(100);
                besarInvestasiBulananTabCalProd.setTextColor(Color.WHITE);
                besarHasilInvestasiTabCalProd.setTextColor(Color.WHITE);
                durasiInvestasiTabCalProd.setTextColor(Color.BLACK);
                viewPager2.setCurrentItem(2);
                break;
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.besarInvestasiBulananTabCalProd: {
                onChageTab(0);
                break;
            }
            case R.id.besarHasilInvestasiTabCalProd: {
                onChageTab(1);
                break;
            }
            case R.id.durasiInvestasiTabCalProd: {
                onChageTab(2);
                break;
            }

            case R.id.img_btn_back_toolbar: {
                onBackPressed();
                break;
            }
        }
    }
}
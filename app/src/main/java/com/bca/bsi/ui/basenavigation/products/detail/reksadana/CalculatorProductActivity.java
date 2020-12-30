package com.bca.bsi.ui.basenavigation.products.detail.reksadana;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import com.bca.bsi.ui.basenavigation.more.CalculatorMore.CalculatorMoreActivity;
import com.bca.bsi.ui.basenavigation.more.CalculatorMore.CalculatorPagerAdapter;
import com.bca.bsi.utils.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_product);

        TextView titlePage = findViewById(R.id.tv_title_toolbar_back);
        titlePage.setText("Kalkulator Investasi");

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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.besarInvestasiBulananTabCalProd: {
                selectCalProd.animate().x(0).setDuration(100);
                besarInvestasiBulananTabCalProd.setTextColor(Color.BLACK);
                besarHasilInvestasiTabCalProd.setTextColor(Color.WHITE);
                durasiInvestasiTabCalProd.setTextColor(Color.WHITE);
                viewPager2.setCurrentItem(0);
                break;
            }
            case R.id.besarHasilInvestasiTabCalProd: {
                int size = besarHasilInvestasiTabCalProd.getWidth();
                selectCalProd.animate().x(size).setDuration(100);
                besarInvestasiBulananTabCalProd.setTextColor(Color.WHITE);
                besarHasilInvestasiTabCalProd.setTextColor(Color.BLACK);
                durasiInvestasiTabCalProd.setTextColor(Color.WHITE);
                viewPager2.setCurrentItem(1);
                break;
            }
            case R.id.durasiInvestasiTabCalProd: {
                int size = besarHasilInvestasiTabCalProd.getWidth() * 2;
                selectCalProd.animate().x(size).setDuration(100);
                besarInvestasiBulananTabCalProd.setTextColor(Color.WHITE);
                besarHasilInvestasiTabCalProd.setTextColor(Color.WHITE);
                durasiInvestasiTabCalProd.setTextColor(Color.BLACK);
                viewPager2.setCurrentItem(2);
                break;
            }

            case R.id.img_btn_back_toolbar: {
                onBackPressed();
                break;
            }
        }
    }
}
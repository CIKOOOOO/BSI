package com.bca.bsi.ui.basenavigation.more.CalculatorMore;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import com.bca.bsi.utils.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.TextView;

import com.bca.bsi.R;

public class CalculatorMoreActivity extends BaseActivity implements View.OnClickListener {

    private ColorStateList def;
    private TextView besarInvestasiBulananTab;
    private TextView besarHasilInvestasiTab;
    private TextView durasiInvestasiTab;
    private TextView besarRoRTab;
    private TextView select;
    private TextView titlePage;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_more);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        besarInvestasiBulananTab = findViewById(R.id.besarInvestasiBulananTab);
        besarHasilInvestasiTab = findViewById(R.id.besarHasilInvestasiTab);
        durasiInvestasiTab = findViewById(R.id.durasiInvestasiTab);
        besarRoRTab = findViewById(R.id.besarRoRTab);
        titlePage = findViewById(R.id.tv_title_toolbar_back);
        viewPager = findViewById(R.id.viewPager);


        besarInvestasiBulananTab.setOnClickListener(this);
        besarHasilInvestasiTab.setOnClickListener(this);
        durasiInvestasiTab.setOnClickListener(this);
        besarRoRTab.setOnClickListener(this);

        besarInvestasiBulananTab.setTextColor(Color.BLACK);
        besarHasilInvestasiTab.setTextColor(Color.WHITE);
        durasiInvestasiTab.setTextColor(Color.WHITE);
        besarRoRTab.setTextColor(Color.WHITE);

        select = findViewById(R.id.select);
        def = besarHasilInvestasiTab.getTextColors();
        titlePage.setText("Kalkulator Investasi");

        CalculatorPagerAdapter calculatorPagerAdapter = new CalculatorPagerAdapter(getSupportFragmentManager(),4);

        viewPager.setAdapter(calculatorPagerAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.besarInvestasiBulananTab: {
                select.animate().x(0).setDuration(100);
                besarInvestasiBulananTab.setTextColor(Color.BLACK);
                besarHasilInvestasiTab.setTextColor(Color.WHITE);
                durasiInvestasiTab.setTextColor(Color.WHITE);
                besarRoRTab.setTextColor(Color.WHITE);
                viewPager.setCurrentItem(0);
                break;
            }

            case R.id.besarHasilInvestasiTab: {
                int size = besarHasilInvestasiTab.getWidth();
                select.animate().x(size).setDuration(100);
                besarInvestasiBulananTab.setTextColor(Color.WHITE);
                besarHasilInvestasiTab.setTextColor(Color.BLACK);
                durasiInvestasiTab.setTextColor(Color.WHITE);
                besarRoRTab.setTextColor(Color.WHITE);
                viewPager.setCurrentItem(1);
                break;
            }

            case R.id.durasiInvestasiTab: {
                int size = besarHasilInvestasiTab.getWidth() * 2;
                select.animate().x(size).setDuration(100);
                besarInvestasiBulananTab.setTextColor(Color.WHITE);
                besarHasilInvestasiTab.setTextColor(Color.WHITE);
                durasiInvestasiTab.setTextColor(Color.BLACK);
                besarRoRTab.setTextColor(Color.WHITE);
                viewPager.setCurrentItem(2);
                break;
            }

            case R.id.besarRoRTab: {
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
}
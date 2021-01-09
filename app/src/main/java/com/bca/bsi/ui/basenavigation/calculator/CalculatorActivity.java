package com.bca.bsi.ui.basenavigation.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bca.bsi.R;
import com.bca.bsi.adapter.CalculatorAdapter;
import com.bca.bsi.utils.BaseActivity;

public class CalculatorActivity extends BaseActivity implements View.OnClickListener  {

    private TextView titlePage;
    private ImageButton backBtn;
    private TextView besarInvestasiBulananTab;
    private TextView besarHasilInvestasiTab;
    private TextView durasiInvestasiTab;
    private TextView besarRoRTab;
    private TextView select;
    private TextView lastTab;
    private ViewPager viewPager;
    private CalculatorAdapter adapter;
    private int numberOfTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        backBtn = findViewById(R.id.img_btn_back_toolbar);
        titlePage = findViewById(R.id.tv_title_toolbar_back);
        besarInvestasiBulananTab = findViewById(R.id.besarInvestasiBulananTab);
        besarHasilInvestasiTab = findViewById(R.id.besarHasilInvestasiTab);
        durasiInvestasiTab = findViewById(R.id.durasiInvestasiTab);
        besarRoRTab = findViewById(R.id.besarRoRTab);
        select = findViewById(R.id.select);
        lastTab = findViewById(R.id.lastTab);
        viewPager = findViewById(R.id.viewPager);

        besarInvestasiBulananTab.setOnClickListener(this);
        besarHasilInvestasiTab.setOnClickListener(this);
        durasiInvestasiTab.setOnClickListener(this);
        besarRoRTab.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        besarInvestasiBulananTab.setTextColor(Color.BLACK);
        besarHasilInvestasiTab.setTextColor(Color.WHITE);
        durasiInvestasiTab.setTextColor(Color.WHITE);
        besarRoRTab.setTextColor(Color.WHITE);
        titlePage.setText(getString(R.string.kalkulator_investasi));

        adapter = new CalculatorAdapter(this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        Intent intent = getIntent();
        numberOfTabs = intent.getIntExtra("numberOfTabs",4);
        adapter.setNumberOfTabs(numberOfTabs);

        switch (numberOfTabs){
            case 3:
                besarRoRTab.setVisibility(View.GONE);
                lastTab.setVisibility(View.GONE);
                viewPager.setOffscreenPageLimit(3);
                break;
            case 4:
                besarRoRTab.setVisibility(View.VISIBLE);
                lastTab.setVisibility(View.VISIBLE);
                viewPager.setOffscreenPageLimit(4);
                break;
        }

        adapter.notifyDataSetChanged();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        switch (position) {
            case 0: {
                select.animate().x(0).setDuration(100);
                besarInvestasiBulananTab.setTextColor(Color.BLACK);
                besarHasilInvestasiTab.setTextColor(Color.WHITE);
                durasiInvestasiTab.setTextColor(Color.WHITE);
                besarRoRTab.setTextColor(Color.WHITE);
                //viewPager.setCurrentItem(0);
                break;
            }

            case 1: {
                int size = besarHasilInvestasiTab.getWidth();
                select.animate().x(size).setDuration(100);
                besarInvestasiBulananTab.setTextColor(Color.WHITE);
                besarHasilInvestasiTab.setTextColor(Color.BLACK);
                durasiInvestasiTab.setTextColor(Color.WHITE);
                besarRoRTab.setTextColor(Color.WHITE);
                //viewPager.setCurrentItem(1);
                break;
            }

            case 2: {
                int size = besarHasilInvestasiTab.getWidth() * 2;
                select.animate().x(size).setDuration(100);
                besarInvestasiBulananTab.setTextColor(Color.WHITE);
                besarHasilInvestasiTab.setTextColor(Color.WHITE);
                durasiInvestasiTab.setTextColor(Color.BLACK);
                besarRoRTab.setTextColor(Color.WHITE);
                //viewPager.setCurrentItem(2);
                break;
            }

            case 3: {
                int size = besarHasilInvestasiTab.getWidth() * 3;
                select.animate().x(size).setDuration(100);
                besarInvestasiBulananTab.setTextColor(Color.WHITE);
                besarHasilInvestasiTab.setTextColor(Color.WHITE);
                durasiInvestasiTab.setTextColor(Color.WHITE);
                besarRoRTab.setTextColor(Color.BLACK);
                //viewPager.setCurrentItem(3);
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.besarInvestasiBulananTab: {
                onChageTab(0);
                break;
            }

            case R.id.besarHasilInvestasiTab: {
                onChageTab(1);
                break;
            }

            case R.id.durasiInvestasiTab: {
                onChageTab(2);
                break;
            }

            case R.id.besarRoRTab: {
                onChageTab(3);
                break;
            }

            case R.id.img_btn_back_toolbar: {
                onBackPressed();
                break;
            }
        }
    }
}
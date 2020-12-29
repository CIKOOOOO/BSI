package com.bca.bsi.ui.basenavigation.products.detail.reksadana;

import android.content.res.ColorStateList;
import android.os.Bundle;

import com.bca.bsi.ui.basenavigation.more.CalculatorMore.CalculatorPagerAdapter;
import com.bca.bsi.utils.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.ImageButton;

import com.bca.bsi.R;

public class CalculatorProductActivity extends BaseActivity implements View.OnClickListener {

    private ColorStateList def2;
    private ViewPager viewPager2;
    private CalculatorPagerAdapter calculatorPagerAdapter2;
    private ImageButton backBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_product);


    }

    @Override
    public void onClick(View v) {

    }
}
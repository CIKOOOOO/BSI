package com.bca.bsi.ui.basenavigation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bca.bsi.R;
import com.bca.bsi.ui.basenavigation.information.InformationFragment;
import com.bca.bsi.ui.basenavigation.more.MoreFragment;
import com.bca.bsi.ui.basenavigation.portfolio.PortfolioFragment;
import com.bca.bsi.ui.basenavigation.products.ProductsFragment;
import com.bca.bsi.ui.basenavigation.profile.ProfileFragment;
import com.bca.bsi.utils.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseNavigationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_navigation);
        initVar();
    }

    private void initVar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bot_nav_base_navigation);

        bottomNavigationView.setItemIconTintList(null);

        final Menu menu = bottomNavigationView.getMenu();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                for (int i = 0; i < menu.size(); i++) {
                    MenuItem menuItem = menu.getItem(i);
                    if (menuItem.getItemId() == item.getItemId()) {
                        menuItem.setChecked(true);
                        break;
                    }
                }

                switch (item.getItemId()) {
                    case R.id.menu_portfolio:
                        changeFragment(new PortfolioFragment());
                        break;
                    case R.id.menu_profile:
                        changeFragment(new ProfileFragment());
                        break;
                    case R.id.menu_products:
                        changeFragment(new ProductsFragment());
                        break;
                    case R.id.menu_information:
                        changeFragment(new InformationFragment());
                        break;
                    case R.id.menu_more:
                        changeFragment(new MoreFragment());
                        break;
                }
                return false;
            }
        });
    }

    public void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_base_navigation, fragment);
        transaction.commit();
    }

}
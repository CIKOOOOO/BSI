package com.bca.bsi.ui.basenavigation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.adapter.RoboRekomenAdapter;
import com.bca.bsi.model.Portfolio;
import com.bca.bsi.ui.basenavigation.information.InformationFragment;
import com.bca.bsi.ui.basenavigation.more.MoreFragment;
import com.bca.bsi.ui.basenavigation.portfolio.PortfolioFragment;
import com.bca.bsi.ui.basenavigation.products.ProductsFragment;
import com.bca.bsi.ui.basenavigation.profile.ProfileFragment;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.dummydata.DummyData;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class BaseNavigationActivity extends BaseActivity implements PortfolioFragment.onBundleClick {

    private BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior;
    RoboRekomenAdapter roboRekomenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_navigation);
        initVar();
    }

    private void initVar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bot_nav_base_navigation);
        ConstraintLayout clBSRoboRekomen = findViewById(R.id.bs_robo_rekomen);
        RecyclerView recycler_robo_rekomen = findViewById(R.id.recycler_product_main);
        final FrameLayout frameLayout = findViewById(R.id.frame_blur);

        roboRekomenAdapter = new RoboRekomenAdapter();
        roboRekomenAdapter.setProductRekomenList(DummyData.getProductRekomenList());

        recycler_robo_rekomen.setLayoutManager(new LinearLayoutManager(this));
        recycler_robo_rekomen.setAdapter(roboRekomenAdapter);

        bottomSheetBehavior = BottomSheetBehavior.from(clBSRoboRekomen);

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
                        changeFragmentToPortfolio();
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

        changeFragmentToPortfolio();
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == BottomSheetBehavior.STATE_EXPANDED){
                    frameLayout.setVisibility(View.VISIBLE);
                } else {
                    frameLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    public void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_base_navigation, fragment);
        transaction.commit();
    }

    private void changeFragmentToPortfolio(){
        PortfolioFragment portfolioFragment = new PortfolioFragment();
        portfolioFragment.setOnBundleClick(BaseNavigationActivity.this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_base_navigation, portfolioFragment);
        transaction.commit();
    }
    @Override
    public void onItemClick(Portfolio portfolio) {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }
}
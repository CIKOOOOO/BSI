package com.bca.bsi.ui.basenavigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.adapter.ReportAdapter;
import com.bca.bsi.adapter.RoboRekomenAdapter;
import com.bca.bsi.model.Forum;
import com.bca.bsi.model.Portfolio;
import com.bca.bsi.model.ProductRekomens;
import com.bca.bsi.ui.basenavigation.information.InformationFragment;
import com.bca.bsi.ui.basenavigation.more.MoreFragment;
import com.bca.bsi.ui.basenavigation.portfolio.PortfolioFragment;
import com.bca.bsi.ui.basenavigation.portfolio.purchasing.PurchasingSmartbotActivity;
import com.bca.bsi.ui.basenavigation.products.ProductsFragment;
import com.bca.bsi.ui.basenavigation.profile.ProfileFragment;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.dummydata.DummyData;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.Calendar;
import java.util.List;

public class BaseNavigationActivity extends BaseActivity implements PortfolioFragment.onBundleClick, InformationFragment.onReport, ReportAdapter.onReportClick, View.OnClickListener, IBaseNavigatonCallback {

    private BottomSheetBehavior<ConstraintLayout> bsSmartBot, bsReport;
    private RoboRekomenAdapter roboRekomenAdapter;
    private FrameLayout frameLayout;
    private ReportAdapter reportAdapter;
    private Button btnReport;
    private BaseNavigationViewModel viewModel;
    private Forum.Report report;
    private ConstraintLayout roboAboutLayout, tipsOfTheWeekLayout;
    private TextView okeMengerti, bottomLanjut;
    private ImageButton clearPopupTOTW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_navigation);
        initVar();
    }

    private void initVar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bot_nav_base_navigation);
        ConstraintLayout clBSRoboRekomen = findViewById(R.id.bs_robo_rekomen);
        ConstraintLayout clBSReport = findViewById(R.id.cl_choose_image);
        RecyclerView recycler_robo_rekomen = findViewById(R.id.recycler_product_main);
        RecyclerView recyclerReport = findViewById(R.id.bs_recycler_choose_image);

        frameLayout = findViewById(R.id.frame_blur);
        btnReport = findViewById(R.id.bs_btn_update_choose_image);

        reportAdapter = new ReportAdapter(this);

        viewModel = new ViewModelProvider(this).get(BaseNavigationViewModel.class);
        viewModel.setCallback(this);

        roboRekomenAdapter = new RoboRekomenAdapter();
        roboRekomenAdapter.setProductRekomenList(DummyData.getProductRekomenList());

        ConstraintLayout.LayoutParams recyclerReportLayoutParams = (ConstraintLayout.LayoutParams) recyclerReport.getLayoutParams();
        recyclerReportLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        recyclerReportLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        recyclerReportLayoutParams.bottomMargin = 20;

        recyclerReport.setLayoutParams(recyclerReportLayoutParams);

        recycler_robo_rekomen.setLayoutManager(new LinearLayoutManager(this));
        recycler_robo_rekomen.setAdapter(roboRekomenAdapter);

        recyclerReport.setLayoutManager(new LinearLayoutManager(this));
        recyclerReport.setAdapter(reportAdapter);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) clBSReport.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        clBSReport.setLayoutParams(layoutParams);

        bsSmartBot = BottomSheetBehavior.from(clBSRoboRekomen);
        bsReport = BottomSheetBehavior.from(clBSReport);
        // lanjut bottom sheet
        bottomLanjut = findViewById(R.id.tv_lanjut);
        bottomLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductRekomens productRekomens = new ProductRekomens(DummyData.getProductRekomenList());
                String minPembelian = "10000";

                Intent intent = new Intent(v.getContext(), PurchasingSmartbotActivity.class);
                intent.putExtra("data", Utils.toJSON(productRekomens));
                intent.putExtra("minPembelian", minPembelian);
                startActivity(intent);


            }
        });

        //Popup tips of the week
        tipsOfTheWeekLayout = findViewById(R.id.popup_tips_of_the_week);
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(dayOfWeek);
        if (dayOfWeek == 3) { // Day-2 = Monday
            tipsOfTheWeekLayout.setVisibility(View.VISIBLE);
        }
        // Clear button tips of the week
        clearPopupTOTW = findViewById(R.id.ib_clear3);
        clearPopupTOTW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipsOfTheWeekLayout.setVisibility(View.GONE);
            }
        });

        // Popup tentang robo
        roboAboutLayout = findViewById(R.id.popup_tentang_robo);
        // Oke Mengerti Popup
        okeMengerti = findViewById(R.id.tv_oke);
        okeMengerti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roboAboutLayout.setVisibility(View.GONE);
            }
        });

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
                        changeFragmentToInformation();
                        break;
                    case R.id.menu_more:
                        changeFragment(new MoreFragment());
                        break;
                }
                return false;
            }
        });

        changeFragmentToPortfolio();

        bsSmartBot.addBottomSheetCallback(bottomSheetCallback);
        bsReport.addBottomSheetCallback(bottomSheetCallback);

        btnReport.setOnClickListener(this);
    }

    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                frameLayout.setVisibility(View.VISIBLE);
            } else {
                frameLayout.setVisibility(View.GONE);
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    };

    public void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_base_navigation, fragment);
        transaction.commit();
    }

    private void changeFragmentToPortfolio() {
        PortfolioFragment portfolioFragment = new PortfolioFragment();
        portfolioFragment.setOnBundleClick(BaseNavigationActivity.this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_base_navigation, portfolioFragment);
        transaction.commit();
    }

    private void changeFragmentToInformation() {
        InformationFragment informationFragment = new InformationFragment();
        informationFragment.setOnReport(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_base_navigation, informationFragment);
        transaction.commit();
    }

    @Override
    public void onItemClick(Portfolio portfolio) {
        bsSmartBot.setState(BottomSheetBehavior.STATE_EXPANDED);

    }

    @Override
    public void onClick(List<Forum.Report> reportList) {
        TextView tvTitle = findViewById(R.id.bs_tv_title_choose_image);

        bsReport.setState(BottomSheetBehavior.STATE_EXPANDED);

        tvTitle.setText(getResources().getString(R.string.reason_to_report));
        btnReport.setText(getResources().getString(R.string.report_now));

        btnReport.setEnabled(false);
        btnReport.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        btnReport.setTextColor(getResources().getColor(android.R.color.white));

        reportAdapter.setReportList(reportList);
        reportAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        if (bsReport.getState() == BottomSheetBehavior.STATE_EXPANDED || bsSmartBot.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bsReport.setState(BottomSheetBehavior.STATE_COLLAPSED);
            bsSmartBot.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else
            super.onBackPressed();
    }

    @Override
    public void onItemReportChoose(Forum.Report report) {
        btnReport.setEnabled(true);
        btnReport.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        btnReport.setTextColor(getResources().getColor(android.R.color.black));
        this.report = report;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bs_btn_update_choose_image:
                if (this.report == null) {
                    showSnackBar("Mohon pilih jenis laporan");
                } else {
                    viewModel.reportPostOrForumWith(this.report);
                }
                break;
        }
    }

    public void onInfoClick () {
        roboAboutLayout.setVisibility(View.VISIBLE);
    }
}
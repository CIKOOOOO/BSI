package com.bca.bsi.ui.basenavigation;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.bca.bsi.model.TipsOfTheWeek;
import com.bca.bsi.ui.basenavigation.information.InformationFragment;
import com.bca.bsi.ui.basenavigation.information.forum.MainForumFragment;
import com.bca.bsi.ui.basenavigation.more.MoreFragment;
import com.bca.bsi.ui.basenavigation.portfolio.PortfolioFragment;
import com.bca.bsi.ui.basenavigation.portfolio.purchasing.PurchasingSmartbotActivity;
import com.bca.bsi.ui.basenavigation.products.ProductsFragment;
import com.bca.bsi.ui.basenavigation.profile.ProfileFragment;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Constant;
import com.bca.bsi.utils.dialog.AboutRoboDialog;
import com.bca.bsi.utils.dialog.TipsOfTheWeekDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BaseNavigationActivity extends BaseActivity implements PortfolioFragment.onBundleClick, ReportAdapter.onReportClick, View.OnClickListener, IBaseNavigatonCallback, TipsOfTheWeekDialog.onItemClick, AboutRoboDialog.onCloseDialog {

    public static BottomSheetBehavior<ConstraintLayout> BOTTOM_SHEET_REPORT;

    private static ReportAdapter reportAdapter;
    private static Button btnReport;
    private static TextView tvTitle;
    private static Resources resources;

    private BottomSheetBehavior<ConstraintLayout> bsSmartBot;
    private RoboRekomenAdapter roboRekomenAdapter;
    private FrameLayout frameLayout;
    private BaseNavigationViewModel viewModel;
    private Forum.Report report;
    private AboutRoboDialog aboutRoboDialog;
    private Portfolio portfolio;
    private TipsOfTheWeekDialog tipsOfTheWeekDialog;


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
        tvTitle = findViewById(R.id.bs_tv_title_choose_image);

        reportAdapter = new ReportAdapter(this);
        tipsOfTheWeekDialog = new TipsOfTheWeekDialog(this);
        aboutRoboDialog = new AboutRoboDialog(this);
        resources = getResources();

        viewModel = new ViewModelProvider(this).get(BaseNavigationViewModel.class);
        viewModel.setCallback(this);

        roboRekomenAdapter = new RoboRekomenAdapter();
//        roboRekomenAdapter.setProductRekomenList(DummyData.getProductRekomenList());

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
        BOTTOM_SHEET_REPORT = BottomSheetBehavior.from(clBSReport);
        // lanjut bottom sheet
        TextView bottomLanjut = findViewById(R.id.tv_lanjut);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(Utils.getTime(Constant.DATE_FORMAT_1)));
        boolean monday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;

        if (
                monday
                        && prefConfig.getTipsActivated()
//                && !prefConfig.getTimeTipsOfTheWeek().equals(Utils.getTime(Constant.DATE_FORMAT_2))
        ) { // Day-2 = Monday
            viewModel.getTipsOfTheWeek(prefConfig.getTokenUser());
        }

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
        BOTTOM_SHEET_REPORT.addBottomSheetCallback(bottomSheetCallback);

        btnReport.setOnClickListener(this);
        bottomLanjut.setOnClickListener(this);
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
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_base_navigation, informationFragment);
        transaction.commit();
    }

    @Override
    public void onItemClick(Portfolio portfolio) {
        this.portfolio = portfolio;

        bsSmartBot.setState(BottomSheetBehavior.STATE_EXPANDED);
        roboRekomenAdapter.setProductRekomenList(portfolio.getProductRekomenList());
        roboRekomenAdapter.notifyDataSetChanged();

        TextView minPembelian = findViewById(R.id.tv_min_pembelian_value);
        minPembelian.setText(portfolio.getMinPurchase());
    }

    @Override
    public void onBackPressed() {
        if (BOTTOM_SHEET_REPORT.getState() == BottomSheetBehavior.STATE_EXPANDED || bsSmartBot.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            BOTTOM_SHEET_REPORT.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
            case R.id.tv_lanjut:
                if (portfolio != null) {
                    Intent intent = new Intent(v.getContext(), PurchasingSmartbotActivity.class);
                    intent.putExtra("data", Utils.toJSON(portfolio));
                    startActivity(intent);
                }
                break;
        }
    }

    public void onInfoClick() {
        aboutRoboDialog.show(getSupportFragmentManager(), "");
    }

    @Override
    public void onCloseDialog() {
        if (tipsOfTheWeekDialog != null && tipsOfTheWeekDialog.getTag() != null) {
            tipsOfTheWeekDialog.dismiss();
        }
    }

    @Override
    public void dontAskTipsOfTheWeek(boolean isShow) {
        prefConfig.setTipsOfTheWeek(isShow);
    }

    @Override
    public void onLoadTipsOfTheWeek(TipsOfTheWeek tipsOfTheWeek) {
//        prefConfig.setTimeOfTipsOfTheWeek(Utils.getTime(Constant.DATE_FORMAT_2));
        tipsOfTheWeekDialog.setTipsOfTheWeek(tipsOfTheWeek);
        tipsOfTheWeekDialog.show(getSupportFragmentManager(), "");
    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }

    @Override
    public void onCloseAboutRoboDialog() {
        if (aboutRoboDialog != null && aboutRoboDialog.getTag() != null) {
            aboutRoboDialog.dismiss();
        }
    }

    public static void loadReport(List<Forum.Report> reportList) {
        BOTTOM_SHEET_REPORT.setState(BottomSheetBehavior.STATE_EXPANDED);

        tvTitle.setText("Alasan Pelaporan");
        btnReport.setText("Laporkan");

        btnReport.setEnabled(false);
        btnReport.setBackgroundColor(resources.getColor(android.R.color.darker_gray));
        btnReport.setTextColor(resources.getColor(android.R.color.white));

        reportAdapter.setReportList(reportList);
        reportAdapter.notifyDataSetChanged();
    }
}
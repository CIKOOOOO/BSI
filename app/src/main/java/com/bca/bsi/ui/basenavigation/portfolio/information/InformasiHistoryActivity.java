package com.bca.bsi.ui.basenavigation.portfolio.information;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Portfolio;
import com.bca.bsi.ui.basenavigation.information.forum.post.PostActivity;
import com.bca.bsi.utils.BaseActivity;
import com.bca.bsi.utils.Utils;

import java.util.List;

public class InformasiHistoryActivity extends BaseActivity implements View.OnClickListener, IInformationHistoryCallback, InformasiHistoryAdapter.onClickShare {

    private InformasiHistoryAdapter informasiHistoryAdapter;
    private TextView toolbarTitle, toolbarSubtitle;
    private ImageButton toolbarBackButton;
    private InformationHistoryViewModel viewModel;

    TextView tvInfromasi, tvHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_history);
        initVar();
    }

    private void initVar() {
        // TOOLBAR
        toolbarTitle = findViewById(R.id.tv_title_toolbar_back);
        toolbarSubtitle = findViewById(R.id.tv_child_toolbar_back);
        toolbarTitle.setText("PORTFOLIO");
        toolbarSubtitle.setText("Reksa Dana");

        toolbarBackButton = findViewById(R.id.img_btn_back_toolbar);
        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // MODE
        tvInfromasi = findViewById(R.id.tv_start_informasi_history);
        tvHistory = findViewById(R.id.tv_end_informasi_history);

        informasiHistoryAdapter = new InformasiHistoryAdapter(this);
        viewModel = new ViewModelProvider(this).get(InformationHistoryViewModel.class);
        viewModel.setCallback(this);

        RecyclerView recyclerView = findViewById(R.id.recycler_informasi_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(informasiHistoryAdapter);

        switchButton(1, this);
        tvInfromasi.setOnClickListener(this);
        tvHistory.setOnClickListener(this);
    }

    private void switchButton(int pos, Context context) {
        switch (pos) {
            case 1:
                tvInfromasi.setTextColor(context.getResources().getColor(R.color.black_palette));
                tvHistory.setTextColor(context.getResources().getColor(R.color.white_palette));
                tvInfromasi.setBackground(context.getDrawable(R.drawable.rectangle_rounded_orange_light_20dp));
                tvHistory.setBackground(context.getDrawable(R.drawable.rectangle_rounded_sherpa_blue));
                viewModel.loadData(1, prefConfig.getTokenUser(), prefConfig.getAccountNumber());
                informasiHistoryAdapter.setType(InformasiHistoryAdapter.INFORMATION);
                break;
            case 2:
                tvHistory.setTextColor(context.getResources().getColor(R.color.black_palette));
                tvInfromasi.setTextColor(context.getResources().getColor(R.color.white_palette));
                tvHistory.setBackground(context.getDrawable(R.drawable.rectangle_rounded_orange_light_20dp));
                tvInfromasi.setBackground(context.getDrawable(R.drawable.rectangle_rounded_sherpa_blue));
//                informasiHistoryAdapter.setProducts(DummyData.getProductsHistory());
                viewModel.loadData(2, prefConfig.getTokenUser(), prefConfig.getAccountNumber());
                informasiHistoryAdapter.setType(InformasiHistoryAdapter.HISTORY);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_start_informasi_history:
                switchButton(1, v.getContext());
                break;
            case R.id.tv_end_informasi_history:
                switchButton(2, v.getContext());
                break;
        }
    }

    @Override
    public void onLoadPortfolioData(List<Portfolio.Information> informationList) {
        informasiHistoryAdapter.setInformationList(informationList);
        informasiHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadHistoryTransaction(List<Portfolio.History> historyList) {
        informasiHistoryAdapter.setHistoryList(historyList);
        informasiHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String msg) {
        showSnackBar(msg);
    }

    @Override
    public void onShareNews(Portfolio.Information information) {
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra(PostActivity.DATA, Utils.toJSON(information));
        intent.putExtra(PostActivity.POST_TYPE, PostActivity.SHARE_TRADE);
        startActivity(intent);
    }

    @Override
    public void onShareNews(Portfolio.History history) {
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra(PostActivity.DATA, Utils.toJSON(history));
        intent.putExtra(PostActivity.POST_TYPE, PostActivity.SHARE_TRADE);
        startActivity(intent);
    }
}
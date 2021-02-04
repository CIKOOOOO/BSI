package com.bca.bsi.ui.basenavigation.portfolio;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.adapter.PortfolioAdapter;
import com.bca.bsi.model.Portfolio;
import com.bca.bsi.ui.basenavigation.portfolio.information.InformasiHistoryActivity;
import com.bca.bsi.ui.basenavigation.portfolio.produkmenu.ProdukChoiceActivity;
import com.bca.bsi.utils.BaseFragment;
import com.bca.bsi.utils.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class PortfolioFragment extends BaseFragment implements PortfolioAdapter.onItemClick, IPortfolioCallback {
    private PortfolioAdapter adapter;
    private ProgressBar progressBar;
    private ImageButton infoButton;
    private TextView reksaSelector, tvPercentReksadana;
    private ImageButton customBundleButton;

    private PortfolioViewModel portfolioViewModel;

    private onBundleClick onBundleClick;

    @Override
    public void onItemClick(Portfolio portfolio) {
        onBundleClick.onItemClick(portfolio);
    }

    @Override
    public void toReksadanaList() {
        Intent intent = new Intent(mActivity, ProdukChoiceActivity.class);
        mActivity.startActivity(intent);
    }

    @Override
    public void onLoadData(List<Portfolio> bundles) {
        adapter.setPortfolioList(bundles);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String msg) {
        showSnackBar(msg);
    }

    public interface onBundleClick {
        void onItemClick(Portfolio portfolio);

        void onInfoClick();
    }

    public void setOnBundleClick(PortfolioFragment.onBundleClick onBundleClick) {
        this.onBundleClick = onBundleClick;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portfolio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //recycler robo
        RecyclerView recyclerView = view.findViewById(R.id.recycler_robo_main);
        adapter = new PortfolioAdapter(this);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 0, true));
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        recyclerView.setAdapter(adapter);
//        adapter.setPortfolioList(getPortfolioList());

        portfolioViewModel = new ViewModelProvider(this).get(PortfolioViewModel.class);
        portfolioViewModel.setCallback(this);
        portfolioViewModel.loadBundle(prefConfig.getTokenUser(), prefConfig.getProfileRisiko(), prefConfig.getAccountNumber());

        // custom bundle button
        customBundleButton = view.findViewById(R.id.ib_custom_bundle);
        customBundleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProdukChoiceActivity.class);
                v.getContext().startActivity(intent);
            }
        });


        //info robo
        infoButton = view.findViewById(R.id.ib_tentang_smartbot);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBundleClick.onInfoClick();
            }
        });

        // reksa selected
        reksaSelector = view.findViewById(R.id.tv_reksa_selector);
        reksaSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InformasiHistoryActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        // setting progressbar percentage
        tvPercentReksadana = view.findViewById(R.id.tv_percent_reksadana);
        int percentReksaValue = 100;
        tvPercentReksadana.setText(percentReksaValue + "%");
        ProgressBar progressBar_reksa = view.findViewById(R.id.percent_reksadana);
        progressBar_reksa.setProgress(percentReksaValue);

        ProgressBar progressBar_obligasi = view.findViewById(R.id.percent_obligasi);
        progressBar_obligasi.setProgress(0);

    }

    private List<Portfolio> getPortfolioList() {
        List<Portfolio> portfolioList = new ArrayList<>();
        portfolioList.add(new Portfolio("1%", "0.02", "Bundle 1"));
        portfolioList.add(new Portfolio("3%", "0.04", "Bundle 2"));
        return portfolioList;
    }
}
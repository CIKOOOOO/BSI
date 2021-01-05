package com.bca.bsi.ui.basenavigation.portfolio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.adapter.PortfolioAdapter;
import com.bca.bsi.model.Portfolio;
import com.bca.bsi.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class PortfolioFragment extends BaseFragment {
    private PortfolioAdapter adapter;
    private ProgressBar progressBar;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false));
        adapter = new PortfolioAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setPortfolioList(getPortfolioList());


        // setting progressbar percentage
        ProgressBar progressBar_reksa = view.findViewById(R.id.percent_reksadana);
        progressBar_reksa.setProgress(50);

        ProgressBar progressBar_obligasi = view.findViewById(R.id.percent_obligasi);
        progressBar_obligasi.setProgress(20);

    }

    private List<Portfolio> getPortfolioList() {
        List<Portfolio> portfolioList = new ArrayList<>();
        portfolioList.add(new Portfolio("1%", "2%","Bundle 1"));
        portfolioList.add(new Portfolio("3%", "4%","Bundle 2"));
        return portfolioList;
    }
}
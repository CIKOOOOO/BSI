package com.bca.bsi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Portfolio;

import java.util.ArrayList;
import java.util.List;

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.Holder> {

    private static final int JUMLAH_PORTFOLIO = 3;
    private List<Portfolio> portfolioList = new ArrayList<>();
    private onItemClick onItemClick;

    public PortfolioAdapter(PortfolioAdapter.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setPortfolioList(List<Portfolio> portfolioList) {
        this.portfolioList = portfolioList;
    }

    public interface onItemClick {
        void onItemClick(Portfolio portfolio);
        void toReksadanaList();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout;
        if (viewType == 0) {
            layout = R.layout.recycler_robo_custom;
        } else layout = R.layout.recycler_robo_main;
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new Holder(v);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == portfolioList.size()) {
            return 0;
        }
        return 1;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if (position == portfolioList.size()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.toReksadanaList();
                }
            });

        } else {
            final Portfolio portfolio = portfolioList.get(position);
            holder.tvReturn.setText(portfolio.getExpReturn()+"%");
            holder.tvRisk.setText(portfolio.getRisk());
            if(position == 0) {
                holder.tvBundleName.setText("Bundle 1");
            } else if(position == 1) {
                holder.tvBundleName.setText("Bundle 2");
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onItemClick(portfolio);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return portfolioList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvTitleProduct, tvReturn, tvRisk, tvBundleName;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvReturn = itemView.findViewById(R.id.recycler_tv_return);
            tvRisk = itemView.findViewById(R.id.recycler_tv_risk);
            tvBundleName = itemView.findViewById(R.id.recycler_tv_bundle_name);
        }
    }

}

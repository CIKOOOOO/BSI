package com.bca.bsi.ui.basenavigation.products.detail.reksadana.detailreksadana;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ReksaDanaPerformanceAdapter extends RecyclerView.Adapter<ReksaDanaPerformanceAdapter.Holder> {

    private List<Product.Performance> performanceList;
    private Context mContext;

    public ReksaDanaPerformanceAdapter() {
        this.performanceList = new ArrayList<>();
    }

    public void setPerformanceList(List<Product.Performance> performanceList) {
        this.performanceList = performanceList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_performance_reksa_dana, parent, false);
        mContext = parent.getContext();
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Product.Performance performance = performanceList.get(position);
        if (performance != null) {
            holder.tvPeriod.setText(performance.getPeriod());

            int color = performance.getValue() < 0 ? R.color.red_palette : R.color.green_base_palette;

            holder.tvPerformance.setTextColor(mContext.getResources().getColor(color));
            holder.tvPerformance.setText(String.valueOf(performance.getValue()));
        }
    }

    @Override
    public int getItemCount() {
        return performanceList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvPeriod, tvPerformance;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvPerformance = itemView.findViewById(R.id.recycler_tv_value_performance_reksa_dana);
            tvPeriod = itemView.findViewById(R.id.recycler_tv_period_performance_reksa_dana);
        }
    }

}

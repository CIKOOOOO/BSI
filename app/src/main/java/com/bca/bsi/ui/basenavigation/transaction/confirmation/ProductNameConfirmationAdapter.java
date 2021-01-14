package com.bca.bsi.ui.basenavigation.transaction.confirmation;

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

public class ProductNameConfirmationAdapter extends RecyclerView.Adapter<ProductNameConfirmationAdapter.Holder> {

    private List<Product.DetailReksaDana> detailReksaDanas;
    private List<Double> percentageList;
    private double nominalPembelian;

    public ProductNameConfirmationAdapter() {
        this.detailReksaDanas = new ArrayList<>();
        this.percentageList = new ArrayList<>();
        nominalPembelian = -1;
    }

    public void setDetailReksaDanas(List<Product.DetailReksaDana> detailReksaDanas) {
        this.detailReksaDanas = detailReksaDanas;
    }

    public void setPercentageList(List<Double> percentageList) {
        this.percentageList = percentageList;
    }

    public void setNominalPembelian(double nominalPembelian) {
        this.nominalPembelian = nominalPembelian;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_detail_product_confirmation_transaction, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Product.DetailReksaDana detailReksaDana = detailReksaDanas.get(position);
        double nominalPembelian = this.nominalPembelian * percentageList.get(position);
        String biayaProdukPembelian = detailReksaDana.getBiayaPembelian().substring(0, 1).equals(".") ? "0" + detailReksaDana.getBiayaPembelian() : String.valueOf(Double.parseDouble(detailReksaDana.getBiayaPembelian()) / 100);

        double biayaPembelian = (Double.parseDouble(biayaProdukPembelian) / 100) * nominalPembelian;
        double totalPembelian = nominalPembelian + biayaPembelian;

        holder.tvName.setText(detailReksaDana.getName());
        holder.tvNominalPembelian.setText("Rp " + String.format("%,.2f", nominalPembelian));
        holder.tvNominalBiayaPembelian.setText("Rp " + String.format("%,.2f", biayaPembelian));
        holder.tvNominalTotalPembelian.setText("Rp " + String.format("%,.2f", totalPembelian));
    }

    @Override
    public int getItemCount() {
        return detailReksaDanas.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvName, tvNominalPembelian, tvNominalBiayaPembelian, tvNominalTotalPembelian;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.recycler_tv_name_confirmation_transaction);
            tvNominalPembelian = itemView.findViewById(R.id.recycler_tv_nominal_pembelian_confirmation);
            tvNominalBiayaPembelian = itemView.findViewById(R.id.recycler_tv_nominal_biaya_pembelian_confirmation);
            tvNominalTotalPembelian = itemView.findViewById(R.id.recycler_tv_nominal_total_pembelian_confirmation);
        }
    }
}

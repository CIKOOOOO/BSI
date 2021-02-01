package com.bca.bsi.ui.basenavigation.transaction.confirmation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;
import com.bca.bsi.utils.constant.Type;

import java.util.ArrayList;
import java.util.List;

public class ProductNameConfirmationAdapter extends RecyclerView.Adapter<ProductNameConfirmationAdapter.Holder> {

    public static final String SMART_BOT = "smart_bot";

    private List<Product.DetailReksaDana> detailReksaDanas;
    private List<Double> percentageList;
    private double nominalPembelian, totalSeluruhPembelian;
    private String type;

    public ProductNameConfirmationAdapter() {
        this.detailReksaDanas = new ArrayList<>();
        this.percentageList = new ArrayList<>();
        type = "";
        totalSeluruhPembelian = 0;
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

    public void setType(String type) {
        this.type = type;
    }

    public double getTotalSeluruhPembelian() {
        return totalSeluruhPembelian;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = viewType == 2 ? R.layout.recycler_single_name_confirmation_transaction : R.layout.recycler_detail_product_confirmation_transaction;
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Product.DetailReksaDana detailReksaDana = detailReksaDanas.get(position);

        holder.tvName.setText(detailReksaDana.getName());

        if (getItemViewType(position) != 2) {
            double nominalPembelian;
            double biayaPembelian;

            String biayaProdukPembelian;

            if (getItemViewType(position) == 1) {
                biayaProdukPembelian = detailReksaDana.getBiayaPembelian().substring(0, 1).equals(".") ? "0" + detailReksaDana.getBiayaPembelian() : String.valueOf(Double.parseDouble(detailReksaDana.getBiayaPembelian()) / 100);
                nominalPembelian = this.nominalPembelian * percentageList.get(position) / 100;
                biayaPembelian = (Double.parseDouble(biayaProdukPembelian) / 100) * nominalPembelian;
                totalSeluruhPembelian += biayaPembelian;
            } else {
                biayaProdukPembelian = detailReksaDana.getBiayaPembelian();
                nominalPembelian = this.nominalPembelian;
                biayaPembelian = Double.parseDouble(biayaProdukPembelian) / 100 * this.nominalPembelian;
            }

            double totalPembelian = nominalPembelian + biayaPembelian;

            holder.tvNominalPembelian.setText("Rp " + String.format("%,.2f", nominalPembelian));
            holder.tvNominalBiayaPembelian.setText("Rp " + String.format("%,.2f", biayaPembelian));
            holder.tvNominalTotalPembelian.setText("Rp " + String.format("%,.2f", totalPembelian));
        }
    }

    @Override
    public int getItemCount() {
        return detailReksaDanas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (type.equals(SMART_BOT)) {
            return 1;
        } else if (type.equals(Type.SELLING)) {
            return 2;
        } else {
            return 0;
        }
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

package com.bca.bsi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.ProductRekomen;

import java.util.ArrayList;
import java.util.List;

public class RoboRekomenAdapter extends RecyclerView.Adapter<RoboRekomenAdapter.Holder>{
    private List<ProductRekomen> productRekomenList = new ArrayList<>();
    private final int JUMLAH_PRODUK = 3;

    public void setProductRekomenList(List<ProductRekomen> productRekomenList) {
        this.productRekomenList = productRekomenList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_product_rekomen, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final ProductRekomen productRekomen = productRekomenList.get(position);
        holder.tvJenisReksa.setText(productRekomen.getJenisReksadana());
        holder.tvNab.setText(productRekomen.getNab());
        holder.tvKinerja.setText(productRekomen.getKinerja());
        holder.tvReksaName.setText(productRekomen.getProductName());
        holder.tvPercent.setText(productRekomen.getPercentage());
        holder.tvLastDate.setText(productRekomen.getLastDate());
    }

    @Override
    public int getItemCount() {
        return JUMLAH_PRODUK;
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvJenisReksa, tvReksaName, tvLastDate, tvPercent, tvKinerja, tvNab;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvJenisReksa = itemView.findViewById(R.id.tv_jenis_reksadana);
            tvLastDate = itemView.findViewById(R.id.tv_produk_last_date);
            tvPercent = itemView.findViewById(R.id.tv_percent_produk);
            tvReksaName = itemView.findViewById(R.id.tv_produk_name);
            tvKinerja = itemView.findViewById(R.id.tv_kinerja_value);
            tvNab = itemView.findViewById(R.id.tv_nab_value);
        }
    }
}

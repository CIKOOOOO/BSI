package com.bca.bsi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.ProductRekomen;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Constant;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class RoboRekomenAdapter extends RecyclerView.Adapter<RoboRekomenAdapter.Holder> {
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
        holder.tvReksaName.setText(productRekomen.getProductName());
        holder.tvPercent.setText(productRekomen.getPercentage() + "%");

        holder.tvNab.setText("Rp " + Utils.formatUang3(Double.parseDouble(productRekomen.getNab())));

        double kinerja = Double.parseDouble(productRekomen.getKinerja());
        int color = kinerja > 0 ? R.color.green_base_palette : R.color.red_palette;
        String kinerjaString = kinerja > 0 ? "+" + kinerja + "%" : kinerja + "%";
        holder.tvKinerja.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), color));
        holder.tvKinerja.setText(kinerjaString);

        try {
            String date = Utils.formatDateFromDateString(Constant.DATE_FORMAT_3, Constant.DATE_FORMAT_2, productRekomen.getLastDate());
            holder.tvLastDate.setText(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return productRekomenList.size();
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

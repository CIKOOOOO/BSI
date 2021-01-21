package com.bca.bsi.ui.basenavigation.portfolio.information;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.ProductIH;

import java.util.ArrayList;
import java.util.List;

public class InformasiHistoryAdapter extends RecyclerView.Adapter<InformasiHistoryAdapter.Holder> {

    public static final String HISTORY = "history";
    public static final String INFORMATION = "information";

    private String type;

    public void setType(String type) {
        this.type = type;
    }

    List<ProductIH> products = new ArrayList<>();

    public void setProducts(List<ProductIH> products) {
        this.products = products;
    }

    public InformasiHistoryAdapter() {
        type = "";
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = viewType == 1 ? R.layout.recycler_history : R.layout.recycler_informasi;
//        if (type.equals(HISTORY)) {
//            layout = R.layout.recycler_history;
//        } else {
//            layout = R.layout.recycler_informasi;
//        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final ProductIH product = products.get(position);
        // disini hrsnya ada if INF / HIS
        if (type.equals(HISTORY)) {
            // Bind untuk history
            holder.tvReksaName_HIS.setText(product.getName());
            holder.tvJenisReksa_HIS.setText(product.getJenis());
            holder.tvDate_HIS.setText(product.getDate());
            holder.tvUnit_HIS.setText(product.getUnit());
            holder.tvCost_HIS.setText(product.getCost());
        } else {
            // Bind untuk information
            holder.tvReksaName_INF.setText(product.getName());
            holder.tvJenisReksa_INF.setText(product.getJenis());
            holder.tvDate_INF.setText(product.getDate());
            holder.tvUnit_INF.setText(product.getUnit());
            holder.tvCost_INF.setText(product.getCost());
            holder.tvRaise_INF.setText("Rp. "+product.getRaise());
            if(product.getRaise().substring(0,1).equals("-")){
                holder.tvRaise_INF.setTextColor(Color.parseColor("#d12f24"));
            } else {
                holder.tvRaise_INF.setTextColor(Color.parseColor("#6bd35b"));
            }
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public int getItemViewType(int position) {
        return type.equals(HISTORY) ? 1 : 0;
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvReksaName_INF, tvJenisReksa_INF, tvDate_INF, tvUnit_INF, tvCost_INF, tvRaise_INF;
        private TextView tvReksaName_HIS, tvJenisReksa_HIS, tvDate_HIS, tvUnit_HIS, tvCost_HIS;

        public Holder(@NonNull View itemView) {
            super(itemView);
            // di recycler informasi
            tvReksaName_INF = itemView.findViewById(R.id.tv_reksadana_name_i);
            tvJenisReksa_INF = itemView.findViewById(R.id.tv_jenis_reksadana_i);
            tvDate_INF = itemView.findViewById(R.id.date_i);
            tvUnit_INF = itemView.findViewById(R.id.tv_unit_i);
            tvCost_INF = itemView.findViewById(R.id.tv_cost_i);
            tvRaise_INF = itemView.findViewById(R.id.tv_raise);

            // di recycler history
            tvReksaName_HIS = itemView.findViewById(R.id.tv_reksadana_name_h);
            tvJenisReksa_HIS = itemView.findViewById(R.id.tv_jenis_transaksi_h);
            tvDate_HIS = itemView.findViewById(R.id.date_h);
            tvUnit_HIS = itemView.findViewById(R.id.tv_unit_h);
            tvCost_HIS = itemView.findViewById(R.id.tv_cost_h);
        }
    }
}

package com.bca.bsi.ui.basenavigation.portfolio.information;

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



    List<ProductIH> products = new ArrayList<>();
    public void setProducts(List<ProductIH> products) {
        this.products = products;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvReksaName_INF, tvJenisReksa_INF, tvDate_INF, tvUnit_INF, tvCost_INF;
        private TextView tvReksaName_HIS, tvJenisReksa_HIS, tvDate_HIS, tvUnit_HIS, tvCost_HIS;

        public Holder(@NonNull View itemView) {
            super(itemView);
            // di recycler informasi
            tvReksaName_INF = itemView.findViewById(R.id.tv_reksadana_name_i);
            tvJenisReksa_INF = itemView.findViewById(R.id.tv_jenis_reksadana_i);
            tvDate_INF = itemView.findViewById(R.id.date_i);
            tvUnit_INF = itemView.findViewById(R.id.tv_unit_i);
            tvCost_INF = itemView.findViewById(R.id.tv_cost_i);

            // di recycler history
            tvReksaName_HIS = itemView.findViewById(R.id.tv_reksadana_name_h);
            tvJenisReksa_HIS = itemView.findViewById(R.id.tv_jenis_transaksi_h);
            tvDate_HIS = itemView.findViewById(R.id.date_h);
            tvUnit_HIS = itemView.findViewById(R.id.tv_unit_h);
            tvCost_HIS = itemView.findViewById(R.id.tv_cost_h);
        }
    }
}

package com.bca.bsi.ui.basenavigation.portfolio.produkmenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Portfolio;
import com.bca.bsi.model.ProductChoice;

import java.util.ArrayList;
import java.util.List;

public class ProdukChoiceAdapter extends RecyclerView.Adapter<ProdukChoiceAdapter.Holder> {

    List<ProductChoice> products = new ArrayList<>();

    public void setProducts(List<ProductChoice> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_product_reksadana_checkbox, parent, false);
        return new ProdukChoiceAdapter.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final ProductChoice productChoice = products.get(position);
        holder.tvJenisReksa.setText(productChoice.getType());
        holder.tvNab.setText(productChoice.getNab());
        holder.tvKinerja.setText(productChoice.getKinerja());
        holder.tvReksaName.setText(productChoice.getTitle());
        holder.cbChoosen.setActivated(productChoice.isChoosen());
        holder.tvLastDate.setText(productChoice.getLastDate());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvJenisReksa, tvReksaName, tvLastDate, tvKinerja, tvNab;
        private CheckBox cbChoosen;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvJenisReksa = itemView.findViewById(R.id.recycler_tv_type_product_reksadana_c);
            tvLastDate = itemView.findViewById(R.id.recycler_tv_date_product_reksadana_c);
            tvReksaName = itemView.findViewById(R.id.recycler_tv_title_product_reksadana_c);
            tvKinerja = itemView.findViewById(R.id.recycler_tv_kinerja_product_reksadana_c);
            tvNab = itemView.findViewById(R.id.recycler_tv_nab_product_reksadana_c);
            cbChoosen = itemView.findViewById(R.id.cb_produk_c);
        }
    }

    /////// BIKIN KLIK /////////
    public interface onItemClick {
        void onItemClick(Portfolio portfolio);
    }

}

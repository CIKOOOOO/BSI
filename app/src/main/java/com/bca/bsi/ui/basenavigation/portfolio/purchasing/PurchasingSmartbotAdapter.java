package com.bca.bsi.ui.basenavigation.portfolio.purchasing;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.ProductRekomen;

import java.util.ArrayList;
import java.util.List;

public class PurchasingSmartbotAdapter extends RecyclerView.Adapter<PurchasingSmartbotAdapter.Holder> {

    private List<ProductRekomen> productRekomenList = new ArrayList<>();
    private final int JUMLAH_PRODUK = 3;

    public void setProductRekomenList(List<ProductRekomen> productRekomenList) {
        this.productRekomenList = productRekomenList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_product_p, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final ProductRekomen productRekomen = productRekomenList.get(position);
        holder.tvJenisReksa.setText(productRekomen.getJenisReksadana());
        holder.tvNab.setText(productRekomen.getNab());
        holder.tvKinerja.setText(productRekomen.getKinerja());
        holder.tvReksaName.setText(productRekomen.getProductName());
        holder.etPercent.setText(productRekomen.getPercentage());
        holder.tvLastDate.setText(productRekomen.getLastDate());

        if(productRekomenList.size()<=2){
            holder.ibClear.setVisibility(View.INVISIBLE);
            holder.ibClear.setEnabled(false);
        } else {
            holder.ibClear.setVisibility(View.VISIBLE);
            holder.ibClear.setEnabled(true);
        }

        holder.ibPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isiPercentString = holder.etPercent.getText().toString();
                int nilaiPercent = Integer.parseInt(isiPercentString);
                if(nilaiPercent<100) {
                    nilaiPercent += 1;
                }
                isiPercentString = String.valueOf(nilaiPercent);
                holder.etPercent.setText(isiPercentString);
                productRekomen.setPercentage(isiPercentString);
            }
        });

        holder.ibMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isiPercentString = holder.etPercent.getText().toString();
                int nilaiPercent = Integer.parseInt(isiPercentString);
                if(nilaiPercent>0) {
                    nilaiPercent -= 1;
                }
                isiPercentString = String.valueOf(nilaiPercent);
                holder.etPercent.setText(isiPercentString);
                productRekomen.setPercentage(isiPercentString);
            }
        });

        holder.ibClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getItemCount()>1) {
                    productRekomenList.remove(position);
                }

                notifyDataSetChanged();
            }
        });

        // Atur angka persen gaboleh 0xx
        holder.etPercent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char zero = '0';
                if(holder.etPercent.getText().length()==2 && holder.etPercent.getText().charAt(0)==zero){
                    holder.etPercent.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return productRekomenList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvJenisReksa, tvReksaName, tvLastDate, tvKinerja, tvNab, etPercent;
        private ImageButton ibPlus, ibMin, ibClear;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvJenisReksa = itemView.findViewById(R.id.tv_jenis_reksadana_p);
            tvLastDate = itemView.findViewById(R.id.tv_produk_last_date_p);
            tvReksaName = itemView.findViewById(R.id.tv_produk_name_p);
            tvKinerja = itemView.findViewById(R.id.tv_kinerja_value_p);
            tvNab = itemView.findViewById(R.id.tv_nab_value_p);
            etPercent = itemView.findViewById(R.id.et_percent_produk_p);
            ibPlus = itemView.findViewById(R.id.ib_plus);
            ibMin = itemView.findViewById(R.id.ib_min);
            ibClear = itemView.findViewById(R.id.ib_clear);
        }
    }

}

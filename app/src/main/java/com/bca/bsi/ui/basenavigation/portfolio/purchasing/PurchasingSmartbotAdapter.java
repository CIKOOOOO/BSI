package com.bca.bsi.ui.basenavigation.portfolio.purchasing;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.ProductRekomen;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Constant;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PurchasingSmartbotAdapter extends RecyclerView.Adapter<PurchasingSmartbotAdapter.Holder> {

    private List<ProductRekomen> productRekomenList = new ArrayList<>();
    private onEventMatch onEventMatch;

    public interface onEventMatch {
        void sendValue(String ids, String proportion);
    }

    public PurchasingSmartbotAdapter(PurchasingSmartbotAdapter.onEventMatch onEventMatch) {
        this.onEventMatch = onEventMatch;
    }

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
        holder.tvKinerja.setText("+" + productRekomen.getKinerja() + "%");
        holder.tvReksaName.setText(productRekomen.getProductName());
        holder.etPercent.setText(productRekomen.getPercentage());

        String date = null;
        try {
            date = Utils.formatDateFromDateString(Constant.DATE_FORMAT_3, Constant.DATE_FORMAT_2, productRekomen.getLastDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvLastDate.setText(date);

        if (productRekomenList.size() <= 2) {
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
                if (nilaiPercent < 100) {
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
                if (nilaiPercent > 0) {
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
                if (getItemCount() > 1) {
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
                if (holder.etPercent.getText().length() == 2 && holder.etPercent.getText().charAt(0) == zero) {
                    holder.etPercent.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) {
                    productRekomen.setPercentage(holder.etPercent.getText().toString());
                    if (is100()) {
                        //TODO HIT API reksaid + proportions
                        onEventMatch.sendValue(getReksaIds(), getProportions());
                    }
                } else {
                    holder.etPercent.setText("0");
                }

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

    public String getReksaIds() {
        String res = "";
        for (ProductRekomen product : this.productRekomenList
        ) {
            res += product.getReksaId() + ",";
        }
        return res.substring(0, res.length() - 1);
    }

    public String getProportions() {
        String res = "";
        for (ProductRekomen product : this.productRekomenList) {
            res += product.getPercentage() + ",";
        }
        return res.substring(0, res.length() - 1);
    }

    public boolean is100() {
        int total = 0;
        for (ProductRekomen produk : this.productRekomenList) {
            Log.e("asdas", produk.getPercentage() + " Total : " + total);
            total += Integer.parseInt(produk.getPercentage());

        }
        return total == 100;
    }

}

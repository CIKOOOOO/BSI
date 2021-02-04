package com.bca.bsi.ui.basenavigation.more.calculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Constant;

import java.text.ParseException;
import java.util.List;

public class ProductsCalculatorAdapter extends RecyclerView.Adapter<ProductsCalculatorAdapter.MyViewHolder> {

    private List<Product.ReksaDana> products;

    public void setProductsCalculatorAdapter (List<Product.ReksaDana> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_product_reksadana_calculator, parent, false);
        return new ProductsCalculatorAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Product.ReksaDana productChoice = products.get(position);
        holder.tvJenisReksa.setText(productChoice.getType());
        holder.tvNab.setText(productChoice.getNab());
        holder.tvKinerja.setText(productChoice.getKinerja());
        holder.tvReksaName.setText(productChoice.getName());

        String date = null;
        try {
            date = Utils.formatDateFromDateString(Constant.DATE_FORMAT_3, Constant.DATE_FORMAT_2, productChoice.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvLastDate.setText(date);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvJenisReksa, tvReksaName, tvLastDate, tvKinerja, tvNab;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvJenisReksa = itemView.findViewById(R.id.recycler_tv_type_product_reksadana_c);
            tvLastDate = itemView.findViewById(R.id.recycler_tv_date_product_reksadana_c);
            tvReksaName = itemView.findViewById(R.id.recycler_tv_title_product_reksadana_c);
            tvKinerja = itemView.findViewById(R.id.recycler_tv_kinerja_product_reksadana_c);
            tvNab = itemView.findViewById(R.id.recycler_tv_nab_product_reksadana_c);
        }
    }
}

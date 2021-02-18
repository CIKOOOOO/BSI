package com.bca.bsi.ui.basenavigation.more.calculator.popup;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;
import com.bca.bsi.ui.basenavigation.products.detail.reksadana.ReksadanaProductAdapter;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Constant;

import java.text.ParseException;
import java.util.List;

public class ProductsCalculatorAdapter extends RecyclerView.Adapter<ProductsCalculatorAdapter.MyViewHolder> {

    private List<Product.ReksaDana> products;
    private ProductsCalculatorAdapter.onItemClick onItemClick;

    public interface onItemClick {
        void onReksaDanaClick(Product.ReksaDana reksaDana);
    }

    public ProductsCalculatorAdapter(ProductsCalculatorAdapter.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setProductsCalculatorAdapter (List<Product.ReksaDana> products) {
        System.out.println("PRODUCTNYA UDAH DI SET YA");
        this.products = products;
    }

    public List<Product.ReksaDana> getProducts() {
        return products;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_product_reksadana_calculator, parent, false);
        return new ProductsCalculatorAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product.ReksaDana productChoice = products.get(position);


        holder.tvJenisReksa.setText(productChoice.getType());
        holder.tvNab.setText(productChoice.getNab());

        try {
            Double kinerja1Bulan = Double.parseDouble(productChoice.getKinerja());
            if (kinerja1Bulan < 0) {
                holder.tvKinerja.setTextColor(Color.parseColor("#ff0000"));
            }else{
                holder.tvKinerja.setTextColor(Color.parseColor("#00c300"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.tvKinerja.setText(productChoice.getKinerja());

        holder.tvReksaName.setText(productChoice.getName());

        String date = null;
        try {
            date = Utils.formatDateFromDateString(Constant.DATE_FORMAT_3, Constant.DATE_FORMAT_2, productChoice.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvLastDate.setText(date);

        holder.tvReksaName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onReksaDanaClick(productChoice);

            }
        });

    }

    @Override
    public int getItemCount() {
        /*
        if(!products.isEmpty()){
            return products.size();
        }else{
            return 10;
        }*/

        //return 10;

        return products.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvJenisReksa, tvReksaName, tvLastDate, tvKinerja, tvNab;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvJenisReksa = itemView.findViewById(R.id.recycler_tv_type_product_reksadana_cp);
            tvLastDate = itemView.findViewById(R.id.recycler_tv_date_product_reksadana_cp);
            tvReksaName = itemView.findViewById(R.id.recycler_tv_title_product_reksadana_cp);
            tvKinerja = itemView.findViewById(R.id.recycler_tv_kinerja_product_reksadana_cp);
            tvNab = itemView.findViewById(R.id.recycler_tv_nab_product_reksadana_cp);
        }
    }
}

package com.bca.bsi.ui.basenavigation.portfolio.produkmenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Portfolio;
import com.bca.bsi.model.Product;
import com.bca.bsi.utils.Utils;
import com.bca.bsi.utils.constant.Constant;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ProdukChoiceAdapter extends RecyclerView.Adapter<ProdukChoiceAdapter.Holder> {

    List<Product.ReksaDana> products = new ArrayList<>();

    public void setProducts(List<Product.ReksaDana> products) {
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
        final Product.ReksaDana productChoice = products.get(position);
        holder.tvJenisReksa.setText(productChoice.getType());
        holder.tvNab.setText(productChoice.getNab());
        holder.tvKinerja.setText(productChoice.getKinerja());
        holder.tvReksaName.setText(productChoice.getName());
        holder.cbChoosen.setChecked(productChoice.isChoosen());

        String date = null;
        try {
            date = Utils.formatDateFromDateString(Constant.DATE_FORMAT_3,Constant.DATE_FORMAT_2,productChoice.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvLastDate.setText(date);

        holder.cbChoosen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                products.get(position).setChoosen(isChecked);
                holder.cbChoosen.setChecked(productChoice.isChoosen());
            }
        });

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

    public String getReksaIds(){
        String res = "";
        for (Product.ReksaDana product:this.products
             ) {
            if(product.isChoosen()){
                res += product.getReksadanaID()+",";
            }
        }
        return res.substring(0,res.length()-1);
    }

    public int getChoosenAmount(){
        int res = 0;
        for (Product.ReksaDana product:this.products
        ) {
            if(product.isChoosen()){
                res += 1;
            }
        }
        return res;
    }

}

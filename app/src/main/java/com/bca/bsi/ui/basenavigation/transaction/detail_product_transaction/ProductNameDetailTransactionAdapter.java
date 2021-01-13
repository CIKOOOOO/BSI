package com.bca.bsi.ui.basenavigation.transaction.detail_product_transaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductNameDetailTransactionAdapter extends RecyclerView.Adapter<ProductNameDetailTransactionAdapter.Holder> {

    private List<Product.ProductTransaction> productTransactions;

    public ProductNameDetailTransactionAdapter() {
        productTransactions = new ArrayList<>();
    }

    public void setProductTransactions(List<Product.ProductTransaction> productTransactions) {
        this.productTransactions = productTransactions;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_product_name_detail_product_transaction, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Product.ProductTransaction productTransaction = productTransactions.get(position);
        if (productTransaction != null) {
            holder.tvName.setText(productTransaction.getName());
            holder.tvDate.setText(productTransaction.getDate());
            holder.tvPrice.setText("Rp. " + productTransaction.getPrice() + "\nNAB/Unit");
        }
    }

    @Override
    public int getItemCount() {
        return productTransactions.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvName, tvDate, tvPrice;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.recycler_tv_name_detail_transaction);
            tvDate = itemView.findViewById(R.id.recycler_tv_date_detail_transaction);
            tvPrice = itemView.findViewById(R.id.recycler_tv_price_detail_transaction);
        }
    }

}

package com.bca.bsi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;
import com.bca.bsi.utils.constant.Type;
import com.bumptech.glide.Glide;

import java.util.List;

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeAdapter.Holder> {

    private List<Object> objectList;
    private String type;
    private objectOnClick objectOnClick;
    private Context context;

    public interface objectOnClick {
        void onProductClick();
    }

    public ProductTypeAdapter(List<Object> objectList, String type, ProductTypeAdapter.objectOnClick objectOnClick) {
        this.objectList = objectList;
        this.type = type;
        this.objectOnClick = objectOnClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_product_type, parent, false);
        context = view.getContext();
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        switch (type) {
            case Type.PRODUCT:
                Product.ProductType productType = (Product.ProductType) objectList.get(position);
                holder.tvTitle.setText(productType.getTitle());
                holder.tvDescription.setText(productType.getDescription());
                Glide.with(context)
                        .load(productType.getImage())
                        .into(holder.imgProduct);
                if (position == 1) {
                    holder.btnFirst.setText("Pasar Perdana");
                    holder.btnSecond.setText("Pasar Sekunder");
                    holder.btnSecond.setVisibility(View.VISIBLE);
                } else {
                    holder.btnSecond.setVisibility(View.GONE);
                    holder.btnFirst.setText(context.getString(R.string.see_product));
                }
                break;
            case Type.LEARNING:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDescription;
        private TextView btnFirst, btnSecond;
        private ImageView imgProduct;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.recycler_tv_title_product_type);
            tvDescription = itemView.findViewById(R.id.recycler_tv_description_product_type);
            btnFirst = itemView.findViewById(R.id.recyler_btn_first_product_type);
            btnSecond = itemView.findViewById(R.id.recycler_btn_second_product_type);
            imgProduct = itemView.findViewById(R.id.recycler_img_product_type);
        }
    }

}

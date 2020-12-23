package com.bca.bsi.ui.basenavigation.products.detail.reksadana;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ReksadanaProductAdapter extends RecyclerView.Adapter<ReksadanaProductAdapter.Holder> {

    private static final int EMPTY = 0;

    private List<Product.ReksaDana> reksaDanaList;
    private onItemClick onItemClick;
    private Context mContext;

    public interface onItemClick {
        void onReksaDanaClick(Product.ReksaDana reksaDana);
    }

    public ReksadanaProductAdapter(ReksadanaProductAdapter.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
        reksaDanaList = new ArrayList<>();
    }

    public void setReksaDanaList(List<Product.ReksaDana> reksaDanaList) {
        this.reksaDanaList = reksaDanaList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = viewType == EMPTY ? R.layout.recycler_empty : R.layout.recycler_product_reksadana;
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        mContext = v.getContext();
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {
        if (getItemViewType(position) != EMPTY) {
            final Product.ReksaDana reksaDana = reksaDanaList.get(position);
            holder.tvTitle.setText(reksaDana.getName());
            holder.tvType.setText(reksaDana.getType());
            holder.tvDate.setText(reksaDana.getDate());
            holder.tvKinerja.setText("+" + reksaDana.getKinerja() + "%");
            holder.tvNab.setText("Rp " + reksaDana.getNab());
            holder.tvBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onReksaDanaClick(reksaDana);
                }
            });
            holder.imgBtnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else {
            holder.tvEmpty.setText(mContext.getString(R.string.product_not_found));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return reksaDanaList.size() == 0 ? EMPTY : 1;
    }

    @Override
    public int getItemCount() {
        return reksaDanaList.size() == 0 ? 1 : reksaDanaList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvType, tvDate, tvKinerja, tvNab, tvBuy, tvEmpty;
        private ImageButton imgBtnMore;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.recycler_tv_title_product_reksadana);
            tvType = itemView.findViewById(R.id.recycler_tv_type_product_reksadana);
            tvDate = itemView.findViewById(R.id.recycler_tv_date_product_reksadana);
            tvKinerja = itemView.findViewById(R.id.recycler_tv_kinerja_product_reksadana);
            tvNab = itemView.findViewById(R.id.recycler_tv_nab_product_reksadana);
            tvBuy = itemView.findViewById(R.id.recycler_tv_buy_product_reksadana);
            imgBtnMore = itemView.findViewById(R.id.recycler_img_btn_more_product_reksadana);
            tvEmpty = itemView.findViewById(R.id.recycler_tv_empty);
        }
    }
}

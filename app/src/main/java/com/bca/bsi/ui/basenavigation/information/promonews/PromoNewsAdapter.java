package com.bca.bsi.ui.basenavigation.information.promonews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.PromoNews;
import com.bca.bsi.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PromoNewsAdapter extends RecyclerView.Adapter<PromoNewsAdapter.Holder> {

    private List<PromoNews> promoNewsList;
    private onItemClick onItemClick;

    public interface onItemClick {
        void onObjectClick(PromoNews promoNews);
    }

    public PromoNewsAdapter(PromoNewsAdapter.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
        this.promoNewsList = new ArrayList<>();
    }

    public void setPromoNewsList(List<PromoNews> promoNewsList) {
        this.promoNewsList = promoNewsList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_promo_news, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final PromoNews promoNews = promoNewsList.get(position);
        holder.tvTitle.setText(promoNews.getTitle());
        holder.tvDesc.setText(promoNews.getDescription());
        holder.tvDate.setText(promoNews.getDate());
        if (!promoNews.getImage().isEmpty()) {
            Picasso.get()
                    .load(Utils.imageURL(promoNews.getImage()))
                    .into(holder.imgView);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onObjectClick(promoNews);
            }
        });
    }

    @Override
    public int getItemCount() {
        return promoNewsList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvDesc, tvDate;
        private ImageView imgView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.recycler_tv_title_promo_news);
            tvDesc = itemView.findViewById(R.id.recycler_tv_description_promo_news);
            tvDate = itemView.findViewById(R.id.recycler_tv_date_promo_news);
            imgView = itemView.findViewById(R.id.recycler_img_promo_news);
        }
    }
}

package com.bca.bsi.ui.basenavigation.portfolio.information;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Portfolio;
import com.bca.bsi.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class InformasiHistoryAdapter extends RecyclerView.Adapter<InformasiHistoryAdapter.Holder> {

    public static final String HISTORY = "history";
    public static final String INFORMATION = "information";

    private List<Portfolio.Information> informationList;
    private List<Portfolio.History> historyList;
    private onClickShare onClickShare;

    private String type;

    public interface onClickShare {
        void onShareNews(Portfolio.Information information);

        void onShareNews(Portfolio.History history);

        void onSell(Portfolio.Information information);
    }

    public void setType(String type) {
        this.type = type;
    }

    public InformasiHistoryAdapter(onClickShare onClickShare) {
        type = "";
        this.onClickShare = onClickShare;
        this.informationList = new ArrayList<>();
        this.historyList = new ArrayList<>();
    }

    public void clearData() {
        this.historyList.clear();
        this.informationList.clear();
        notifyDataSetChanged();
    }

    public void setInformationList(List<Portfolio.Information> informationList) {
        this.informationList = informationList;
    }

    public void setHistoryList(List<Portfolio.History> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout;
        if (viewType == 2) {
            layout = R.layout.custom_loading_white;
        } else {
            layout = viewType == 1 ? R.layout.recycler_history : R.layout.recycler_informasi;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
//        final ProductIH product = products.get(position);
        // disini hrsnya ada if INF / HIS
        if (getItemViewType(position) == 1) {
            // Bind untuk history

            Portfolio.History history = historyList.get(position);

            holder.tvReksaName_HIS.setText(history.getReksadanaName());
            holder.tvJenisReksa_HIS.setText(history.getTransactionType());
            holder.tvDate_HIS.setText(history.getDate());
            holder.tvUnit_HIS.setText(history.getReksaDanaUnit());
            holder.tvCost_HIS.setText(Utils.formatUang3(Double.parseDouble(history.getAmount())));
//            holder.tvCost_HIS.setText(history.getAmount());

            holder.tvShare_HIS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickShare.onShareNews(history);
                }
            });
        } else if (getItemViewType(position) == 0) {
            // Bind untuk information
            Portfolio.Information information = informationList.get(position);
            holder.tvReksaName_INF.setText(information.getName());
            holder.tvJenisReksa_INF.setText(information.getJenis());
            holder.tvDate_INF.setText(information.getDate());
            holder.tvUnit_INF.setText(String.valueOf(information.getUnit()));
            holder.tvCost_INF.setText(Utils.formatUang3(information.getCost()));
            holder.tvRaise_INF.setText("Rp. " + Utils.formatUang3(information.getRaise()));
            if (information.getRaise() < 0) {
                holder.tvRaise_INF.setTextColor(Color.parseColor("#d12f24"));
            } else {
                holder.tvRaise_INF.setTextColor(Color.parseColor("#6bd35b"));
            }

            holder.tvShare_INF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickShare.onShareNews(information);
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickShare.onSell(information);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int types;
        if (type.equals(HISTORY)) {
            if (historyList.size() == 0) {
                types = 1;
            } else {
                types = historyList.size();
            }
        } else {
            if (informationList.size() == 0) {
                types = 1;
            } else {
                types = informationList.size();
            }
        }
        return types;
    }

    @Override
    public int getItemViewType(int position) {
        int types;
        if (type.equals(HISTORY)) {
            if (historyList.size() == 0) {
                types = 2;
            } else {
                types = 1;
            }
        } else {
            if (informationList.size() == 0) {
                types = 2;
            } else {
                types = 0;
            }
        }
        return types;
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvReksaName_INF, tvJenisReksa_INF, tvDate_INF, tvUnit_INF, tvCost_INF, tvRaise_INF, tvShare_INF;
        private TextView tvReksaName_HIS, tvJenisReksa_HIS, tvDate_HIS, tvUnit_HIS, tvCost_HIS, tvShare_HIS;

        public Holder(@NonNull View itemView) {
            super(itemView);
            // di recycler informasi
            tvReksaName_INF = itemView.findViewById(R.id.tv_reksadana_name_i);
            tvJenisReksa_INF = itemView.findViewById(R.id.tv_jenis_reksadana_i);
            tvDate_INF = itemView.findViewById(R.id.date_i);
            tvUnit_INF = itemView.findViewById(R.id.tv_unit_i);
            tvCost_INF = itemView.findViewById(R.id.tv_cost_i);
            tvRaise_INF = itemView.findViewById(R.id.tv_raise);
            tvShare_INF = itemView.findViewById(R.id.tv_share_i);

            // di recycler history
            tvReksaName_HIS = itemView.findViewById(R.id.tv_reksadana_name_h);
            tvJenisReksa_HIS = itemView.findViewById(R.id.tv_jenis_transaksi_h);
            tvDate_HIS = itemView.findViewById(R.id.date_h);
            tvUnit_HIS = itemView.findViewById(R.id.tv_unit_h);
            tvCost_HIS = itemView.findViewById(R.id.tv_cost_h);
            tvShare_HIS = itemView.findViewById(R.id.tv_share_h);
        }
    }
}

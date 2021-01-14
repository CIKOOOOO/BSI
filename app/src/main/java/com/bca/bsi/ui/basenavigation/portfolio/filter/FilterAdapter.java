package com.bca.bsi.ui.basenavigation.portfolio.filter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.FilterJenisReksa;

import java.util.ArrayList;
import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.Holder>{

    List<FilterJenisReksa> filterJenisReksaList = new ArrayList<>();

    public List<FilterJenisReksa> getFilterJenisReksaList() {
        return filterJenisReksaList;
    }

    public void setFilterJenisReksaList(List<FilterJenisReksa> filterJenisReksaList) {
        this.filterJenisReksaList = filterJenisReksaList;
    }

    int JUMLAH_JENIS_REKSADANA = 5;

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_filter, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final FilterJenisReksa filterJenisReksa = filterJenisReksaList.get(position) ;
        holder.tvJenisReksa.setText(filterJenisReksa.getJenisReksa());
//        holder.cbChoosen.setActivated(filterJenisReksa.isChoosen());
        holder.cbChoosen.setChecked(filterJenisReksa.isChoosen());
    }

    @Override
    public int getItemCount() {
        return JUMLAH_JENIS_REKSADANA;
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvJenisReksa;
        private CheckBox cbChoosen;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvJenisReksa = itemView.findViewById(R.id.tv_filter_jenis_reksa);
            cbChoosen = itemView.findViewById(R.id.cb_filter_jenis_reksa);
        }
    }
}

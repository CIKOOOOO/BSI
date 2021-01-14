package com.bca.bsi.ui.basenavigation.portfolio.filter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.SortJenisReksa;

import java.util.ArrayList;
import java.util.List;

public class SortAdapter extends RecyclerView.Adapter<SortAdapter.Holder> {
    List<SortJenisReksa> sortJenisReksas = new ArrayList<>();
    int JUMLAH_JENIS_SORT = 4;
    private onWholeClick onWholeClick;

    public SortAdapter(onWholeClick onWholeClick) {
        this.onWholeClick = onWholeClick;
    }

    private int lastPosition = 0;

    public void resetButton(){
        setCheck(0,sortJenisReksas.get(0));
    }

    public void setCheck(int currentPosition, SortJenisReksa sortJenisReksa){
        if(lastPosition != -1){
            final SortJenisReksa sortJenisReksa2 = sortJenisReksas.get(lastPosition);
            sortJenisReksa2.setChoosen(false);
            sortJenisReksas.set(lastPosition,sortJenisReksa2);
        }
        sortJenisReksa.setChoosen(true);
        lastPosition = currentPosition;
        sortJenisReksas.set(currentPosition,sortJenisReksa);
        notifyDataSetChanged();
    }

    public void setSortJenisReksas(List<SortJenisReksa> sortJenisReksas) {
        this.sortJenisReksas = sortJenisReksas;
    }

    public interface onWholeClick{
        void onItemClick(int position);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_sort, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final SortJenisReksa sortJenisReksa = sortJenisReksas.get(position);
        holder.tvSortName.setText(sortJenisReksa.getSortName());
        holder.tvSortTypeStart.setText(sortJenisReksa.getSortTypeStart());
        holder.tvSortTypeEnd.setText(sortJenisReksa.getSortTypeEnd());
        holder.imgChoosen.setVisibility(isShown(sortJenisReksa.isChoosen()));

        // Kalau dipilih
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheck(position, sortJenisReksa);
                onWholeClick.onItemClick(position);
            }
        });
    }

    public int isShown(boolean isChoosen){
        if(isChoosen){
            return View.VISIBLE;
        } else {
            return View.INVISIBLE;
        }
    }

    @Override
    public int getItemCount() {
        return JUMLAH_JENIS_SORT;
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvSortName, tvSortTypeStart, tvSortTypeEnd;
        private ImageView imgChoosen;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvSortName = itemView.findViewById(R.id.tv_sort_jenis);
            tvSortTypeStart = itemView.findViewById(R.id.tv_sort_order_start);
            tvSortTypeEnd = itemView.findViewById(R.id.tv_sort_order_end);
            imgChoosen = itemView.findViewById(R.id.img_checked_sort);
        }
    }

}

package com.bca.bsi.ui.basenavigation.transaction.detail_transaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.bsi.R;
import com.bca.bsi.model.Transaction;
import com.bca.bsi.ui.basenavigation.transaction.confirmation.ConfirmationTransactionActivity;
import com.bca.bsi.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class DetailTransactionAdapter extends RecyclerView.Adapter<DetailTransactionAdapter.Holder> {

    private List<Transaction.TransactionResult> transactionResultList;
    private String type;

    public DetailTransactionAdapter() {
        this.transactionResultList = new ArrayList<>();
    }

    public void setTransactionResultList(List<Transaction.TransactionResult> transactionResultList) {
        this.transactionResultList = transactionResultList;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_detail_transaction, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Transaction.TransactionResult transactionResult = transactionResultList.get(position);

        String dummyNominalPembelian, dummyNominalBiayaPembelian, dummyTotalPenjualan, dummyRekeningTujuan;

        if (type.equals(ConfirmationTransactionActivity.SELLING_FROM_CONFIRMATION_ACTIVITY)) {
            dummyNominalPembelian = "Nominal Penjualan";
            dummyNominalBiayaPembelian = "Nominal Biaya Penjualan";
            dummyTotalPenjualan = "Nominal Total Penjualan";
        } else {
            dummyNominalPembelian = "Nominal Pembelian";
            dummyNominalBiayaPembelian = "Nominal Biaya Pembelian";
            dummyTotalPenjualan = "Nominal Total Pembelian";
        }

        holder.tvProductName.setText(transactionResult.getProductName());
        holder.tvNominalPembelian.setText("Rp " + Utils.formatUang2(Double.parseDouble(transactionResult.getNominalTransaksi())));
        holder.tvNominalBiayaPembelian.setText("Rp " + Utils.formatUang2(Double.parseDouble(transactionResult.getNominalBiayaTransaksi())));
        holder.tvNominalTotalPembelian.setText("Rp " + Utils.formatUang2(Double.parseDouble(transactionResult.getNominalTotalTransaksi())));
        holder.tvReferenceNumber.setText(transactionResult.getReferenceNumber());

        holder.setData(dummyNominalPembelian, dummyNominalBiayaPembelian, dummyTotalPenjualan);
    }

    @Override
    public int getItemCount() {
        return transactionResultList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView tvProductName, tvNominalPembelian, tvNominalBiayaPembelian, tvNominalTotalPembelian, tvReferenceNumber;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.recycler_tv_product_name_detail_transaction);
            tvNominalPembelian = itemView.findViewById(R.id.recycler_tv_nominal_pembelian_detail_transaction);
            tvNominalBiayaPembelian = itemView.findViewById(R.id.recycler_tv_nominal_biaya_pembelian_detail_pembelian);
            tvNominalTotalPembelian = itemView.findViewById(R.id.recycler_tv_nominal_total_pembelian_detail_pembelian);
            tvReferenceNumber = itemView.findViewById(R.id.recycler_tv_referensi_number_detail_transaction);
        }

        public void setData(String dummyNominalPembelian, String dummyNominalBiayaPembelian, String dummyTotalPenjualan) {
            ((TextView) itemView.findViewById(R.id.tv_01_detail_transaction)).setText(dummyNominalPembelian);
            ((TextView) itemView.findViewById(R.id.tv_02_detail_transaction)).setText(dummyNominalBiayaPembelian);
            ((TextView) itemView.findViewById(R.id.tv_03_detail_transaction)).setText(dummyTotalPenjualan);
        }
    }
}

package com.bca.bsi.model;

import com.google.gson.annotations.SerializedName;

public class Transaction {

    public static class Purchasing{

        @SerializedName("bca_id")
        private String bcaID;

        @SerializedName("amount")
        private String amount;

        @SerializedName("no_rekening")
        private String accountNumber;

        @SerializedName("reksa_dana_id")
        private String reksaDanaID;

        @SerializedName("transaction_type")
        private String transactionType;

        @SerializedName("tipe_pembayaran")
        private String paymentType;

        @SerializedName("nominal_biaya_pembelian")
        private String nominalBiayaPembelian;

        @SerializedName("reksa_dana_unit")
        private String reksaDanaUnit;

        private String nab;

        public Purchasing() {
        }

        public String getBcaID() {
            return bcaID;
        }

        public void setBcaID(String bcaID) {
            this.bcaID = bcaID;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getReksaDanaID() {
            return reksaDanaID;
        }

        public void setReksaDanaID(String reksaDanaID) {
            this.reksaDanaID = reksaDanaID;
        }

        public String getTransactionType() {
            return transactionType;
        }

        public void setTransactionType(String transactionType) {
            this.transactionType = transactionType;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getNominalBiayaPembelian() {
            return nominalBiayaPembelian;
        }

        public void setNominalBiayaPembelian(String nominalBiayaPembelian) {
            this.nominalBiayaPembelian = nominalBiayaPembelian;
        }

        public String getReksaDanaUnit() {
            return reksaDanaUnit;
        }

        public void setReksaDanaUnit(String reksaDanaUnit) {
            this.reksaDanaUnit = reksaDanaUnit;
        }

        public String getNab() {
            return nab;
        }

        public void setNab(String nab) {
            this.nab = nab;
        }
    }

    public static class TransactionResult{

        @SerializedName("nama_produk")
        private String productName;

        @SerializedName("tipe_pembayaran")
        private String paymentType;

        @SerializedName("nominal_transaksi")
        private String nominalTransaksi;

        @SerializedName("nominal_biaya_transaksi")
        private String nominalBiayaTransaksi;

        @SerializedName("nominal_total_transaksi")
        private String nominalTotalTransaksi;

        @SerializedName("rekening_sumber_dana")
        private String rekeningSumberDana;

        @SerializedName("waktu_transaksi")
        private String transactionTime;

        @SerializedName("no_referensi")
        private String referenceNumber;

        public TransactionResult() {
        }

        public String getProductName() {
            return productName;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public String getNominalTransaksi() {
            return nominalTransaksi;
        }

        public String getNominalBiayaTransaksi() {
            return nominalBiayaTransaksi;
        }

        public String getNominalTotalTransaksi() {
            return nominalTotalTransaksi;
        }

        public String getRekeningSumberDana() {
            return rekeningSumberDana;
        }

        public String getTransactionTime() {
            return transactionTime;
        }

        public String getReferenceNumber() {
            return referenceNumber;
        }
    }

}

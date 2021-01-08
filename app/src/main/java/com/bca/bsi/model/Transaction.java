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
        private double nominalBiayaPembelian;

        @SerializedName("reksa_dana_unit")
        private String reksaDanaUnit;

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

        public double getNominalBiayaPembelian() {
            return nominalBiayaPembelian;
        }

        public void setNominalBiayaPembelian(double nominalBiayaPembelian) {
            this.nominalBiayaPembelian = nominalBiayaPembelian;
        }

        public String getReksaDanaUnit() {
            return reksaDanaUnit;
        }

        public void setReksaDanaUnit(String reksaDanaUnit) {
            this.reksaDanaUnit = reksaDanaUnit;
        }
    }

    public static class TransactionResult{

        @SerializedName("nama_produk")
        private String productName;

        @SerializedName("tipe_pembayaran")
        private String paymentType;

        @SerializedName("nominal_pembelian")
        private String nominalPembelian;

        @SerializedName("nominal_biaya_pembelian")
        private String nominalBiayaPembelian;

        @SerializedName("nominal_total_pembelian")
        private String nominalTotalPembelian;

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

        public String getNominalPembelian() {
            return nominalPembelian;
        }

        public String getNominalBiayaPembelian() {
            return nominalBiayaPembelian;
        }

        public String getNominalTotalPembelian() {
            return nominalTotalPembelian;
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

package com.bca.bsi.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Transaction {

    public static class Purchasing implements Parcelable {

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

        @SerializedName("nominal_biaya_transaksi")
        private String nominalBiayaTransaksi;

        @SerializedName("reksa_dana_unit")
        private String reksaDanaUnit;

        @SerializedName("nab")
        private String nab;

        public Purchasing() {
        }

        protected Purchasing(Parcel in) {
            bcaID = in.readString();
            amount = in.readString();
            accountNumber = in.readString();
            reksaDanaID = in.readString();
            transactionType = in.readString();
            paymentType = in.readString();
            nominalBiayaPembelian = in.readString();
            reksaDanaUnit = in.readString();
            nab = in.readString();
            nominalBiayaTransaksi = in.readString();
        }

        public static final Creator<Purchasing> CREATOR = new Creator<Purchasing>() {
            @Override
            public Purchasing createFromParcel(Parcel in) {
                return new Purchasing(in);
            }

            @Override
            public Purchasing[] newArray(int size) {
                return new Purchasing[size];
            }
        };

        public String getNominalBiayaTransaksi() {
            return nominalBiayaTransaksi;
        }

        public void setNominalBiayaTransaksi(String nominalBiayaTransaksi) {
            this.nominalBiayaTransaksi = nominalBiayaTransaksi;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(bcaID);
            parcel.writeString(amount);
            parcel.writeString(accountNumber);
            parcel.writeString(reksaDanaID);
            parcel.writeString(transactionType);
            parcel.writeString(paymentType);
            parcel.writeString(nominalBiayaPembelian);
            parcel.writeString(reksaDanaUnit);
            parcel.writeString(nab);
            parcel.writeString(nominalBiayaTransaksi);
        }
    }

    public static class TransactionResult implements Parcelable{

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


        protected TransactionResult(Parcel in) {
            productName = in.readString();
            paymentType = in.readString();
            nominalTransaksi = in.readString();
            nominalBiayaTransaksi = in.readString();
            nominalTotalTransaksi = in.readString();
            rekeningSumberDana = in.readString();
            transactionTime = in.readString();
            referenceNumber = in.readString();
        }

        public static final Creator<TransactionResult> CREATOR = new Creator<TransactionResult>() {
            @Override
            public TransactionResult createFromParcel(Parcel in) {
                return new TransactionResult(in);
            }

            @Override
            public TransactionResult[] newArray(int size) {
                return new TransactionResult[size];
            }
        };

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


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(productName);
            dest.writeString(paymentType);
            dest.writeString(nominalTransaksi);
            dest.writeString(nominalBiayaTransaksi);
            dest.writeString(nominalTotalTransaksi);
            dest.writeString(rekeningSumberDana);
            dest.writeString(transactionTime);
            dest.writeString(referenceNumber);
        }
    }

}

package com.bca.bsi.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductRekomen implements Parcelable {
    @SerializedName("id_reksa_dana")
    private String reksaId;

    @SerializedName("nama_reksa_dana")
    private String productName;

    @SerializedName("update_date")
    private String lastDate;

    @SerializedName("persentase")
    private String percentage;

    @SerializedName("kinerja_terakhir")
    private String kinerja;

    @SerializedName("nab")
    private String nab;

    @SerializedName("jenis_reksa_dana")
    private String jenisReksadana;

    private String biayaPembelian;

    public void setBiayaPembelian(String biayaPembelian) {
        this.biayaPembelian = biayaPembelian;
    }

    protected ProductRekomen(Parcel in) {
        reksaId = in.readString();
        productName = in.readString();
        lastDate = in.readString();
        percentage = in.readString();
        kinerja = in.readString();
        nab = in.readString();
        jenisReksadana = in.readString();
        biayaPembelian = in.readString();
    }

    public static final Creator<ProductRekomen> CREATOR = new Creator<ProductRekomen>() {
        @Override
        public ProductRekomen createFromParcel(Parcel in) {
            return new ProductRekomen(in);
        }

        @Override
        public ProductRekomen[] newArray(int size) {
            return new ProductRekomen[size];
        }
    };

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public ProductRekomen(String productName, String lastDate, String percentage, String kinerja, String nab, String jenisReksadana) {
        this.productName = productName;
        this.lastDate = lastDate;
        this.percentage = percentage;
        this.kinerja = kinerja;
        this.nab = nab;
        this.jenisReksadana = jenisReksadana;
    }

    public String getProductName() {
        return productName;
    }

    public String getLastDate() {
        return lastDate;
    }

    public String getPercentage() {
        return percentage;
    }

    public String getKinerja() {
        return kinerja;
    }

    public String getNab() {
        return nab;
    }

    public String getJenisReksadana() {
        return jenisReksadana;
    }

    public String getReksaId() {
        return reksaId;
    }

    public void setReksaId(String reksaId) {
        this.reksaId = reksaId;
    }

    public String getBiayaPembelian() {
        return biayaPembelian;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(reksaId);
        parcel.writeString(productName);
        parcel.writeString(lastDate);
        parcel.writeString(percentage);
        parcel.writeString(kinerja);
        parcel.writeString(nab);
        parcel.writeString(jenisReksadana);
        parcel.writeString(biayaPembelian);
    }
}

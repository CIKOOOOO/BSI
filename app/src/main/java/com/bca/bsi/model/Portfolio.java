package com.bca.bsi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Portfolio {
    @SerializedName("reksa_dana_list")
    private List<ProductRekomen> productRekomenList;

    @SerializedName("return")
    private String expReturn;

    @SerializedName("risk")
    private String risk;

    @SerializedName("minimum_purchase")
    private String minPurchase;

    private String bundleName;

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public Portfolio(String expReturn, String risk, String bundleName) {
        this.expReturn = expReturn;
        this.risk = risk;
        this.bundleName = bundleName;
    }

    public String getExpReturn() {
        return expReturn;
    }

    public String getRisk() {
        return risk;
    }

    public String getBundleName() {
        return bundleName;
    }

    public String getMinPurchase() {
        return minPurchase;
    }

    public void setMinPurchase(String minPurchase) {
        this.minPurchase = minPurchase;
    }

    public List<ProductRekomen> getProductRekomenList() {
        return productRekomenList;
    }

    public static class Information {

        @SerializedName("nama_reksa_dana")
        private String name;

        @SerializedName("kategori")
        private String jenis;

        @SerializedName("reksa_dana_unit")
        private Double unit;

        @SerializedName("total")
        private Double cost;

        @SerializedName("closing_date")
        private String date;

        @SerializedName("profit")
        private Double raise;

        @SerializedName("nab")
        private Double nab;

        public Information() {
        }

        public Double getRaise() {
            return raise;
        }

        public String getName() {
            return name;
        }

        public String getJenis() {
            return jenis;
        }

        public Double getUnit() {
            return unit;
        }

        public Double getCost() {
            return cost;
        }

        public String getDate() {
            return date;
        }

        public Double getNab() {
            return nab;
        }
    }

    public static class History {

        @SerializedName("transaction_type")
        private String transactionType;
        @SerializedName("nama_reksa_dana")
        private String reksadanaName;
        @SerializedName("date")
        private String date;
        @SerializedName("amount")
        private String amount;
        @SerializedName("reksa_dana_unit")
        private String reksaDanaUnit;
        @SerializedName("nab")
        private Double nab;

        public History() {
        }

        public String getTransactionType() {
            return transactionType;
        }

        public String getReksadanaName() {
            return reksadanaName;
        }

        public String getDate() {
            return date;
        }

        public String getAmount() {
            return amount;
        }

        public String getReksaDanaUnit() {
            return reksaDanaUnit;
        }

        public Double getNab() {
            return nab;
        }
    }
}

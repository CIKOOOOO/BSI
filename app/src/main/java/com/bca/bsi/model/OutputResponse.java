package com.bca.bsi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OutputResponse {

    @SerializedName("error_schema")
    private ErrorSchema errorSchema;

    @SerializedName("output_schema")
    private OutputSchema outputSchema;

    public OutputResponse() {
    }

    public ErrorSchema getErrorSchema() {
        return errorSchema;
    }

    public OutputSchema getOutputSchema() {
        return outputSchema;
    }

    public static class ErrorSchema {

        @SerializedName("error_code")
        private String errorCode;

        @SerializedName("error_message")
        private String errorMessage;

        public ErrorSchema() {
        }

        public String getErrorCode() {
            return errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

    public static class OutputSchema {

        @SerializedName("forum_user")
        private User.ForumUser forumUser;

        @SerializedName("welma_user")
        private User.WelmaUser welmaUser;

        @SerializedName("list_products")
        private List<Product.ReksaDana> reksaDanaList;

        @SerializedName("detail_reksadana")
        private Product.DetailReksaDana detailReksaDana;

        @SerializedName("rekening")
        private User.BCAUser.Rekening rekeningUser;

        @SerializedName("transaction")
        private Transaction.TransactionResult transactionResult;

        @SerializedName("kuis_data")
        private KuisData kuisData;

        @SerializedName("bundles")
        private List<Portfolio> bundles;

        @SerializedName("user_score")
        private KuisData.UserScore userScore;

        @SerializedName("portfolio")
        private List<Portfolio.Information> informationList;

        @SerializedName("transaction_history")
        private List<Portfolio.History> historyList;

        @SerializedName("tips")
        private TipsOfTheWeek tipsOfTheWeek;

        @SerializedName("transaction_result_list")
        private List<Transaction.TransactionResult> transactionResultList;

        public OutputSchema() {
        }

        public List<Portfolio> getBundles() {
            return bundles;
        }

        public List<Product.ReksaDana> getReksaDanaList() {
            return reksaDanaList;
        }

        public User.ForumUser getForumUser() {
            return forumUser;
        }

        public User.WelmaUser getWelmaUser() {
            return welmaUser;
        }

        public Product.DetailReksaDana getDetailReksaDana() {
            return detailReksaDana;
        }

        public User.BCAUser.Rekening getRekeningUser() {
            return rekeningUser;
        }

        public Transaction.TransactionResult getTransactionResult() {
            return transactionResult;
        }

        public KuisData getKuisData() {
            return kuisData;
        }

        public KuisData.UserScore getUserScore() {
            return userScore;
        }

        public List<Portfolio.Information> getInformationList() {
            return informationList;
        }

        public List<Portfolio.History> getHistoryList() {
            return historyList;
        }

        public TipsOfTheWeek getTipsOfTheWeek() {
            return tipsOfTheWeek;
        }

        public List<Transaction.TransactionResult> getTransactionResultList() {
            return transactionResultList;
        }
    }
}

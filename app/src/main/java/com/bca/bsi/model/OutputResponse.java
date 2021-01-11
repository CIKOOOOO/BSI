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

    public static class OutputSchema{

        @SerializedName("forum_user")
        private User.ForumUser forumUser;

        @SerializedName("welma_user")
        private User.WelmaUser welmaUser;

        @SerializedName("list_products")
        private List<Product.ReksaDana> reksaDanaList;

        @SerializedName("detail_reksadana")
        private Product.DetailReksaDana detailReksaDana;

        @SerializedName("bca_user")
        private User.BCAUser bcaUser;

        @SerializedName("transaction")
        private Transaction.TransactionResult transactionResult;

        public OutputSchema() {
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

        public User.BCAUser getBcaUser() {
            return bcaUser;
        }

        public Transaction.TransactionResult getTransactionResult() {
            return transactionResult;
        }
    }
}

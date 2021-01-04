package com.bca.bsi.model;

import com.google.gson.annotations.SerializedName;

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
        private int errorCode;

        @SerializedName("error_message")
        private String errorMessage;

        public ErrorSchema() {
        }

        public int getErrorCode() {
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

        public OutputSchema() {
        }

        public User.ForumUser getForumUser() {
            return forumUser;
        }

        public User.WelmaUser getWelmaUser() {
            return welmaUser;
        }
    }
}

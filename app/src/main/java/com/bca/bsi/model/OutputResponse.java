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

        @SerializedName(value = "my_profile", alternate = {"profile"})
        private Forum.User forumProfileUser;

        @SerializedName(value = "my_posting", alternate = {"share_trade", "posting", "trending"})
        private List<Forum.Post> myPostList;

        @SerializedName("my_bookmark")
        private List<Forum.Post> myBookmarkList;

        @SerializedName("picture_list")
        private List<Forum.ProfilePicture> profilePictureList;

        @SerializedName("list_users")
        private List<User.ForumUser> directUserList;

        @SerializedName("list_news")
        private List<PromoNews> promoNewsList;

        @SerializedName("my_inbox")
        private List<Forum.Inbox> inboxList;

        @SerializedName(value = "list_followingme", alternate = {"list_followerme", "list_follower", "list_following"})
        private List<Forum.Connection> connectionList;

        @SerializedName("categories")
        private List<Forum.Category> categoryList;

        @SerializedName("list_reason")
        private List<Forum.Report> reportList;

        @SerializedName("return_save_post")
        private Forum.SavePost savePost;

        @SerializedName("like")
        private Forum.LikePost likePost;

        @SerializedName("detail_post")
        private Forum.Post detailPost;

        @SerializedName("comment")
        private Forum.Comment comment;

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

        public Forum.User getForumProfileUser() {
            return forumProfileUser;
        }

        public List<Forum.Post> getMyPostList() {
            return myPostList;
        }

        public List<Forum.Post> getMyBookmarkList() {
            return myBookmarkList;
        }

        public List<Forum.ProfilePicture> getProfilePictureList() {
            return profilePictureList;
        }

        public List<User.ForumUser> getDirectUserList() {
            return directUserList;
        }

        public List<PromoNews> getPromoNewsList() {
            return promoNewsList;
        }

        public List<Forum.Inbox> getInboxList() {
            return inboxList;
        }

        public List<Forum.Connection> getConnectionList() {
            return connectionList;
        }

        public List<Forum.Category> getCategoryList() {
            return categoryList;
        }

        public List<Forum.Report> getReportList() {
            return reportList;
        }

        public Forum.SavePost getSavePost() {
            return savePost;
        }

        public Forum.LikePost getLikePost() {
            return likePost;
        }

        public Forum.Post getDetailPost() {
            return detailPost;
        }

        public Forum.Comment getComment() {
            return comment;
        }
    }
}

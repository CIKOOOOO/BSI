package com.bca.bsi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forum {

    public static class Post {
        private String postID;
        private String imageProfile;
        private String name;
        private String date;
        private String type;
        private String content;
        private String like;
        private String comment;
        private String share;
        private String statusLike;
        private String statusShare;
        private String profileID;
        private PromoNews promoNews;
        private ShareTrade shareTrade;
        private List<ImagePost> imagePostList;
        private Post post;

        public Post() {
        }

        public Post(String postID, String imageProfile, String name, String date, String type, String content, String statusShare, String like, String comment, String share, String statusLike, String profileID, PromoNews promoNews) {
            this.postID = postID;
            this.imageProfile = imageProfile;
            this.name = name;
            this.date = date;
            this.type = type;
            this.content = content;
            this.like = like;
            this.comment = comment;
            this.share = share;
            this.statusLike = statusLike;
            this.profileID = profileID;
            this.promoNews = promoNews;
            this.statusShare = statusShare;
        }

        public Post(String postID, String imageProfile, String name, String date, String type, String content, String statusShare, String like, String comment, String share, String statusLike, String profileID, ShareTrade shareTrade) {
            this.postID = postID;
            this.imageProfile = imageProfile;
            this.name = name;
            this.date = date;
            this.type = type;
            this.content = content;
            this.like = like;
            this.comment = comment;
            this.share = share;
            this.statusLike = statusLike;
            this.profileID = profileID;
            this.shareTrade = shareTrade;
        }

        public Post(String postID, String imageProfile, String name, String date, String type, String content, String statusShare, String like, String comment, String share, String statusLike, String profileID, List<ImagePost> imagePostList) {
            this.postID = postID;
            this.imageProfile = imageProfile;
            this.name = name;
            this.date = date;
            this.type = type;
            this.content = content;
            this.like = like;
            this.comment = comment;
            this.share = share;
            this.statusLike = statusLike;
            this.profileID = profileID;
            this.imagePostList = imagePostList;
            this.statusShare = statusShare;
        }

        public Post(String postID, String imageProfile, String name, String date, String type, String content, String like, String comment, String share, String statusLike, String profileID, Post post, String statusShare) {
            this.postID = postID;
            this.imageProfile = imageProfile;
            this.name = name;
            this.date = date;
            this.type = type;
            this.content = content;
            this.like = like;
            this.comment = comment;
            this.share = share;
            this.statusLike = statusLike;
            this.profileID = profileID;
            this.post = post;
            this.statusShare = statusShare;
        }

        public void setPostID(String postID) {
            this.postID = postID;
        }

        public void setImageProfile(String imageProfile) {
            this.imageProfile = imageProfile;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setLike(String like) {
            this.like = like;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public void setStatusLike(String statusLike) {
            this.statusLike = statusLike;
        }

        public void setStatusShare(String statusShare) {
            this.statusShare = statusShare;
        }

        public void setProfileID(String profileID) {
            this.profileID = profileID;
        }

        public String getImageProfile() {
            return imageProfile;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public String getType() {
            return type;
        }

        public String getContent() {
            return content;
        }

        public String getLike() {
            return like;
        }

        public String getStatusShare() {
            return statusShare;
        }

        public String getComment() {
            return comment;
        }

        public String getShare() {
            return share;
        }

        public String getStatusLike() {
            return statusLike;
        }

        public String getPostID() {
            return postID;
        }

        public String getProfileID() {
            return profileID;
        }

        public PromoNews getPromoNews() {
            return promoNews;
        }

        public ShareTrade getShareTrade() {
            return shareTrade;
        }

        public List<ImagePost> getImagePostList() {
            return imagePostList;
        }

        public Post getPost() {
            return post;
        }

        public static class ImagePost {
            private String imageID;
            private String imageURL;

            public ImagePost(String imageID, String imageURL) {
                this.imageID = imageID;
                this.imageURL = imageURL;
            }

            public ImagePost() {
            }

            public String getImageID() {
                return imageID;
            }

            public String getImageURL() {
                return imageURL;
            }
        }
    }

    public static class Inbox {

        private String username;
        private String date;
        private String content;

        public Inbox() {
        }

        public Inbox(String username, String date, String content) {
            this.username = username;
            this.date = date;
            this.content = content;
        }

        public String getUsername() {
            return username;
        }

        public String getDate() {
            return date;
        }

        public String getContent() {
            return content;
        }
    }

    public static class Comment {

        private String commentID;
        private String name;
        private String date;
        private String content;
        private String image;

        public Comment() {
        }

        public String getCommentID() {
            return commentID;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public String getContent() {
            return content;
        }

        public String getImage() {
            return image;
        }
    }

    public static class Report {
        private String reportID;
        private String value;
        private boolean choose;

        public Report(String reportID, String value, boolean choose) {
            this.reportID = reportID;
            this.value = value;
            this.choose = choose;
        }

        public void setChoose(boolean choose) {
            this.choose = choose;
        }

        public String getReportID() {
            return reportID;
        }

        public String getValue() {
            return value;
        }

        public boolean isChoose() {
            return choose;
        }
    }

    public static class Category {
        private String categoryID;
        private String categoryName;
        private boolean choose;

        public Category() {
        }

        public Category(String categoryID, String categoryName, boolean choose) {
            this.categoryID = categoryID;
            this.categoryName = categoryName;
            this.choose = choose;
        }

        public String getCategoryID() {
            return categoryID;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public boolean isChoose() {
            return choose;
        }

        public void setChoose(boolean choose) {
            this.choose = choose;
        }
    }

    public static class ShareTrade {

        private String shareTradeID, title, type, value, productName, date;

        public ShareTrade() {
        }

        public ShareTrade(String shareTradeID, String title, String type, String value, String productName, String date) {
            this.shareTradeID = shareTradeID;
            this.title = title;
            this.type = type;
            this.value = value;
            this.productName = productName;
            this.date = date;
        }

        public String getShareTradeID() {
            return shareTradeID;
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }

        public String getValue() {
            return value;
        }

        public String getProductName() {
            return productName;
        }

        public String getDate() {
            return date;
        }
    }

    public static class User {

        @SerializedName("img_background_url")
        private String imgBackgroundUrl;

        @SerializedName("img_profile_url")
        private String imgProfileUrl;

        @SerializedName("follower_count")
        private String followerCount;

        @SerializedName("following_count")
        private String followingCount;

        @SerializedName("flag_unread_inbox")
        private String statusInbox;

        public User() {
        }

        public String getImgBackgroundUrl() {
            return imgBackgroundUrl;
        }

        public String getImgProfileUrl() {
            return imgProfileUrl;
        }

        public String getFollowerCount() {
            return followerCount;
        }

        public String getFollowingCount() {
            return followingCount;
        }

        public String getStatusInbox() {
            return statusInbox;
        }
    }

    public static class ProfilePicture {
        @SerializedName("id")
        private String id;

        @SerializedName("url")
        private String imgURL;

        public ProfilePicture() {
        }

        public String getId() {
            return id;
        }

        public String getImgURL() {
            return imgURL;
        }
    }
}

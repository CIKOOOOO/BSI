package com.bca.bsi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forum {

    public static class Post {
        @SerializedName("post_id")
        private String postID;
        @SerializedName("profile_picture")
        private String imageProfile;
        @SerializedName("username")
        private String name;
        @SerializedName("created_date")
        private String date;
        @SerializedName("post_category")
        private String type;
        @SerializedName("post_text")
        private String content;
        @SerializedName("like_count")
        private String like;
        @SerializedName("comment_count")
        private String comment;
        @SerializedName("share_count")
        private String share;
        @SerializedName("like_status")
        private String statusLike;
        @SerializedName("share_status")
        private String statusShare;
        @SerializedName("profile_id")
        private String profileID;
        @SerializedName("post_privacy")
        private String privacy;
        @SerializedName("save_status")
        private String statusSave;
        private PromoNews promoNews;
        @SerializedName(value = "share_trade_detail", alternate = {"share_trade"})
        private ShareTrade shareTrade;
        private List<ImagePost> imagePostList;
        @SerializedName("post_attachment")
        private List<String> imageURLList;
        @SerializedName("repost_content")
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

        public void setShareTrade(ShareTrade shareTrade) {
            this.shareTrade = shareTrade;
        }

        public String getPrivacy() {
            return privacy;
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

        public List<String> getImagePostList() {
            return imageURLList;
        }

        public Post getPost() {
            return post;
        }

        public String getStatusSave() {
            return statusSave;
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

        @SerializedName("profile_id")
        private String profileID;
        @SerializedName("username")
        private String username;
        @SerializedName("inbox_date")
        private String date;
        @SerializedName("inbox_content")
        private String content;
        @SerializedName("other_profile_id")
        private String otherProfileID;
        @SerializedName("inbox_id")
        private String inboxID;
        @SerializedName("post_id")
        private String postID;

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

        public String getProfileID() {
            return profileID;
        }

        public String getOtherProfileID() {
            return otherProfileID;
        }

        public String getInboxID() {
            return inboxID;
        }

        public String getPostID() {
            return postID;
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

        public Comment(String commentID, String name, String date, String content, String image) {
            this.commentID = commentID;
            this.name = name;
            this.date = date;
            this.content = content;
            this.image = image;
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

        @SerializedName("share_trade_id")
        private String shareTradeID;
        private String title;
        @SerializedName("type")
        private String type;
        @SerializedName("value")
        private String value;
        @SerializedName("reksa_dana_name")
        private String productName;
        @SerializedName("date")
        private String date;
        @SerializedName("reksa_dana_id")
        private String reksadanaID;

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

        public void setTitle(String title) {
            this.title = title;
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

        public String getReksadanaID() {
            return reksadanaID;
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

    public static class Connection {

        @SerializedName("profile_id")
        private String profileID;
        @SerializedName("follow_profile_id")
        private String followProfileID;
        @SerializedName("username")
        private String username;
        @SerializedName("is_followed")
        private String follow;
        @SerializedName("profile_picture")
        private String imgProfile;

        public Connection() {
        }

        public String getProfileID() {
            return profileID;
        }

        public String getFollowProfileID() {
            return followProfileID;
        }

        public String getUsername() {
            return username;
        }

        public String getFollow() {
            return follow;
        }

        public String getImgProfile() {
            return imgProfile;
        }
    }
}

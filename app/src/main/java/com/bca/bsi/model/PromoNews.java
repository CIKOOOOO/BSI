package com.bca.bsi.model;

import com.google.gson.annotations.SerializedName;

public class PromoNews {

    @SerializedName("news_id")
    private String newsID;

    @SerializedName("news_title")
    private String title;

    private String description;

    @SerializedName("news_image")
    private String image;

    @SerializedName("news_content")
    private String content;

    @SerializedName("news_release_date")
    private String date;

    public PromoNews(String newsID, String title, String description, String image, String content, String date) {
        this.newsID = newsID;
        this.title = title;
        this.description = description;
        this.image = image;
        this.content = content;
        this.date = date;
    }

    public String getNewsID() {
        return newsID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }
}

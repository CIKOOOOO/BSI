package com.bca.bsi.model;

public class PromoNews {

    private String newsID;
    private String title, description, image, content, date;


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

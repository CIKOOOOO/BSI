package com.bca.bsi.model;

public class PromoNews {

    private String title, description, image, content, date;

    public PromoNews(String title, String description,String content, String date) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.date = date;
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

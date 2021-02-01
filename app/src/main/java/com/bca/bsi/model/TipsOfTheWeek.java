package com.bca.bsi.model;

import com.google.gson.annotations.SerializedName;

public class TipsOfTheWeek {

    @SerializedName("tips_judul")
    private String title;

    @SerializedName("tips_text")
    private String content;

    @SerializedName("tips_image")
    private String imgURL;

    public TipsOfTheWeek(String title, String content, String imgURL) {
        this.title = title;
        this.content = content;
        this.imgURL = imgURL;
    }

    public TipsOfTheWeek() {
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImgURL() {
        return imgURL;
    }
}

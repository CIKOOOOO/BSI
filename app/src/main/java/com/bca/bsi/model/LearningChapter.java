package com.bca.bsi.model;

public class LearningChapter {
    private int image;
    private String title;
    private String explanation;

    public LearningChapter(int image, String title, String explanation) {
        this.image = image;
        this.title = title;
        this.explanation = explanation;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}

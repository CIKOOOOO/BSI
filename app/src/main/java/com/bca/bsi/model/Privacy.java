package com.bca.bsi.model;

public class Privacy {

    private String name;
    private int image, imageClick;
    private boolean click;


    public Privacy(String name, int image, int imageClick, boolean click) {
        this.name = name;
        this.image = image;
        this.imageClick = imageClick;
        this.click = click;
    }

    public Privacy() {
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getImageClick() {
        return imageClick;
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }
}

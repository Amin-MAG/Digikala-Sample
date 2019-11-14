package com.mag.digikala.Model;

public class Merchandise {

    private String id;
    private String name;
    private DigikalaImage[] images;

    public Merchandise(String name, DigikalaImage[] images) {
        this.name = name;
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return name;
    }

    public DigikalaImage[] getImages() {
        return images;
    }

}

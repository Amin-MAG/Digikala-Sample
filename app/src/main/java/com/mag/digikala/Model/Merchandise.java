package com.mag.digikala.Model;

public class Merchandise {

    private int id;
    private String name;

    public Merchandise(String title) {
        this.name = title;
    }

    public String getTitle() {
        return name;
    }

}

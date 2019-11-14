package com.mag.digikala.Model;

public class DigikalaMenuItem {

    private String name;
    private int drawbleId;

    public DigikalaMenuItem(String name, int drawbleId) {
        this.name = name;
        this.drawbleId = drawbleId;
    }

    public String getName() {
        return name;
    }

    public int getDrawbleId() {
        return drawbleId;
    }

}

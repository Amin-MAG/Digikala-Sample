package com.mag.digikala.data.model;

import android.view.View;

public class MenuItem {

    private String name;
    private int drawbleId;
    private View.OnClickListener listener;

    public MenuItem(String name, int drawbleId, View.OnClickListener listener) {
        this.name = name;
        this.drawbleId = drawbleId;
        this.listener = listener;
    }

    public String getName() {
        return name;
    }

    public int getDrawbleId() {
        return drawbleId;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

}

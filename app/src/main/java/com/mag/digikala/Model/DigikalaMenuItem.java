package com.mag.digikala.Model;

import android.view.View;

public class DigikalaMenuItem {

    private String name;
    private int drawbleId;
    private View.OnClickListener listener;

    public DigikalaMenuItem(String name, int drawbleId, View.OnClickListener listener) {
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

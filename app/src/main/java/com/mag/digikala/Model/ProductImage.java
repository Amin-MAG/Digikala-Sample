package com.mag.digikala.Model;

import androidx.annotation.NonNull;

public class ProductImage {

    private String id;
    private String src;
    private String name;

    public ProductImage(String src, String name) {
        this.src = src;
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public String getName() {
        return name;
    }

}

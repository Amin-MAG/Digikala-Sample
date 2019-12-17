package com.mag.digikala.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {

    private String id;
    private String name;
    private ProductImage[] images;
    private String description;
    @SerializedName("short_description")
    private String shortDescription;
    // prices
    private String price;
    @SerializedName("sale_price")
    private String salePrice;

    public Product(String name, ProductImage[] images, String price, String sale_price) {
        this.name = name;
        this.images = images;
        this.price = price;
        this.salePrice = sale_price;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductImage[] getImages() {
        return images;
    }

    public String getPrice() {
        return price;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        return shortDescription;
    }
}

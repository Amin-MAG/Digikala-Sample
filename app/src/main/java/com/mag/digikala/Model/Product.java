package com.mag.digikala.Model;

import java.io.Serializable;

public class Product implements Serializable {

    private String id;
    private String name;
    private ProductImage[] images;
    // prices
    private String regular_price;
    private String sale_price;

    public Product(String name, ProductImage[] images, String regular_price, String sale_price) {
        this.name = name;
        this.images = images;
        this.regular_price = regular_price;
        this.sale_price = sale_price;
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

    public String getRegular_price() {
        return regular_price;
    }

    public String getSale_price() {
        return sale_price;
    }

}

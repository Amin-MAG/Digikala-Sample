package com.mag.digikala.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CardProduct extends RealmObject {

    @PrimaryKey
    private String id;
    private String productId;
    private int count;
    private String color;

    public CardProduct() {
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public int getCount() {
        return count;
    }

    public String getColor() {
        return color;
    }

}

package com.mag.digikala.Model;

public class CardProduct {

    private Product product;
    private int count;
    private String color;


    public CardProduct(Product product, int count, String color) {
        this.product = product;
        this.count = count;
        this.color = color;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }

    public String getColor() {
        return color;
    }
}

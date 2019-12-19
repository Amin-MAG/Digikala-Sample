package com.mag.digikala.Model;

import java.util.List;

public class ProductAttribute {

    private String id;
    private String name;
    private List<String> options;

    public ProductAttribute(String id, String name, List<String> options) {
        this.id = id;
        this.name = name;
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getOptions() {
        return options;
    }
    
}

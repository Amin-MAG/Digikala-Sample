package com.mag.digikala.data.model;

import com.google.gson.annotations.SerializedName;

public class Category {

    private String id;
    @SerializedName("parent")
    private String parentId;
    private  String name;

    public Category(String id, String parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }
}

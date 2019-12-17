package com.mag.digikala.Model;

import java.util.ArrayList;
import java.util.List;

public class CategoryGroup {

    private String id;
    private String groupName;
    private List<Category> categories;

    public CategoryGroup(String id, String groupName) {
        this.id = id;
        this.groupName = groupName;
        categories = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void  addCategory(Category category) {
        this.categories.add(category);
    }


    public List<Category> getCategories() {
        return categories;
    }


}

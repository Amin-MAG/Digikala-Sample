package com.mag.digikala.Model;

import java.util.List;

public class CategoryGroup {

    private String groupName;
    private List<Category> categories;

    public CategoryGroup(String groupName, List<Category> categories) {
        this.groupName = groupName;
        this.categories = categories;
    }

}

package com.mag.digikala.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.mag.digikala.Model.Category;
import com.mag.digikala.Model.CategoryGroup;

public class CategoryViewModel extends BaseObservable {

    private CategoryGroup categoryGroup;
    private Category category;

    public CategoryViewModel() {

    }

    @Bindable
    public String getCategoryGroupTitle() {
        return categoryGroup.getGroupName();
    }

    @Bindable
    public String getCategoryTitle() {
        return category.getName();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCategoryGroup(CategoryGroup categoryGroup) {
        this.categoryGroup = categoryGroup;
    }

}

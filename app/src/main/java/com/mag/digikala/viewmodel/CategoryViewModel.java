package com.mag.digikala.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.mag.digikala.data.model.Category;
import com.mag.digikala.data.model.CategoryGroup;

public class CategoryViewModel extends AndroidViewModel {

    private CategoryGroup categoryGroup;
    private Category category;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
    }


    public String getCategoryGroupTitle() {
        return categoryGroup.getGroupName();
    }

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

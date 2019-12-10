package com.mag.digikala.Model.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mag.digikala.Controller.Fragments.CategoryListFragment;
import com.mag.digikala.Model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public CategoryViewPagerAdapter(FragmentManager fm, List<Category> categories) {
        super(fm);

        fragments = new ArrayList<>();

        for (Category category : categories)
            fragments.add(CategoryListFragment.newInstance(category));

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}

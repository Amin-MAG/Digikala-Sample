package com.mag.digikala.Model.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mag.digikala.Controller.Fragments.CategoryListFragment;
import com.mag.digikala.Model.Category;
import com.mag.digikala.Model.CategoryGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public CategoryViewPagerAdapter(FragmentManager fm, Map<String, CategoryGroup> categoryMap) {
        super(fm);

        fragments = new ArrayList<>();

        for (String id : categoryMap.keySet())
            fragments.add(CategoryListFragment.newInstance(id));

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

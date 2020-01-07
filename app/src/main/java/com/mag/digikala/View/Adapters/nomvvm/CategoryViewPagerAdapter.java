package com.mag.digikala.View.Adapters.nomvvm;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mag.digikala.View.Fragments.CategoryListFragment;
import com.mag.digikala.Model.CategoryGroup;
import com.mag.digikala.Repository.ProductsRepository;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private List<CategoryGroup> parents;

    public CategoryViewPagerAdapter(FragmentManager fm, List<CategoryGroup> parents) {
        super(fm);

        this.parents = parents;
        this.fragments = new ArrayList<>();

        for (int i = 0; i < parents.size(); i++)
            fragments.add(CategoryListFragment.newInstance(parents.get(i).getId()));

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return parents.get(position).getGroupName();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public int getCategoryPostion(String id) {
        for (int i = 0; i < parents.size(); i++)
            if (parents.get(i).getId().equals(id))
                return i;
        return -1;
    }

}

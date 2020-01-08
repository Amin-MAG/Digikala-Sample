package com.mag.digikala.view.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mag.digikala.view.adapters.nomvvm.CategoryViewPagerAdapter;
import com.mag.digikala.data.model.CategoryGroup;
import com.mag.digikala.data.repository.ProductsRepository;
import com.mag.digikala.R;
import com.mag.digikala.databinding.FragmentCategoryViewPagerBinding;

import java.util.List;

public class CategoryViewPagerFragment extends Fragment {

    public static final String CATEGORY_GRUOP_ID = "default";
    private FragmentCategoryViewPagerBinding binding;

    public static final String ARG_CATEGORY_GROUP_ID = "arg_category_group_id";
    private ViewPager mainViewpager;
    private CategoryViewPagerAdapter categoryViewPagerAdapter;
    private TabLayout tabLayout;

    public static CategoryViewPagerFragment newInstance(String categoryGruopId) {

        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY_GROUP_ID, categoryGruopId);

        CategoryViewPagerFragment fragment = new CategoryViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static CategoryViewPagerFragment newInstance() {
        return newInstance(CATEGORY_GRUOP_ID);
    }

    public CategoryViewPagerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_category_view_pager, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPagerInitialization();

        tabInitialization();

    }

    private void viewPagerInitialization() {
        mainViewpager = binding.fragmentCategoryViewpageViewpager;
        categoryViewPagerAdapter = new CategoryViewPagerAdapter(getFragmentManager(), ProductsRepository.getInstance().getParentCategory());
        mainViewpager.setAdapter(categoryViewPagerAdapter);
        // Should be changed
        mainViewpager.setCurrentItem(getArguments().get(ARG_CATEGORY_GROUP_ID).equals(CATEGORY_GRUOP_ID) ? categoryViewPagerAdapter.getCount() - 1 : categoryViewPagerAdapter.getCategoryPostion(getArguments().getString(ARG_CATEGORY_GROUP_ID)));
        mainViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
    }

    private void tabInitialization() {
        tabLayout = binding.categoryActivityTabLayout;
        addTabsToTabLayout();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mainViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setupWithViewPager(mainViewpager);
    }

    private void addTabsToTabLayout() {
        List<CategoryGroup> categoryGroups = ProductsRepository.getInstance().getParentCategory();
        for (CategoryGroup categoryGroup : categoryGroups) {
            tabLayout.addTab(tabLayout.newTab().setText(categoryGroup.getGroupName()));
        }
    }

}

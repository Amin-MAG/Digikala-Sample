package com.mag.digikala.Controller.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.mag.digikala.Model.Adapter.CategoryViewPagerAdapter;
import com.mag.digikala.Model.CategoryGroup;
import com.mag.digikala.Model.ProductsRepository;
import com.mag.digikala.R;

import java.util.List;

public class CategoryViewPagerFragment extends Fragment {


    public static final String ARG_CATEGORY_GROUP_ID = "arg_category_group_id";
    private ViewPager mainViewpager;
    private CategoryViewPagerAdapter categoryViewPagerAdapter;
    private TabLayout tabLayout;

    public static CategoryViewPagerFragment newInstance(String categoryGruopId) {

        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY_GROUP_ID,categoryGruopId);

        CategoryViewPagerFragment fragment = new CategoryViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static CategoryViewPagerFragment newInstance() {
        return newInstance("-1");
    }

    public CategoryViewPagerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_view_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewpager = view.findViewById(R.id.fragment_category_viewpage__viewpager);
        categoryViewPagerAdapter = new CategoryViewPagerAdapter(getFragmentManager(), ProductsRepository.getInstance().getParentCategory());
        mainViewpager.setAdapter(categoryViewPagerAdapter);
        mainViewpager.setCurrentItem(getArguments().get(ARG_CATEGORY_GROUP_ID) == "-1" ? categoryViewPagerAdapter.getCount() - 1 : categoryViewPagerAdapter.getCategoryPostion(getArguments().getString(ARG_CATEGORY_GROUP_ID)));
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

        tabLayout = view.findViewById(R.id.category_activity__tabLayout);
        List<CategoryGroup> categoryGroups = ProductsRepository.getInstance().getParentCategory();
        for (int k = 0; k < categoryGroups.size(); k++) {

            tabLayout.addTab(tabLayout.newTab().setText(categoryGroups.get(k).getGroupName()));
            Log.d("Group233", "onViewCreated: " + categoryGroups.get(k).getGroupName());

        }


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

}

package com.mag.digikala.Controller.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mag.digikala.Model.Adapter.CategoryViewPagerAdapter;
import com.mag.digikala.Model.Category;
import com.mag.digikala.R;

import java.util.ArrayList;

public class CategoryViewPagerFragment extends Fragment {


    private ViewPager mainViewpager;
    private CategoryViewPagerAdapter categoryViewPagerAdapter;

    public static CategoryViewPagerFragment newInstance() {

        Bundle args = new Bundle();

        CategoryViewPagerFragment fragment = new CategoryViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
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
        categoryViewPagerAdapter = new CategoryViewPagerAdapter(getFragmentManager(),new ArrayList<Category>(){{
            add(new Category("Something"));
            add(new Category("Something"));
            add(new Category("Something"));
            add(new Category("Something"));
            add(new Category("Something"));
            add(new Category("Something"));
        }});
        mainViewpager.setAdapter(categoryViewPagerAdapter);

    }


}

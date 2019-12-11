package com.mag.digikala.Controller.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mag.digikala.Model.Adapter.CategoryListAdapter;
import com.mag.digikala.Model.Category;
import com.mag.digikala.Model.ProductsRepository;
import com.mag.digikala.R;

import java.util.ArrayList;

public class CategoryListFragment extends Fragment {

    private RecyclerView categoryRecycler;
    private CategoryListAdapter categoryListAdapter;

    public static CategoryListFragment newInstance(Category category) {

        Bundle args = new Bundle();

        CategoryListFragment fragment = new CategoryListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CategoryListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        categoryRecycler = view.findViewById(R.id.category_list_fragment__main_recycler);
//        categoryListAdapter = new CategoryListAdapter(new ArrayList<Category>() {{
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//            add(new Category("something"));
//        }});
        categoryListAdapter = new CategoryListAdapter(ProductsRepository.getInstance().getCategories());
        categoryRecycler.setAdapter(categoryListAdapter);


    }

}

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
import com.mag.digikala.Model.CategoryGroup;
import com.mag.digikala.Model.ProductsRepository;
import com.mag.digikala.R;

public class CategoryListFragment extends Fragment {

    public static final String ARG_CATEGORY_ID = "arg_category_id";
    private RecyclerView categoryRecycler;
    private CategoryListAdapter categoryListAdapter;

    public static CategoryListFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY_ID, id);

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
        categoryListAdapter = new CategoryListAdapter(ProductsRepository.getInstance().getCategoryMap().get(getArguments().get(ARG_CATEGORY_ID)).getCategories());
        categoryRecycler.setAdapter(categoryListAdapter);


    }

}

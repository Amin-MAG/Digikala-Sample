package com.mag.digikala.View.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.mag.digikala.Model.ProductsRepository;
import com.mag.digikala.R;
import com.mag.digikala.View.Adapters.CategoryListAdapter;
import com.mag.digikala.databinding.FragmentCategoryListBinding;

public class CategoryListFragment extends Fragment {

    public static final String ARG_CATEGORY_ID = "arg_category_id";

    private FragmentCategoryListBinding binding;

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
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_category_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setAdapter();

    }

    private void setAdapter() {
        categoryListAdapter = new CategoryListAdapter(ProductsRepository.getInstance().getCategoryMap().get(getArguments().get(ARG_CATEGORY_ID)).getCategories());
        binding.categoryListFragmentMainRecycler.setAdapter(categoryListAdapter);
    }

}

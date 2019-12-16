package com.mag.digikala.Controller.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Model.Adapter.FilterListAdapter;
import com.mag.digikala.Model.Product;
import com.mag.digikala.Model.ProductsRepository;
import com.mag.digikala.R;

import java.util.ArrayList;

public class FilterFragment extends Fragment {

    public static final String ARG_SEARCH_STRING = "arg_search_string";
    private String searchString;

    private RecyclerView filterRecycler;
    private FilterListAdapter filterListAdapter;

    public static FilterFragment newInstance(String searchString) {

        Bundle args = new Bundle();
        args.putString(ARG_SEARCH_STRING, searchString);

        FilterFragment fragment = new FilterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchString = getArguments().getString(ARG_SEARCH_STRING);

        filterRecycler = view.findViewById(R.id.filter_fragment__recycler);
        filterListAdapter = new FilterListAdapter(ProductsRepository.getInstance().getAllProducts());
        filterRecycler.setAdapter(filterListAdapter);

    }

}

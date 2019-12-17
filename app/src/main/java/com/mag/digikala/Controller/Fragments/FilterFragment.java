package com.mag.digikala.Controller.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Model.Adapter.FilterListAdapter;
import com.mag.digikala.Model.Category;
import com.mag.digikala.Model.Product;
import com.mag.digikala.Model.ProductsRepository;
import com.mag.digikala.Network.RetrofitApi;
import com.mag.digikala.Network.RetrofitInstance;
import com.mag.digikala.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterFragment extends Fragment {

    public static final String ARG_SEARCH_STRING = "arg_search_string";
    private String searchString;
    private List<Category> searchItem;
    private RetrofitApi retrofitApi;


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
        retrofitApi = RetrofitInstance.getInstance().create(RetrofitApi.class);
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
        filterListAdapter = new FilterListAdapter(new ArrayList<Product>());
        filterRecycler.setAdapter(filterListAdapter);


        retrofitApi.searchProducts(searchString).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (response.isSuccessful()) {

                    filterListAdapter.setData(response.body());
                    filterListAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }

        });


    }

}

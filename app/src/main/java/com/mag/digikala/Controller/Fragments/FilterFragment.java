package com.mag.digikala.Controller.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Model.Adapter.FilterListAdapter;
import com.mag.digikala.Model.Category;
import com.mag.digikala.Model.Product;
import com.mag.digikala.Network.RetrofitApi;
import com.mag.digikala.Network.RetrofitInstance;
import com.mag.digikala.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mag.digikala.Controller.Fragments.SortSelectionDialogFragment.EXTRA_SORT_ID;

public class FilterFragment extends Fragment {

    public static final String ARG_SEARCH_STRING = "arg_search_string";
    public static final String ARG_CATEGORY_ID = "arg_category_id";
    private static final int REQUEST_CODE_FOR_SORT_DIALOG = 15001;
    public static final String SORT_SELECTION_DIALOG_FRAGMENT = "sort_selection_dialog_fragment";


    private String searchString;
    String categoryid;
    private List<Category> searchItem;
    private RetrofitApi retrofitApi;
    private FilterSelectionCallBack filterSelectionCallBack;

    public static enum SORT_MODE {
        SORT_BY_VIEW(0), SORT_BY_SELL(1), SORT_BY_PRICE_ASCENDING(2), SORT_BY_PRICE_DESCENDING(3), SORT_BY_NEWEST(4);

        private int code;

        SORT_MODE(int code) {
            this.code = code;
        }

        public int getNumVal() {
            return code;
        }

    }

    private TextView sortMode;
    private TextView filterMode;
    private RecyclerView filterRecycler;
    private FilterListAdapter filterListAdapter;
    private ConstraintLayout sortingLayout;
    private ConstraintLayout filterLayout;

    public static FilterFragment newInstance(String searchString, String categoryId) {

        Bundle args = new Bundle();
        args.putString(ARG_SEARCH_STRING, searchString);
        args.putString(ARG_CATEGORY_ID, categoryId);

        FilterFragment fragment = new FilterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof FilterSelectionCallBack)
            filterSelectionCallBack = (FilterSelectionCallBack) getActivity();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_FOR_SORT_DIALOG:

                if (resultCode == Activity.RESULT_OK) {

                    sortPage(SORT_MODE.values()[data.getExtras().getInt(EXTRA_SORT_ID)]);

                }

            default:
                break;
        }

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
        categoryid = getArguments().getString(ARG_CATEGORY_ID);

        findConponents(view);

        sortMode.setText(getResources().getString(R.string.no_mode));
        filterMode.setText(R.string.filter_default);
        filterListAdapter = new FilterListAdapter(new ArrayList<>());
        filterRecycler.setAdapter(filterListAdapter);

        setEvents();

        initialSearchRequest();


    }

    private void setEvents() {
        filterLayout.setOnClickListener(filterModeView->{
            filterSelectionCallBack.showPage();;
        });

        sortingLayout.setOnClickListener(sortingModeView -> {
            SortSelectionDialogFragment sortSelectionDialogFragment = SortSelectionDialogFragment.newInstance();
            sortSelectionDialogFragment.setTargetFragment(FilterFragment.this, REQUEST_CODE_FOR_SORT_DIALOG);
            sortSelectionDialogFragment.show(getFragmentManager(), SORT_SELECTION_DIALOG_FRAGMENT);
        });
    }

    private void initialSearchRequest() {
        HashMap<String, String> queryHashMap = new HashMap<>();
        retrofitApi.searchProducts(searchString, queryHashMap).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (response.isSuccessful()) {

                    List<Product> filteredProducts = response.body();

                    if (categoryid != null) {
                        filteredProducts = new ArrayList<>();
                        for (Product product : response.body())
                            for (Category category : product.getCategories())
                                if (category.getId().equals(categoryid))
                                    filteredProducts.add(product);
                    }

                    filterListAdapter.setData(filteredProducts);
                    filterListAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }

        });
    }

    private void findConponents(@NonNull View view) {
        filterRecycler = view.findViewById(R.id.filter_fragment__recycler);
        filterLayout = view.findViewById(R.id.filter_fragment__filter_constrain_layout);
        sortingLayout = view.findViewById(R.id.filter_fragment__sorting_constrain_layout);
        sortMode = view.findViewById(R.id.filter_fragment__sort_mode);
        filterMode = view.findViewById(R.id.filter_fragment__filter_mode);
    }

    private void sortPage(SORT_MODE mode) {

        HashMap<String, String> queryHashMap = new HashMap<>();

        Log.d("orderREquest", "onResponse: " + mode.toString());

        switch (mode) {
            case SORT_BY_VIEW:
                queryHashMap.put("orderby", "popularity");
                sortMode.setText(getResources().getString(R.string.most_views));
                break;
            case SORT_BY_SELL:
                queryHashMap.put("orderby", "rating");
                sortMode.setText(getResources().getString(R.string.best_sellers));
                break;
            case SORT_BY_PRICE_ASCENDING:
                queryHashMap.put("orderby", "price");
                queryHashMap.put("order", "asc");
                sortMode.setText(getResources().getString(R.string.price_ascending));
                break;
            case SORT_BY_PRICE_DESCENDING:
                queryHashMap.put("orderby", "price");
                queryHashMap.put("order", "desc");
                sortMode.setText(getResources().getString(R.string.price_descending));
                break;
            case SORT_BY_NEWEST:
                queryHashMap.put("orderby", "date");
                sortMode.setText(getResources().getString(R.string.newests));
                break;
            default:
                break;
        }

        retrofitApi.searchProducts(searchString, queryHashMap).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (response.isSuccessful()) {

                    List<Product> filteredProducts = response.body();

                    if (categoryid != null) {
                        filteredProducts = new ArrayList<>();
                        for (Product product : response.body())
                            for (Category category : product.getCategories())
                                if (category.getId().equals(categoryid))
                                    filteredProducts.add(product);
                    }

                    filterListAdapter.setData(filteredProducts);
                    filterListAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }

    public interface FilterSelectionCallBack {
        void showPage();
    }

}

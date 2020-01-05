package com.mag.digikala.View.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.mag.digikala.Controller.Activities.FilterActivity;
import com.mag.digikala.Controller.Fragments.SortSelectionDialogFragment;
import com.mag.digikala.Model.Adapter.FilterListAdapter;
import com.mag.digikala.R;
import com.mag.digikala.databinding.FragmentFilterBinding;
import com.mag.digikala.viewmodel.FilterViewModel;

import static com.mag.digikala.Controller.Fragments.SortSelectionDialogFragment.EXTRA_SORT_ID;

public class FilterFragment extends Fragment {


    public static final String ARG_SEARCH_STRING = "arg_search_string";
    public static final String ARG_CATEGORY_ID = "arg_category_id";
    private static final int REQUEST_CODE_FOR_SORT_DIALOG = 15001;
    public static final String SORT_SELECTION_DIALOG_FRAGMENT = "sort_selection_dialog_fragment";

    private FragmentFilterBinding binding;

    private FilterViewModel viewModel;

    private FilterSelectionCallBack filterSelectionCallBack;


    private FilterListAdapter filterListAdapter;

    public static FilterFragment newInstance(String searchString, String categoryId) {

        Bundle args = new Bundle();
        args.putString(ARG_SEARCH_STRING, searchString);
        args.putString(ARG_CATEGORY_ID, categoryId);

        FilterFragment fragment = new FilterFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_FOR_SORT_DIALOG:

                if (resultCode == Activity.RESULT_OK) {

                    viewModel.setSortMode(FilterViewModel.SORT_MODE.values()[data.getExtras().getInt(EXTRA_SORT_ID)]);

                }

            default:
                break;
        }

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof FilterSelectionCallBack)
            filterSelectionCallBack = (FilterSelectionCallBack) getActivity();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new FilterViewModel(getArguments().getString(ARG_SEARCH_STRING), getArguments().getString(ARG_CATEGORY_ID));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_filter, container, false);
        binding.setFilterViewHolder(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setAdapter();

        setEvents();

    }

    private void setAdapter() {
        filterListAdapter = new FilterListAdapter();
        binding.filterFragmentRecycler.setAdapter(filterListAdapter);
        viewModel.getFilteredProducts().observe(this, products -> {
            filterListAdapter.setData(products);
        });
        ((FilterActivity) getActivity()).getFilterSelectionViewModel().getSelectedAttribute().observe(this, attribute -> {
            viewModel.filter();
        });
        viewModel.filter();

    }

    private void setEvents() {

        binding.filterFragmentFilterConstrainLayout.setOnClickListener(filterModeView -> {
            filterSelectionCallBack.showFitlerSelectionPage();
        });

        binding.filterFragmentSortingConstrainLayout.setOnClickListener(sortingModeView -> {
            SortSelectionDialogFragment sortSelectionDialogFragment = SortSelectionDialogFragment.newInstance();
            sortSelectionDialogFragment.setTargetFragment(FilterFragment.this, REQUEST_CODE_FOR_SORT_DIALOG);
            sortSelectionDialogFragment.show(getFragmentManager(), SORT_SELECTION_DIALOG_FRAGMENT);
        });

    }

    public interface FilterSelectionCallBack {
        void showFitlerSelectionPage();
    }

    public FilterViewModel getViewModel() {
        return viewModel;
    }

}

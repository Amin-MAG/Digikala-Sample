package com.mag.digikala.View.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.mag.digikala.Controller.Activities.FilterActivity;
import com.mag.digikala.View.Adapters.FilterSelectionAttributesRecyclerAdapter;
import com.mag.digikala.View.Adapters.FilterSelectionTermsRecyclerAdapter;
import com.mag.digikala.R;
import com.mag.digikala.databinding.FragmentFilterSelectionBinding;
import com.mag.digikala.viewmodel.FilterSelectionViewModel;

public class FilterSelectionFragment extends Fragment {

    private FragmentFilterSelectionBinding binding;

    private FilterSelectionViewModel viewModel;

    private FilterSelectionFragmentCallBack callBack;

    private FilterSelectionAttributesRecyclerAdapter attributesRecyclerAdapter;
    private FilterSelectionTermsRecyclerAdapter termsRecyclerAdapter;

    public static FilterSelectionFragment newInstance(FilterSelectionFragmentCallBack callBack) {

        Bundle args = new Bundle();

        FilterSelectionFragment fragment = new FilterSelectionFragment(callBack);
        fragment.setArguments(args);
        return fragment;
    }

    public FilterSelectionFragment(FilterSelectionFragmentCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_filter_selection, container, false);
        viewModel = ((FilterActivity) getActivity()).getFilterSelectionViewModel();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setAdapter();

        binding.filterSelectionFragmentFilterBtn.setOnClickListener(filterBtnView -> callBack.filter());

//        binding.filterSelectionFragmentFilterBtn.setOnClickListener(filterBtnView -> viewModel.filter());


    }

    private void setAdapter() {

        termsRecyclerAdapter = new FilterSelectionTermsRecyclerAdapter(viewModel);
        attributesRecyclerAdapter = new FilterSelectionAttributesRecyclerAdapter(viewModel,termsRecyclerAdapter);

        binding.filterSelectionFragmentOptionsRecycler.setAdapter(termsRecyclerAdapter);
        binding.filterSelectionFragmentAttributeRecycler.setAdapter(attributesRecyclerAdapter);

    }

    public interface FilterSelectionFragmentCallBack {
        void filter();
    }

}

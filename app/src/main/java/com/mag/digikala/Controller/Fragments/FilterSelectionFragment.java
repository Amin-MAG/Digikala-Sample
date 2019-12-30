package com.mag.digikala.Controller.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.mag.digikala.Model.Adapter.FilterSelectionAttributesRecyclerAdapter;
import com.mag.digikala.Model.Adapter.FilterSelectionTermsRecyclerAdapter;
import com.mag.digikala.Model.ProductAttributesRepository;
import com.mag.digikala.R;
import com.mag.digikala.databinding.FragmentFilterSelectionBinding;

public class FilterSelectionFragment extends Fragment {

    private FragmentFilterSelectionBinding binding;

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
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setAdapter();

        binding.filterSelectionFragmentFilterBtn.setOnClickListener(filterBtnView -> callBack.filter());

    }

    private void setAdapter() {

        attributesRecyclerAdapter = new FilterSelectionAttributesRecyclerAdapter(ProductAttributesRepository.getInstance().getAttributes(), () -> {
            termsRecyclerAdapter = new FilterSelectionTermsRecyclerAdapter(attributesRecyclerAdapter.getSelected());
            binding.filterSelectionFragmentOptionsRecycler.setAdapter(termsRecyclerAdapter);
        });

        binding.filterSelectionFragmentAttributeRecycler.setAdapter(attributesRecyclerAdapter);

    }

    public interface FilterSelectionFragmentCallBack {
        void filter();
    }

}

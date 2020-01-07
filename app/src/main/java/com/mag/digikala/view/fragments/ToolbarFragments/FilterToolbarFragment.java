package com.mag.digikala.view.fragments.ToolbarFragments;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.mag.digikala.view.activities.CardActivity;
import com.mag.digikala.view.activities.SearchActivity;
import com.mag.digikala.R;
import com.mag.digikala.data.var.Constants;
import com.mag.digikala.databinding.FragmentFilterToolbarBinding;
import com.mag.digikala.viewmodel.MainToolbarViewModel;

public class FilterToolbarFragment extends Fragment {

    private MainToolbarViewModel viewModel;

    private FragmentFilterToolbarBinding binding;

    private TextView cardNumber;

    public static FilterToolbarFragment newInstance() {

        Bundle args = new Bundle();

        FilterToolbarFragment fragment = new FilterToolbarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public FilterToolbarFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MainToolbarViewModel.class);
        viewModel.loadData();
        viewModel.getNumberOfCardProducts().observe(this, numberOfCardProducts -> {
            if (numberOfCardProducts == 0) {
                cardNumber.setBackgroundColor(getResources().getColor(R.color.nothing));
                cardNumber.setText(Constants.EMPTY_CHAR);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    cardNumber.setBackground(getResources().getDrawable(R.drawable.cart_counter));
                }
                cardNumber.setText(String.valueOf(numberOfCardProducts));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_filter_toolbar, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardNumber = binding.filterToolbarFragmentCardNumber;

        setEvents();

    }

    private void setEvents() {
        binding.filterToolbarFragmentBackBtn.setOnClickListener(backBtnView -> getActivity().finish());
        binding.filterToolbarFragmentSearchBtn.setOnClickListener(searchBtnView -> startActivity(SearchActivity.newIntent(getContext())));
        binding.filterToolbarFragmentCardBtn.setOnClickListener(cardBtnView -> startActivity(CardActivity.newIntent(getContext())));
    }

}
